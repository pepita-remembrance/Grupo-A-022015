package unq.dapp.supergol.main;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import unq.dapp.supergol.dependencyInjection.DI;
import unq.dapp.supergol.dependencyInjection.WithProductionDependencies;
import unq.dapp.supergol.model.League;
import unq.dapp.supergol.model.Match;
import unq.dapp.supergol.model.Stage;
import unq.dapp.supergol.model.Team;
import unq.dapp.supergol.model.repositories.Repository;

import java.time.LocalDate;
import java.time.Month;

public class Bootstrap implements DI, WithProductionDependencies, TransactionalOps, WithGlobalEntityManager {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
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

}
