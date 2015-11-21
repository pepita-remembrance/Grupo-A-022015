package unq.dapp.supergol.model;

import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;

import java.util.ArrayList;
import java.util.Collection;

public class Team {
  public static final int MAX_GOALKEEPERS = 1;
  private Collection<Player> players = new ArrayList<>();

  public void addPlayer(Player player) {
    checkFormationStillValidAfterAdding(player);
    players.add(player);
  }

  private void checkFormationStillValidAfterAdding(Player player) {
      if (players.stream()
        .filter(it -> it.getPosition() == Position.GOALKEEPER)
        .count() == MAX_GOALKEEPERS) {
        throw new InvalidTeamFormationException(MAX_GOALKEEPERS, Position.GOALKEEPER);
      }
  }
}
