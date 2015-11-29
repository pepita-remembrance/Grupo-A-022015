package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.exceptions.UnexistentPlayerException;
import unq.dapp.supergol.model.repositories.Repository;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyRealWorldTeam;

public class StageImportTest {
  private Player milito;
  private Player mancuello;
  private Stage stage;
  private Repository<Player> repo;

  @Before
  public void setUp() {
    repo = new Repository<>();

    RealWorldTeam racing = anyRealWorldTeam();
    milito = Player.forward(racing);
    saveWithId(milito, 1);

    RealWorldTeam independiente = anyRealWorldTeam();
    mancuello = Player.midfielder(independiente);
    saveWithId(mancuello, 2);

    stage = Stage.ofDate(
      Date.valueOf(LocalDate.of(2015, 10, 25))
    );
  }

  @Test(expected = UnexistentPlayerException.class)
  public void theImportFailsIfAPlayerDoesntExist() {
    new StageImport(repo, "2\n89,Midfielder,3\n1.Forward,3", stage).execute();
  }

  @Test
  public void theImportAddsTheGoalsToTheStage() {
    new StageImport(repo, "3\n1,Forward,3", stage).execute();

    assertEquals(3, stage.goalsOf(milito));
  }

  @Test
  public void theImportAddsTheGoalsOfAllThePlayers() {
    new StageImport(repo, "4\n1,Forward,3\n2,Midfielder,1", stage).execute();

    assertEquals(3, stage.goalsOf(milito));
    assertEquals(1, stage.goalsOf(mancuello));
  }

  @Test
  public void whenExecutedTheCodeIsSet() {
    StageImport stageImport = new StageImport(repo, "28\n1,Forward,3\n2,Midfielder,1", stage);
    stageImport.execute();

    assertEquals(28, stageImport.getCode());
  }

  private void saveWithId(Player player, int id) {
    player.setId(id);
    repo.add(player);
  }
}
