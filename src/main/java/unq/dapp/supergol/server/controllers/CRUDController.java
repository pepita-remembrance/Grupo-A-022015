package unq.dapp.supergol.server.controllers;

import unq.dapp.supergol.model.exceptions.EntityNotFoundException;
import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.model.repositories.Repository;
import unq.dapp.supergol.server.serialization.ErrorMessage;
import unq.dapp.supergol.server.serialization.JsonTransformer;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;

public class CRUDController<TEntity extends Persistable> {
  private final Class<TEntity> clazz;
  private final Repository<TEntity> repository;
  private final JsonTransformer transformer;

  public CRUDController(Class<TEntity> clazz, Repository<TEntity> repository) {
    this.clazz = clazz;
    this.repository = repository;
    this.transformer = new JsonTransformer();
  }

  public void registerRoutes(String baseUrl) {
    before((request, response) -> response.type("application/json"));

    get(
      baseUrl,
      (request, response) -> repository.all(),
      transformer
    );

    get(
      baseUrl + "/:id",
      (request, response) -> {
        long id = Long.parseLong(request.params("id"));
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
      },
      transformer
    );

    exception(EntityNotFoundException.class, (exception, request, response) -> {
      response.status(404);
      response.body(transformer.render(new ErrorMessage(exception)));
    });
  }
}
