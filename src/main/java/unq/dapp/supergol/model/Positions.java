package unq.dapp.supergol.model;

public enum Positions implements Position {
  FORWARD {
    @Override
    public int scoreFor(Player player, Match match) {
      return match.goalsOf(player);
    }
  },

  GOALKEEPER {
    private static final int SUCCESS_POINTS = 2;
    private static final int FAILURE_POINTS = 0;

    @Override
    public int scoreFor(Player player, Match match) {
      return match.goalsAgainst(player.getRealWorldTeam()) == 0
        ? SUCCESS_POINTS
        : FAILURE_POINTS;
    }
  }
}
