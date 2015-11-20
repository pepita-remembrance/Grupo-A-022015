package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Match;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.RealWorldTeam;

import static org.junit.Assert.assertEquals;

public class MidfielderTest {
  private Player diMaria;
  private Match match;

  @Before
  public void setUp() throws Exception {
    RealWorldTeam argentina = RealWorldTeam.named("Argentina");

    diMaria = Player.midfielder(argentina);
    match = Match.versus(argentina, RealWorldTeam.named("Switzerland"));
  }

  @Test
  public void whenNoGoalsWereMadeTheScoreIs0() {
    assertEquals(0, diMaria.scoreFor(match));
  }

  @Test
  public void whenGoalsWereMadeAPointIsScoredPerGoal() {
    match.addGoals(diMaria, 1);
    assertEquals(1, diMaria.scoreFor(match));
  }
}
