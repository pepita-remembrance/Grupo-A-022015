package unq.dapp.supergol.model;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;
import unq.dapp.supergol.model.repositories.Persistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Entity
public class Stage extends Persistable {
  @Convert(converter = LocalDateConverter.class)
  private LocalDate date;

  @ElementCollection
  @MapKeyJoinColumn(name = "player_id")
  private Map<Player, Integer> goals = new HashMap<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "stage")
  private List<Match> matches = new ArrayList<>();

  public static Stage ofDate(LocalDate date) {
    Stage stage = new Stage();
    stage.date = date;

    return stage;
  }

  public LocalDate getDate() {
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
