package unq.dapp.supergol.model;

import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Player extends Persistable {
  @ManyToOne(cascade = CascadeType.PERSIST)
  private RealWorldTeam realWorldTeam;

  private Position position;
  private String name;

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

  public static Player defender(RealWorldTeam team) {
    return Player.ofTeam(Position.DEFENDER, team);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int scoreFor(Stage stage) {
    return position.scoreFor(this, stage);
  }

  public RealWorldTeam getRealWorldTeam() {
    return realWorldTeam;
  }

  public Position getPosition() {
    return position;
  }
}
