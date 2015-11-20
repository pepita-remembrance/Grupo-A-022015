package unq.dapp.supergol.model;

public class Player {
  private RealWorldTeam realWorldTeam;
  private Position position;

  public static Player goalkeeper(RealWorldTeam team) {
    return Player.ofTeam(Positions.GOALKEEPER, team);
  }

  public static Player forward(RealWorldTeam team) {
    return Player.ofTeam(Positions.FORWARD, team);
  }

  public static Player midfielder(RealWorldTeam team) {
    return Player.ofTeam(Positions.MIDFIELDER, team);
  }

  private static Player ofTeam(Position position, RealWorldTeam team) {
    Player player = new Player();
    player.position = position;
    player.realWorldTeam = team;

    return player;
  }

  public int scoreFor(Match match) {
    return position.scoreFor(this, match);
  }

  public RealWorldTeam getRealWorldTeam() {
    return realWorldTeam;
  }

  public static Player defender(RealWorldTeam team) {
    return Player.ofTeam(Positions.DEFENDER, team);
  }
}
