package unq.dapp.supergol.model;

import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.toIntExact;

public class Team {
  private Collection<Player> players = new ArrayList<>();

  public Team addPlayer(Player player) {
    checkFormationStillValidAfterAdding(player);
    players.add(player);

    return this;
  }

  private void checkFormationStillValidAfterAdding(Player player) {
    if (currentCountOf(player.getPosition()) == player.getPosition().maxQuantityPerTeam()) {
      throw new InvalidTeamFormationException(player.getPosition());
    }
  }

  private int currentCountOf(Position position) {
    return toIntExact(players.stream()
      .filter(it -> it.getPosition() == position)
      .count());
  }

  public int scoreFor(Match match) {
    return players.stream().mapToInt(it -> it.scoreFor(match)).sum();
  }
}
