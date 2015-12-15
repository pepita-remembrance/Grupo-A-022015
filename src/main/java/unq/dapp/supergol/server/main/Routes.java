package unq.dapp.supergol.server.main;

import unq.dapp.supergol.model.League;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Stage;
import unq.dapp.supergol.model.Team;
import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.server.controllers.CRUDController;
import unq.dapp.supergol.server.controllers.StageController;
import unq.dapp.supergol.server.controllers.TeamController;
import unq.dapp.supergol.server.dependencyInjection.DI;
import unq.dapp.supergol.server.dependencyInjection.WithProductionDependencies;

import static spark.Spark.staticFileLocation;
import static spark.SparkBase.port;

public class Routes implements DI, WithProductionDependencies {
  public static void main(String[] args) {
    System.out.println("Starting server...");

    port(8080);

    staticFileLocation("/public");

    new Bootstrap().run();
    new Routes().registerRoutes();
  }

  private void registerRoutes() {
    registerCrudEndpoint(League.class, "/leagues");
    registerCrudEndpoint(Player.class, "/players");

    new TeamController(Team.class, getRepository(Team.class), getRepository(League.class))
      .registerRoutes("/teams");

    new StageController(Stage.class, getRepository(Stage.class), getRepository(Player.class))
      .registerRoutes("/stages");
  }

  private <T extends Persistable> void registerCrudEndpoint(Class<T> modelClazz, String route) {
    crudEndpoint(modelClazz).registerRoutes(route);
  }

  private <T extends Persistable> CRUDController<T> crudEndpoint(Class<T> modelClazz) {
    return new CRUDController<>(modelClazz, getRepository(modelClazz));
  }
}
