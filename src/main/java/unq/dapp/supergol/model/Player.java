package unq.dapp.supergol.model;

import unq.dapp.supergol.model.repositories.Persistable;

public class Player extends Persistable {
  private RealWorldTeam realWorldTeam;
  private Position position;
  private int id;

  public static Player goalkeeper(RealWorldTeam team) {
    return Player.ofTeam(Position.GOALKEEPER, team);
  }

  public static Player forward(RealWorldTeam team) {
    return Player.ofTeam(Position.FORWARD, team);
  }

  public static Player midfielder(RealWorldTeam team) {
    return Player.ofTeam(Position.MIDFIELDER, team);
  }

  public static Player ofTeam(Position position, RealWorldTeam team) {
    Player player = new Player();
    player.position = position;
    player.realWorldTeam = team;

    return player;
  }

  public int scoreFor(Stage stage) {
    return position.scoreFor(this, stage);
  }

  public RealWorldTeam getRealWorldTeam() {
    return realWorldTeam;
  }

  public static Player defender(RealWorldTeam team) {
    return Player.ofTeam(Position.DEFENDER, team);
  }

  public Position getPosition() {
    return position;
  }
}
