package unq.dapp.supergol.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class Stage {
  private Date date;
  private Collection<Match> matches;

  public static Stage ofDate(Date date, Match... matches) {
    Stage stage = new Stage();
    stage.date = date;
    stage.matches = Arrays.asList(matches);

    return stage;
  }

  public int scoreOf(Player player) {
    return matches.stream().mapToInt(player::scoreFor).sum();
  }

  public void addGoals(Player player, int goals) {

  }
}
