package unq.dapp.supergol.model;

public enum Position {
  FORWARD {
    @Override
    public int scoreFor(Player player, Match match) {
      return match.goalsOf(player);
    }

    @Override
    public int maxQuantityPerTeam() {
      return 0;
    }
  },

  MIDFIELDER {
    @Override
    public int scoreFor(Player player, Match match) {
      return FORWARD.scoreFor(player, match);
    }

    @Override
    public int maxQuantityPerTeam() {
      return 0;
    }
  },

  DEFENDER {
    private static final int MULTIPLIER = 3;

    @Override
    public int scoreFor(Player player, Match match) {
      return MULTIPLIER * FORWARD.scoreFor(player, match);
    }

    @Override
    public int maxQuantityPerTeam() {
      return 3;
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

    @Override
    public int maxQuantityPerTeam() {
      return 1;
    }
  };

  public abstract int scoreFor(Player player, Match match);
  public abstract int maxQuantityPerTeam();
}
