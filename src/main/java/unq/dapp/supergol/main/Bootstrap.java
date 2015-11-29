package unq.dapp.supergol.main;

import javafx.util.Pair;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import unq.dapp.supergol.dependencyInjection.DI;
import unq.dapp.supergol.dependencyInjection.WithProductionDependencies;
import unq.dapp.supergol.model.*;
import unq.dapp.supergol.model.repositories.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class Bootstrap implements DI, WithProductionDependencies, TransactionalOps, WithGlobalEntityManager {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      newTeam("Club Atletico San Lorenzo de Almagro",
        new Pair<>(Position.GOALKEEPER, "Sebastian Torrico"),
        new Pair<>(Position.DEFENDER, "Julio Alberto Buffarini"),
        new Pair<>(Position.DEFENDER, "Mario Yepes"),
        new Pair<>(Position.DEFENDER, "Emanuel Mas"),
        new Pair<>(Position.MIDFIELDER, "Leandro Romagnoli"),
        new Pair<>(Position.MIDFIELDER, "Nestor Ortigoza"),
        new Pair<>(Position.MIDFIELDER, "Sebastian Blanco"),
        new Pair<>(Position.MIDFIELDER, "Juan Ignacio Mercier"),
        new Pair<>(Position.FORWARD, "Mauro Matas"),
        new Pair<>(Position.FORWARD, "Hector Daniel Villalba"),
        new Pair<>(Position.FORWARD, "Martin Cauteruccio"));

      newTeam("Club Atletico Huracan",
        new Pair<>(Position.GOALKEEPER, "Marcos Guillermo Diaz"),
        new Pair<>(Position.DEFENDER, "Carlos Arano"),
        new Pair<>(Position.DEFENDER, "Federico Mancinelli"),
        new Pair<>(Position.DEFENDER, "Martin Nervo"),
        new Pair<>(Position.MIDFIELDER, "Patricio Toranzo"),
        new Pair<>(Position.MIDFIELDER, "Edson Puch"),
        new Pair<>(Position.MIDFIELDER, "Sebastian Blanco"),
        new Pair<>(Position.MIDFIELDER, "Juan Ignacio Mercier"),
        new Pair<>(Position.FORWARD, "Mauro Matas"),
        new Pair<>(Position.FORWARD, "Hector Daniel Villalba"),
        new Pair<>(Position.FORWARD, "Martin Cauteruccio"));

      Repository<League> leagueRepo = getRepository(League.class);

      Team villaLuro = Team.named("Los pibes de Villa Luro");
      Team floresta = Team.named("Los invencibles de Floresta");
      Team mataderos = Team.named("Los maravillosos de Mataderos");
      Team flores = Team.named("Los heroes de Flores");

      Stage stage = Stage.ofDate(LocalDate.of(2015, Month.AUGUST, 13));
      Match.versus(stage, villaLuro, floresta);
      Match.versus(stage, mataderos, flores);

      leagueRepo.add(
        League.withAllowedTeams(2, 4, "Champion liga")
          .addTeam(villaLuro)
          .addTeam(floresta)
          .addTeam(mataderos)
          .addStage(stage)
      );
    });
  }

  @SafeVarargs
  private final void newTeam(String name, Pair<Position, String>... players) {
    RealWorldTeam sanLorenzo = RealWorldTeam.named(name);

    Arrays.asList(players)
      .stream()
      .map(it -> newPlayer(it.getKey(), it.getValue(), sanLorenzo))
      .forEach(it -> getRepository(Player.class).add(it));
  }

  private Player newPlayer(Position position, String name, RealWorldTeam team) {
    Player player = Player.ofTeam(position, team);
    player.setName(name);

    return player;
  }
}
