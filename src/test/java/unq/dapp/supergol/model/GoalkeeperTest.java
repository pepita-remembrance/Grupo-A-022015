package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoalkeeperTest {
  private Player romero;
  private Match match;
  private RealWorldTeam argentina;
  private RealWorldTeam bosniaAndHerzegovina;

  @Before
  public void setUp() throws Exception {
    argentina = RealWorldTeam.named("Argentina");
    bosniaAndHerzegovina = RealWorldTeam.named("Bosnia & Herzegovina");

    romero = Player.goalkeeper(argentina);
    match = Match.versus(argentina, bosniaAndHerzegovina);
  }

  @Test
  public void whenNoGoalsWereReceivedTheScoreIs2() {
    assertEquals(2, romero.scoreFor(match));
  }

  @Test
  public void whenNoGoalsWereReceivedTheScoreIs0() {
    match.addGoals(Player.forward(bosniaAndHerzegovina), 1);
    assertEquals(0, romero.scoreFor(match));
  }
}
