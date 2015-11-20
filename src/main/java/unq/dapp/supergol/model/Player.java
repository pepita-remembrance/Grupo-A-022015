package unq.dapp.supergol.model;

public class Player {
  private RealWorldTeam realWorldTeam;
  private Position position;

  public int scoreFor(Match match) {
    return position.scoreFor(this, match);
  }

  public static Player goalkeeper(RealWorldTeam team) {
    return Player.ofTeam(new Goalkeeper(), team);
  }

  public static Player forward(RealWorldTeam team) {
    return Player.ofTeam(new Forward(), team);
  }

  private static Player ofTeam(Position position, RealWorldTeam team) {
    Player player = new Player();
    player.position = position;
    player.realWorldTeam = team;

    return player;
  }

  public RealWorldTeam getRealWorldTeam() {
    return realWorldTeam;
  }
}
