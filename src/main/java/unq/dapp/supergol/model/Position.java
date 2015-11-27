package unq.dapp.supergol.model;

public enum Position {
  FORWARD {
    @Override
    public int scoreFor(Player player, Stage stage) {
      return stage.goalsOf(player);
    }

    @Override
    public int maxQuantityPerTeam() {
      return 3;
    }
  },

  MIDFIELDER {
    @Override
    public int scoreFor(Player player, Stage stage) {
      return FORWARD.scoreFor(player, stage);
    }

    @Override
    public int maxQuantityPerTeam() {
      return 4;
    }
  },

  DEFENDER {
    private static final int MULTIPLIER = 3;

    @Override
    public int scoreFor(Player player, Stage stage) {
      return MULTIPLIER * FORWARD.scoreFor(player, stage);
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
    public int scoreFor(Player player, Stage stage) {
      return stage.goalsOf(player) == 0
        ? SUCCESS_POINTS
        : FAILURE_POINTS;
    }

    @Override
    public int maxQuantityPerTeam() {
      return 1;
    }
  };

  public abstract int scoreFor(Player player, Stage stage);
  public abstract int maxQuantityPerTeam();
}
