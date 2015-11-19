package unq.dapp.supergol.main;

import spark.template.handlebars.HandlebarsTemplateEngine;
import unq.dapp.supergol.controllers.HomeController;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import static spark.SparkBase.port;

public class Routes {

  public static void main(String[] args) {
    System.out.println("Starting server...");

    HomeController home = new HomeController();
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    port(8080);

    staticFileLocation("/public");

    get("/", home::show, engine);
    get("/index.html", (request, response) -> {
      response.redirect("/");
      return null;
    });
  }

}
