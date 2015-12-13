package unq.dapp.supergol.server.controllers;

import spark.Filter;
import spark.Spark;
import unq.dapp.supergol.server.dependencyInjection.WithResponseTransformer;

import java.util.Arrays;

public abstract class BaseController implements WithResponseTransformer {

  protected void withInterceptors(Filter ...filters){
    Arrays.asList(filters).forEach(Spark::before);
  }

}
