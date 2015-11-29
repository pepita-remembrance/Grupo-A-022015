package unq.dapp.supergol.main;

import unq.dapp.supergol.controllers.CRUDController;
import unq.dapp.supergol.model.League;
import unq.dapp.supergol.model.repositories.SqlRepository;

import static spark.Spark.staticFileLocation;
import static spark.SparkBase.port;

public class Routes {

  public static void main(String[] args) {
    System.out.println("Starting server...");

    port(8080);

    staticFileLocation("/public");


    new CRUDController<>(new SqlRepository<>(League.class))
      .registerRoutes("/leagues");
  }

}
