package unq.dapp.supergol.server.controllers;

import spark.Filter;
import spark.Spark;
import unq.dapp.supergol.server.dependencyInjection.WithResponseTransformer;

import java.util.Arrays;
import static spark.Spark.*;

public abstract class BaseController implements WithResponseTransformer {

  protected void withInterceptors(Filter ...filters){
    Arrays.asList(filters).forEach(Spark::before);
  }

  protected void enableCORS(final String origin, final String methods, final String headers) {
    before((request,response)-> {
      response.raw().setHeader("Access-Control-Allow-Origin", "*");
      response.raw().setHeader("Access-Control-Request-Method", "*");
      response.raw().setHeader("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
      response.raw().setHeader("Access-Control-Allow-Methods",  "POST, GET, OPTIONS, PUT");
    });

    options("/*", (request,response)->{
//      response.raw().setHeader("Access-Control-Allow-Origin", "*");
//      response.raw().setHeader("Access-Control-Request-Method", "*");
//      response.raw().setHeader("Access-Control-Allow-Headers",  "*");
//      response.raw().setHeader("Access-Control-Allow-Methods",  "POST, GET, OPTIONS, PUT");
      return response;
    });
  }
}
