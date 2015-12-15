package unq.dapp.supergol.server.controllers;

import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Stage;
import unq.dapp.supergol.model.StageImport;
import unq.dapp.supergol.model.repositories.Repository;

import static spark.Spark.post;

public class StageController extends CRUDController<Stage> {
  private final Repository<Player> playerRepository;

  public StageController(Class<Stage> clazz, Repository<Stage> repository, Repository<Player> playerRepository) {
    super(clazz, repository);
    this.playerRepository = playerRepository;
  }

  @Override
  public void registerRoutes(String baseUrl) {
    super.registerRoutes(baseUrl);

    post(baseUrl + "/:id/import", (request, response) -> {
      String csv = transformer.parse(CsvData.class, request.body()).data;
      Stage stage = getEntityFromRequest(request);

      new StageImport(playerRepository, csv, stage).execute();

      withTransaction(() -> repository.update(stage));

      response.status(200);

      return "{ \"status\": \"ok\" }";
    });
  }

  public class CsvData {
    private String data;
  }
}
