package unq.dapp.supergol.server.controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.HaltException;
import spark.Request;
import unq.dapp.supergol.model.exceptions.EntityNotFoundException;
import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.model.repositories.Repository;
import unq.dapp.supergol.server.dependencyInjection.WithJsonTransformer;
import unq.dapp.supergol.server.interceptors.WithAuthInterceptor;
import unq.dapp.supergol.server.interceptors.WithLoggerInterceptor;
import unq.dapp.supergol.server.serialization.ErrorMessage;
import unq.dapp.supergol.server.serialization.JsonTransformer;

import static spark.Spark.*;

public class CRUDController<TEntity extends Persistable>

  extends    BaseController

  implements WithGlobalEntityManager,
             TransactionalOps ,
             WithAuthInterceptor,
             WithLoggerInterceptor,
             WithJsonTransformer
{

  private final Class<TEntity> clazz;
  protected final Repository<TEntity> repository;
  protected final JsonTransformer transformer;

  public CRUDController(Class<TEntity> clazz, Repository<TEntity> repository) {
    this.transformer = responseTransformer();
    this.clazz = clazz;
    this.repository = repository;
  }

  public void registerRoutes(String baseUrl) {
    enableCORS("*", "*", "*");

    withInterceptors(
      jsonResponseType(),
      authenticationInterceptor(),
      loggingInterceptor()
    );

    get(
      baseUrl,
      (request, response) -> repository.all(),
      transformer
    );

    get(
      baseUrl + "/:id",
      (request, response) -> {
        return getEntityFromRequest(request);
      },
      transformer
    );

    post(baseUrl, (request, response) -> {
      TEntity entity = transformer.parse(clazz, request.body());

      withTransaction(() -> repository.update(entity));

      response.status(201);
      return String.format("{ \"id\": \"%s\" }", entity.getId());
    });

    put(baseUrl + "/:id", (request, response) -> {
      TEntity entity = transformer.parse(clazz, request.body());

      withTransaction(() -> repository.update(entity));

      response.status(201);
      return String.format("{ \"id\": \"%s\" }", entity.getId());
    });


    after(baseUrl, (request, response) -> commitTransaction());

    exception(EntityNotFoundException.class, (exception, request, response) -> {
      response.status(404);
      response.body(transformer.render(new ErrorMessage(exception)));
    });

    exception(HaltException.class, (exception, request, response) -> {
      response.status(((HaltException)exception).getStatusCode());
      response.body(transformer.render(new ErrorMessage(exception)));
    });
  }

  protected TEntity getEntityFromRequest(Request request) {
    long id = Long.parseLong(request.params("id"));
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
  }
}
