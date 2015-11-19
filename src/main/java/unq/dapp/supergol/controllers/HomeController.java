package unq.dapp.supergol.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

  public ModelAndView show(Request request, Response response) {
    return new ModelAndView(null, "home.hbs");
  }

}
