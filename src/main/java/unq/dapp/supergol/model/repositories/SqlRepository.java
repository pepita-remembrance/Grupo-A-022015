package unq.dapp.supergol.model.repositories;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.Collection;
import java.util.Optional;

public class SqlRepository<TEntity extends Persistable> implements Repository<TEntity>, WithGlobalEntityManager, EntityManagerOps {
  private final Class<TEntity> clazz;

  public SqlRepository(Class<TEntity> clazz) {
    this.clazz = clazz;
  }

  @Override
  public Collection<TEntity> all() {
    return createQuery(String.format("from %s", clazz.getName()), clazz).getResultList();
  }

  @Override
  public Optional<TEntity> findById(long id) {
    return Optional.ofNullable(find(clazz, id));
  }

  @Override
  public void add(TEntity element) {
    persist(element);
  }

  @Override
  public void update(TEntity element) {
    merge(element);
  }
}
