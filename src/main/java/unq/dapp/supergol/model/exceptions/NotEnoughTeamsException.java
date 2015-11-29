package unq.dapp.supergol.model.exceptions;

public class NotEnoughTeamsException extends DomainException {
  public NotEnoughTeamsException(int currentTeams, int minTeams) {
    super(String.format("This League has %d teams, but the minimum is %d.", currentTeams, minTeams));
  }
}
