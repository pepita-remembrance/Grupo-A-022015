package unq.dapp.supergol.model.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Repository<T extends Entity> {
  private Collection<T> elements = new ArrayList<T>();

  public Collection<T> all() {
    return elements;
  }

  public Optional<T> findById(long id) {
    return elements.stream().filter(it -> it.getId() == id).findAny();
  }

  public Collection<T> filter(Predicate<T> comparator) {
    return elements.stream().filter(comparator).collect(Collectors.<T>toList());
  }

  public Repository<T> add(T element) {
    elements.add(element);
    return this;
  }
}
