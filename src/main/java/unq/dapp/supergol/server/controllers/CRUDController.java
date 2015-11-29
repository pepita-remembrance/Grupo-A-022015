package unq.dapp.supergol.server.controllers;

import unq.dapp.supergol.model.repositories.Repository;
import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.server.serialization.JsonTransformer;

import static spark.Spark.get;

public class CRUDController<TEntity extends Persistable> {
  private final Repository<TEntity> repository;

  public CRUDController(Repository<TEntity> repository) {
    this.repository = repository;
  }

  public void registerRoutes(String baseUrl) {
    get(baseUrl, (request, response) -> {
      return repository.all();
    }, new JsonTransformer());
  }
}
