package unq.dapp.supergol.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import unq.dapp.supergol.model.exceptions.NotEnoughTeamsException;
import unq.dapp.supergol.model.exceptions.TooManyTeamsException;
import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class League extends Persistable {
  private String name;
  private int minTeams;
  private int maxTeams;

  @OneToMany
  @Cascade(CascadeType.PERSIST)
  private Collection<Team> teams = new ArrayList<>();

  @OneToMany
  @Cascade(CascadeType.PERSIST)
  @JoinColumn(name = "league_id")
  private List<Stage> stages = new ArrayList<>();

  public static League withAllowedTeams(int minTeams, int maxTeams, String name) {
    League league = new League();
    league.minTeams = minTeams;
    league.maxTeams = maxTeams;
    league.name = name;

    return league;
  }

  public League addTeam(Team team) {
    this.teams.add(team);
    return this;
  }

  public League addStage(Stage stage) {
    this.stages.add(stage);
    return this;
  }

  public void validateTeams() {
    if (currentTeams() < minTeams) { throw new NotEnoughTeamsException(currentTeams(), minTeams); }
    if (currentTeams() > maxTeams) { throw new TooManyTeamsException(currentTeams(), maxTeams); }
  }

  private int currentTeams() {
    return this.teams.size();
  }

  public String getName() {
    return name;
  }

  public int getMinTeams() {
    return minTeams;
  }

  public int getMaxTeams() {
    return maxTeams;
  }

  public Collection<Team> getTeams() {
    return teams;
  }

  public List<Stage> getStages() {
    return stages;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int scoreFor(Team team) {
    return this.stages.stream().mapToInt(team::scoreFor).sum();
  }
}
