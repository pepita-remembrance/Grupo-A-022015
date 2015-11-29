package unq.dapp.supergol.model.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
  public void add(T element) {
    elements.add(element);
  }
}
