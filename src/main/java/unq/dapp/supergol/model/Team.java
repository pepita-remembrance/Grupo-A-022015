package unq.dapp.supergol.model;

import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;
import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.toIntExact;

@Entity
public class Team extends Persistable {
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "team_id")
  private Collection<Player> players = new ArrayList<>();

  private String name;
  private String logoUrl;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public Collection<Player> getPlayers() {
    return players;
  }

  public Team addPlayer(Player player) {
    checkFormationStillValidAfterAdding(player);
    players.add(player);

    return this;
  }

  private void checkFormationStillValidAfterAdding(Player player) {
    if (currentCountOf(player.getPosition()) == player.getPosition().maxQuantityPerTeam()) {
      throw new InvalidTeamFormationException(player.getPosition());
    }
  }

  private int currentCountOf(Position position) {
    return toIntExact(players.stream()
      .filter(it -> it.getPosition() == position)
      .count());
  }

  public int scoreFor(Stage stage) {
    return players.stream().mapToInt(it -> it.scoreFor(stage)).sum();
  }

  public static Team named(String name) {
    Team team = new Team();
    team.setName(name);

    return team;
  }
}
