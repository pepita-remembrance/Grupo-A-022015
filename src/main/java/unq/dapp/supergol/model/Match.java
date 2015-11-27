package unq.dapp.supergol.model;

import java.util.Map;
import java.util.function.Predicate;

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

  public int goalsOf(RealWorldTeam realWorldTeam) {
    return goalsOf(player -> player.getRealWorldTeam() == realWorldTeam);
  }

  public int goalsOf(Player player) {
    return goalsOf(goalAuthor -> goalAuthor == player);
  }

  public int goalsAgainst(RealWorldTeam realWorldTeam) {
    return realWorldTeam == homeTeam
      ? goalsOf(awayTeam)
      : goalsOf(homeTeam);
  }

  private int goalsOf(Predicate<Player> predicate) {
    return stage.goals.entrySet().stream()
      .filter(pair -> predicate.test(pair.getKey()))
      .mapToInt(Map.Entry::getValue)
      .sum();
  }
}
