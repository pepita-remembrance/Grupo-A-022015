package unq.dapp.supergol.model;

import unq.dapp.supergol.model.exceptions.NotEnoughTeamsException;
import unq.dapp.supergol.model.exceptions.TooManyTeamsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class League {
  private String name;
  private int minTeams;
  private int maxTeams;
  private Collection<Team> teams = new ArrayList<>();
  private List<Stage> stages;

  public static League withAllowedTeams(int minTeams, int maxTeams) {
    League league = new League();
    league.minTeams = minTeams;
    league.maxTeams = maxTeams;

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
}
