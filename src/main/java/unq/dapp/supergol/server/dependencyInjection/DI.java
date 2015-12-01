package unq.dapp.supergol.server.dependencyInjection;

import unq.dapp.supergol.model.repositories.Persistable;
import unq.dapp.supergol.model.repositories.Repository;

public interface DI {
  <T extends Persistable> Repository<T> getRepository(Class<T> clazz);
}
