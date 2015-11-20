package unq.dapp.supergol.model;

public enum Position {
  FORWARD {
    @Override
    public int scoreFor(Player player, Match match) {
      return match.goalsOf(player);
    }
  },

  MIDFIELDER {
    @Override
    public int scoreFor(Player player, Match match) {
      return FORWARD.scoreFor(player, match);
    }
  },

  DEFENDER {
    private static final int MULTIPLIER = 3;

    @Override
    public int scoreFor(Player player, Match match) {
      return MULTIPLIER * FORWARD.scoreFor(player, match);
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
  };

  public abstract int scoreFor(Player player, Match match);
}
