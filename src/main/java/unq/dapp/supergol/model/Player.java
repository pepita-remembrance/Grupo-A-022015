package unq.dapp.supergol.model;

import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.Entity;

@Entity
public class Player extends Persistable {
  private String realWorldTeam;
  private Position position;
  private String name;

  public static Player goalkeeper(String team) {
    return Player.ofTeam(Position.GOALKEEPER, team);
  }

  public static Player forward(String team) {
    return Player.ofTeam(Position.FORWARD, team);
  }

  public static Player midfielder(String team) {
    return Player.ofTeam(Position.MIDFIELDER, team);
  }

  public static Player ofTeam(Position position, String team) {
    Player player = new Player();
    player.position = position;
    player.realWorldTeam = team;

    return player;
  }

  public static Player defender(String team) {
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

  public String getRealWorldTeam() {
    return realWorldTeam;
  }

  public Position getPosition() {
    return position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Player player = (Player) o;

    return getId() != 0 && getId() == player.getId();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(getId());
  }
}
