package unq.dapp.supergol.model;

import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyJoinColumn;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Entity
public class Stage extends Persistable {
  private Date date;

  @ElementCollection
  @MapKeyJoinColumn(name = "player_id")
  private Map<Player, Integer> goals = new HashMap<>();

  public static Stage ofDate(Date date) {
    Stage stage = new Stage();
    stage.date = date;

    return stage;
  }

  public Date getDate() {
    return date;
  }

  public void addGoals(Player player, Integer quantity) {
    goals.put(player, quantity);
  }

  public int goalsOf(Player player) {
    return goalsOf(goalAuthor -> goalAuthor.equals(player));
  }

  private int goalsOf(Predicate<Player> predicate) {
    return goals.entrySet().stream()
      .filter(pair -> predicate.test(pair.getKey()))
      .mapToInt(Map.Entry::getValue)
      .sum();
  }
}
