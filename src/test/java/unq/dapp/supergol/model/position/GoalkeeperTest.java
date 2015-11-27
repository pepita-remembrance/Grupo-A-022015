package unq.dapp.supergol.model.position;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Match;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.RealWorldTeam;

import static org.junit.Assert.*;

public class GoalkeeperTest {
  private Player romero;
  private Match match;
  private RealWorldTeam bosniaAndHerzegovina;

  @Before
  public void setUp() throws Exception {
    RealWorldTeam argentina = RealWorldTeam.named("Argentina");
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
    match.stage.addGoals(Player.forward(bosniaAndHerzegovina), 1);
    assertEquals(0, romero.scoreFor(match));
  }
}
