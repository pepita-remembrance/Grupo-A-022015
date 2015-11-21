package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;

public class TeamTest {

  private Team team;

  @Before
  public void setUp() throws Exception {
    team = new Team();
  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void ATeamCantHaveMoreThanOneGoalkeeper() {
    team.addPlayer(anyGoalkeeper());

    expectedException.expect(InvalidTeamFormationException.class);
    expectedException.expectMessage("This team already has 1 player with position Goalkeeper, no more can be added.");
    team.addPlayer(anyGoalkeeper());
  }

  private Player anyGoalkeeper() {
    return Player.goalkeeper(anyRealWorldTeam());
  }

  private RealWorldTeam anyRealWorldTeam() {
    return RealWorldTeam.named("Anyone");
  }
}
