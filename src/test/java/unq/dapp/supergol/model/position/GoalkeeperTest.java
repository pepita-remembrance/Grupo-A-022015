package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Position;
import unq.dapp.supergol.model.Stage;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;
import static unq.dapp.supergol.helpers.DomainFactory.anyStage;

public class GoalkeeperTest {
  private Player romero;
  private Stage stage;

  @Before
  public void setUp() {
    romero = anyPlayer(Position.GOALKEEPER);
    stage = anyStage();
  }

  @Test
  public void whenNoGoalsWereReceivedTheScoreIs2() {
    assertEquals(2, romero.scoreFor(stage));
  }

  @Test
  public void whenNoGoalsWereReceivedTheScoreIs0() {
    stage.addGoals(romero, 1);
    assertEquals(0, romero.scoreFor(stage));
  }
}
