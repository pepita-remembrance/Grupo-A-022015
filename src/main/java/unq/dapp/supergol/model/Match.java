package unq.dapp.supergol.model;

public class Match {
  private RealWorldTeam homeTeam;
  private RealWorldTeam awayTeam;
  public Stage stage;

  public static Match versus(RealWorldTeam homeTeam, RealWorldTeam awayTeam) {
    Match match = new Match();
    match.homeTeam = homeTeam;
    match.awayTeam = awayTeam;

    match.stage = new Stage();

    return match;
  }
}
