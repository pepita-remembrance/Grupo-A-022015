package unq.dapp.supergol.model.exceptions;

public class EntityNotFoundException extends DomainException {
  public EntityNotFoundException(long id) {
    super(String.format("Entity with id %d not found", id));
  }
}
