package unq.dapp.supergol.server.controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import unq.dapp.supergol.model.exceptions.EntityNotFoundException;
import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.model.repositories.Repository;
import unq.dapp.supergol.server.serialization.ErrorMessage;
import unq.dapp.supergol.server.serialization.JsonTransformer;

import static spark.Spark.*;

public class CRUDController<TEntity extends Persistable> implements WithGlobalEntityManager, TransactionalOps {
  private final Class<TEntity> clazz;
  private final Repository<TEntity> repository;
  private final JsonTransformer transformer;

  public CRUDController(Class<TEntity> clazz, Repository<TEntity> repository) {
    this.clazz = clazz;
    this.repository = repository;
    this.transformer = new JsonTransformer();
  }

  public void registerRoutes(String baseUrl) {
    before(baseUrl, (request, response) -> response.type("application/json"));

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

    post(baseUrl, (request, response) -> {
      TEntity entity = transformer.parse(clazz, request.body());

      withTransaction(() -> repository.add(entity));

      response.status(201);
      return String.format("{ \"id\": \"%s\" }", entity.getId());
    });

    after(baseUrl, (request, response) -> commitTransaction());

    exception(EntityNotFoundException.class, (exception, request, response) -> {
      response.status(404);
      response.body(transformer.render(new ErrorMessage(exception)));
    });
  }
}
