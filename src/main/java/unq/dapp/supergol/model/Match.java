package unq.dapp.supergol.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Match {
  private RealWorldTeam homeTeam;
  private RealWorldTeam awayTeam;

  private Map<Player, Integer> goals = new HashMap<>();

  public static Match versus(RealWorldTeam homeTeam, RealWorldTeam awayTeam) {
    Match match = new Match();
    match.homeTeam = homeTeam;
    match.awayTeam = awayTeam;

    return match;
  }

  public void addGoals(Player player, Integer quantity) {
    if (isInvolvedInTheMatch(player)) {
      this.goals.put(player, quantity);
    }
  }

  private boolean isInvolvedInTheMatch(Player player) {
    return homeTeam.includes(player) || awayTeam.includes(player);
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
    return goals.entrySet().stream()
      .filter(pair -> predicate.test(pair.getKey()))
      .mapToInt(Map.Entry::getValue)
      .sum();
  }
}
