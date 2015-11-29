package unq.dapp.supergol.model.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T extends Persistable> {
  Collection<T> all();

  Optional<T> findById(long id);

  Collection<T> filter(Predicate<T> comparator);

  Repository<T> add(T element);
}
