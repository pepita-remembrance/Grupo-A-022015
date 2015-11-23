package unq.dapp.supergol.model.exceptions;

public class UnexistentPlayerException extends DomainException {
  public UnexistentPlayerException(long id) {
    super(String.format("Player with id %d doesn't exist", id));
  }
}
