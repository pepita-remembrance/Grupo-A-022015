package unq.dapp.supergol.model;

import unq.dapp.supergol.model.exceptions.UnexistentPlayerException;
import unq.dapp.supergol.model.repositories.Repository;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.toIntExact;
import static unq.dapp.supergol.utils.ListUtils.tail;

public class StageImport {
  private static final int PLAYER_POSITION = 0;
  private static final int GOALS_POSITION = 2;

  private final Repository<Player> playerRepo;
  private final String csv;
  private final Stage stage;
  private long id;

  public StageImport(Repository<Player> playerRepo, String csv, Stage stage) {
    this.playerRepo = playerRepo;
    this.csv = csv;
    this.stage = stage;
  }

  public void execute() {
    List<String> lines = Arrays.asList(csv.split("\n"));

    id = Long.parseLong(lines.get(0));

    tail(lines).forEach(line -> {
      String[] row = line.split(",");
      stage.addGoals(player(row), goals(row));
    });
  }

  private int goals(String[] row) {
    return toIntExact(getLong(row, GOALS_POSITION));
  }

  private Player player(String[] row) {
    long id = getLong(row, PLAYER_POSITION);

    return playerRepo
      .findById(id)
      .orElseThrow(() -> new UnexistentPlayerException(id));
  }

  private long getLong(String[] row, int position) {
    return Long.parseLong(row[position]);
  }

  public long getId() {
    return id;
  }
}
