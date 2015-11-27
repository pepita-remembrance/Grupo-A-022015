package unq.dapp.supergol.model.exceptions;

public class TooManyTeamsException extends DomainException {
  public TooManyTeamsException(int currentTeams, int maxTeams) {
    super(String.format("This League has %d teams, but the maximum is %d.", currentTeams, maxTeams));
  }
}
