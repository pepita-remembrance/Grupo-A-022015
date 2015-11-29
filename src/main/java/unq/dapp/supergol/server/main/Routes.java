package unq.dapp.supergol.server.main;

import unq.dapp.supergol.server.controllers.CRUDController;
import unq.dapp.supergol.server.dependencyInjection.DI;
import unq.dapp.supergol.server.dependencyInjection.WithProductionDependencies;
import unq.dapp.supergol.model.League;
import unq.dapp.supergol.model.Player;

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
    new CRUDController<>(getRepository(League.class)).registerRoutes("/leagues");
    new CRUDController<>(getRepository(Player.class)).registerRoutes("/players");
  }
}
