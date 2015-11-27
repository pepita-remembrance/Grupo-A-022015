package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.*;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;
import static unq.dapp.supergol.helpers.DomainFactory.anyStage;

public class MidfielderTest {
  private Player diMaria;
  private Stage stage;

  @Before
  public void setUp() throws Exception {
    diMaria = anyPlayer(Position.MIDFIELDER);
    stage = anyStage();
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, diMaria.scoreFor(stage));
  }

  @Test
  public void whenGoalsWereMadeAPointIsScoredPerGoal() {
    stage.addGoals(diMaria, 1);
    assertEquals(1, diMaria.scoreFor(stage));
  }
}
