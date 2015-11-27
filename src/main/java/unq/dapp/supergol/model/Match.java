package unq.dapp.supergol.model;

public class Match {
  private static final int TIE_SCORE = 1;
  private static final int WINNER_SCORE = 3;
  private static final int LOSER_SCORE = 0;

  private Stage stage;
  private Team homeTeam;
  private Team awayTeam;

  public static Match versus(Stage stage, Team homeTeam, Team awayTeam) {
    Match match = new Match();
    match.stage = stage;
    match.homeTeam = homeTeam;
    match.awayTeam = awayTeam;

    return match;
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
}
