package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefenderTest {
  private Player rojo;
  private Match match;

  @Before
  public void setUp() throws Exception {
    RealWorldTeam argentina = RealWorldTeam.named("Argentina");

    rojo = Player.defender(argentina);
    match = Match.versus(argentina, RealWorldTeam.named("Switzerland"));
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, rojo.scoreFor(match));
  }

  @Test
  public void whenGoalsWereMade3PointAreScoredPerGoal() {
    match.addGoals(rojo, 1);
    assertEquals(3, rojo.scoreFor(match));
  }
}