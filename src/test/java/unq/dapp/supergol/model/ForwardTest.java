package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForwardTest {
  private Player messi;
  private Match match;

  @Before
  public void setUp() throws Exception {
    RealWorldTeam argentina = RealWorldTeam.named("Argentina");

    messi = Player.forward(argentina);
    match = Match.versus(argentina, RealWorldTeam.named("Nigeria"));
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, messi.scoreFor(match));
  }

  @Test
  public void whenGoalsWereMadeAPointIsScoredPerGoal() {
    match.addGoals(messi, 2);
    assertEquals(2, messi.scoreFor(match));
  }
}