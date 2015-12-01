package unq.dapp.supergol.server.dependencyInjection;

import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.model.repositories.Repository;
import unq.dapp.supergol.model.repositories.SqlRepository;

public interface WithProductionDependencies extends DI {
  default <T extends Persistable> Repository<T> getRepository(Class<T> clazz) {
    return new SqlRepository<>(clazz);
  }
}
