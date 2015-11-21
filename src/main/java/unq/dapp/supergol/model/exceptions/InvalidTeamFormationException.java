package unq.dapp.supergol.model.exceptions;

import org.apache.commons.lang3.text.WordUtils;
import unq.dapp.supergol.model.Position;

public class InvalidTeamFormationException extends DomainException {
  public InvalidTeamFormationException(Position position) {
    super(makeErrorMessage(position));
  }

  private static String makeErrorMessage(Position position) {
    return String.format(
      "This team already has %d player%s with position %s, no more can be added.",
      position.maxQuantityPerTeam(),
      position.maxQuantityPerTeam() == 1 ? "" : "s",
      WordUtils.capitalizeFully(position.toString())
    );
  }
}
