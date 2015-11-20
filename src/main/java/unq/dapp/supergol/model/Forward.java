package unq.dapp.supergol.model;

public class Forward implements Position {
  @Override
  public int scoreFor(Player player, Match match) {
    return match.goalsOf(player);
  }
}
