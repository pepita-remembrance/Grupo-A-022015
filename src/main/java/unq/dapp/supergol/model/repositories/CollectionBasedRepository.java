package unq.dapp.supergol.model.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionBasedRepository<T extends Persistable> implements Repository<T> {
  private Collection<T> elements = new ArrayList<T>();

  @Override
  public Collection<T> all() {
    return elements;
  }

  @Override
  public Optional<T> findById(long id) {
    return elements.stream().filter(it -> it.getId() == id).findAny();
  }

  @Override
  public Collection<T> filter(Predicate<T> comparator) {
    return elements.stream().filter(comparator).collect(Collectors.<T>toList());
  }

  @Override
  public Repository<T> add(T element) {
    elements.add(element);
    return this;
  }
}
