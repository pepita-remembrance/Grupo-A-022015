package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;
import static unq.dapp.supergol.helpers.DomainFactory.anyStage;

public class MatchTest {

  private Player playerOfFedeTeam;
  private Team fedeTeam;
  private Team ariTeam;
  private Match match;
  private Stage stage;

  @Before
  public void setUp() throws Exception {
    playerOfFedeTeam = anyPlayer(Position.MIDFIELDER);

    fedeTeam = new Team().addPlayer(playerOfFedeTeam);
    ariTeam = new Team().addPlayer(anyPlayer(Position.FORWARD));

    stage = anyStage();
    match = Match.versus(stage, fedeTeam, ariTeam);
  }

  @Test
  public void whenTeamsScoreIsTheSameEachOneGetsAPoint() {
    assertEquals(1, match.scoreFor(fedeTeam));
    assertEquals(1, match.scoreFor(ariTeam));
  }

  @Test
  public void whenTeamsScoreIsntTheSameTheWinnerTeamGets3Points() {
    stage.addGoals(playerOfFedeTeam, 2);
    assertEquals(3, match.scoreFor(fedeTeam));
  }

  @Test
  public void whenTeamsScoreIsntTheSameTheLooserTeamGetsNoPoints() {
    stage.addGoals(playerOfFedeTeam, 2);
    assertEquals(0, match.scoreFor(ariTeam));
  }
}
