package unq.dapp.supergol.server.controllers;

import unq.dapp.supergol.model.Stage;
import unq.dapp.supergol.model.StageImport;
import unq.dapp.supergol.model.Team;
import unq.dapp.supergol.model.repositories.Repository;

import static spark.Spark.*;

public class TeamController extends CRUDController<Team> {
  public TeamController(Class<Team> clazz, Repository<Team> repository) {
    super(clazz, repository);
  }

  @Override
  public void registerRoutes(String baseUrl) {
    super.registerRoutes(baseUrl);

    get(baseUrl + "/:id/results", (request, response) -> {
      Team team = getEntityFromRequest(request);

    });
  }
}
