package unq.dapp.supergol.model.repositories;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T extends Persistable> {
  Collection<T> all();

  Optional<T> findById(long id);

  void add(T element);
}
