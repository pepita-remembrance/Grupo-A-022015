package unq.dapp.supergol.model;

import unq.dapp.supergol.serialization.JsonIgnore;
import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Match extends Persistable {
  private static final int TIE_SCORE = 1;
  private static final int WINNER_SCORE = 3;
  private static final int LOSER_SCORE = 0;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Stage stage;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Team homeTeam;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Team awayTeam;

  public static Match versus(Stage stage, Team homeTeam, Team awayTeam) {
    Match match = new Match();
    match.stage = stage;
    match.homeTeam = homeTeam;
    match.awayTeam = awayTeam;

    return match;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public int scoreFor(Team team) {
    if (isATie()) return TIE_SCORE;
    if (isTheWinner(team)) return WINNER_SCORE;
    return LOSER_SCORE;
  }

  private boolean isTheWinner(Team team) {
    return team.scoreFor(stage) > oppositeTeam(team).scoreFor(stage);
  }

  private Team oppositeTeam(Team team) {
    return team == homeTeam ? awayTeam : homeTeam;
  }

  private boolean isATie() {
    return homeTeam.scoreFor(stage) == awayTeam.scoreFor(stage);
  }

  public Stage getStage() {
    return stage;
  }
}
