package unq.dapp.supergol.server.main;

import javafx.util.Pair;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import unq.dapp.supergol.server.dependencyInjection.DI;
import unq.dapp.supergol.server.dependencyInjection.WithProductionDependencies;
import unq.dapp.supergol.model.*;
import unq.dapp.supergol.model.repositories.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bootstrap implements DI, WithProductionDependencies, TransactionalOps, WithGlobalEntityManager {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      newTeam("Club Atletico San Lorenzo de Almagro",
        pair(Position.GOALKEEPER, "Sebastian Torrico"),
        pair(Position.DEFENDER, "Julio Alberto Buffarini"),
        pair(Position.DEFENDER, "Mario Yepes"),
        pair(Position.DEFENDER, "Emanuel Mas"),
        pair(Position.MIDFIELDER, "Leandro Romagnoli"),
        pair(Position.MIDFIELDER, "Nestor Ortigoza"),
        pair(Position.MIDFIELDER, "Sebastian Blanco"),
        pair(Position.MIDFIELDER, "Juan Ignacio Mercier"),
        pair(Position.FORWARD, "Mauro Matas"),
        pair(Position.FORWARD, "Hector Daniel Villalba"),
        pair(Position.FORWARD, "Martin Cauteruccio"));

      Collection<Player> aTeam = newTeam("Club Atletico Huracan",
        pair(Position.GOALKEEPER, "Marcos Guillermo Diaz"),
        pair(Position.DEFENDER, "Carlos Arano"),
        pair(Position.DEFENDER, "Federico Mancinelli"),
        pair(Position.DEFENDER, "Martin Nervo"),
        pair(Position.MIDFIELDER, "Patricio Toranzo"),
        pair(Position.MIDFIELDER, "Edson Puch"),
        pair(Position.MIDFIELDER, "Sebastian Blanco"),
        pair(Position.MIDFIELDER, "Juan Ignacio Mercier"),
        pair(Position.FORWARD, "Mauro Matas"),
        pair(Position.FORWARD, "Hector Daniel Villalba"),
        pair(Position.FORWARD, "Martin Cauteruccio"));

      Repository<League> leagueRepo = getRepository(League.class);

      Team villaLuro = Team.named("Los pibes de Villa Luro");


      Team floresta = Team.named("Los invencibles de Floresta");

      Team mataderos = Team.named("Los maravillosos de Mataderos");
      mataderos.setLogoUrl("http://media-cdn.tripadvisor.com/media/photo-s/03/7e/59/c9/feria-de-mataderos.jpg");

      Team flores = Team.named("Los heroes de Flores");
      flores.setLogoUrl("http://sp3.fotolog.com/photo/3/0/70/merinali/1216751367742_f.jpg");
      aTeam.forEach(flores::addPlayer);

      leagueRepo.add(
        League.withAllowedTeams(2, 4, "Champion liga")
          .addTeam(villaLuro)
          .addTeam(floresta)
          .addTeam(flores)
          .addTeam(mataderos)
          .addStage(
            newStage(LocalDate.of(2015, Month.AUGUST, 13),
              pair(villaLuro, floresta),
              pair(mataderos, flores)
            )
          )
          .addStage(
            newStage(LocalDate.of(2015, Month.AUGUST, 20),
              pair(villaLuro, flores),
              pair(mataderos, floresta)
            )
          )
          .addStage(
            newStage(LocalDate.of(2015, Month.AUGUST, 27),
              pair(mataderos, villaLuro),
              pair(flores, floresta)
            )
          )
      );
    });
  }

  @SafeVarargs
  private final Stage newStage(LocalDate date, Pair<Team, Team>... matches) {
    Stage stage = Stage.ofDate(date);

    Arrays.asList(matches)
      .stream()
      .map(it -> Match.versus(stage, it.getKey(), it.getValue()))
      .forEach(it -> getRepository(Match.class).add(it));

    return stage;
  }

  @SafeVarargs
  private final Collection<Player> newTeam(String name, Pair<Position, String>... players) {
    List<Player> playersList = Arrays.asList(players)
      .stream()
      .map(it -> newPlayer(it.getKey(), it.getValue(), name)).collect(Collectors.toList());

    playersList.forEach(it -> getRepository(Player.class).add(it));

    return playersList;
  }

  private Player newPlayer(Position position, String name, String team) {
    Player player = Player.ofTeam(position, team);
    player.setName(name);

    return player;
  }

  protected <U,V> Pair<U,V> pair(U u, V v){
    return new Pair<>(u,v);
  }
}
