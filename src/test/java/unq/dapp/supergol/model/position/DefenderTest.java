package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Position;
import unq.dapp.supergol.model.Stage;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;

public class DefenderTest {
  private Player rojo;
  private Stage stage;

  @Before
  public void setUp() throws Exception {
    rojo = anyPlayer(Position.DEFENDER);
    stage = new Stage();
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, rojo.scoreFor(stage));
  }

  @Test
  public void whenGoalsWereMade3PointAreScoredPerGoal() {
    stage.addGoals(rojo, 1);
    assertEquals(3, rojo.scoreFor(stage));
  }
}
