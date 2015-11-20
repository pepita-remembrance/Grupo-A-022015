package unq.dapp.supergol.model;

public class Player {
  private static final int SUCCESS_POINTS = 2;
  private static final int FAILURE_POINTS = 0;

  private RealWorldTeam realWorldTeam;

  public int scoreFor(Match match) {
    return match.goalsAgainst(realWorldTeam) == 0 ? SUCCESS_POINTS : FAILURE_POINTS;
  }

  public static Player goalkeeper(RealWorldTeam team) {
    return Player.ofTeam(team);
  }

  public static Player forward(RealWorldTeam team) {
    return Player.ofTeam(team);
  }

  private static Player ofTeam(RealWorldTeam team) {
    Player player = new Player();
    player.realWorldTeam = team;

    return player;
  }

  public RealWorldTeam getRealWorldTeam() {
    return realWorldTeam;
  }
}
