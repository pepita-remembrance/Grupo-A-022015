package unq.dapp.supergol.model;

import java.util.*;
import java.util.function.Predicate;

public class Stage {
  private Date date;
  public Map<Player, Integer> goals = new HashMap<>();

  public static Stage ofDate(Date date) {
    Stage stage = new Stage();
    stage.date = date;

    return stage;
  }

  public void addGoals(Player player, Integer quantity) {
    goals.put(player, quantity);
  }

  public int goalsOf(Player player) {
    return goalsOf(goalAuthor -> goalAuthor == player);
  }

  private int goalsOf(Predicate<Player> predicate) {
    return goals.entrySet().stream()
      .filter(pair -> predicate.test(pair.getKey()))
      .mapToInt(Map.Entry::getValue)
      .sum();
  }
}
