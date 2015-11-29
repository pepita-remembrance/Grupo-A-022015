package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Position;
import unq.dapp.supergol.model.Stage;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;
import static unq.dapp.supergol.helpers.DomainFactory.anyStage;

public class ForwardTest {
  private Player messi;
  private Stage stage;

  @Before
  public void setUp() {
    messi = anyPlayer(Position.FORWARD);
    stage = anyStage();
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, messi.scoreFor(stage));
  }

  @Test
  public void whenGoalsWereMadeAPointIsScoredPerGoal() {
    stage.addGoals(messi, 2);
    assertEquals(2, messi.scoreFor(stage));
  }
}
