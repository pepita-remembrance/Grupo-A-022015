package unq.dapp.supergol.model;

import java.util.HashMap;
import java.util.Map;

public class Match {
  private RealWorldTeam homeTeam;
  private RealWorldTeam awayTeam;

  private Map<Player, Integer> goals = new HashMap<>();

  public void addGoals(Player player, Integer quantity) {
    this.goals.put(player, quantity);
  }

  public static Match versus(RealWorldTeam homeTeam, RealWorldTeam awayTeam) {
    Match match = new Match();
    match.homeTeam = homeTeam;
    match.awayTeam = awayTeam;

    return match;
  }

  public int goalsOf(RealWorldTeam realWorldTeam) {
    return goals.entrySet().stream()
      .filter(pair -> pair.getKey().getRealWorldTeam() == realWorldTeam)
      .mapToInt(Map.Entry::getValue)
      .sum();
  }

  public int goalsAgainst(RealWorldTeam realWorldTeam) {
    return realWorldTeam == homeTeam
      ? goalsOf(awayTeam)
      : goalsOf(homeTeam);
  }
}
