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
    team.addPlayer(anyPlayer(Position.GOALKEEPER));

    expectInvalidTeamFormationException("This team already has 1 player with position Goalkeeper, no more can be added.");
    team.addPlayer(anyPlayer(Position.GOALKEEPER));
  }

  @Test
  public void ATeamCantHaveMoreThanThreeDefenders() {
    for (int i = 0; i < 3; i++) {
      team.addPlayer(anyPlayer(Position.DEFENDER));
    }

    expectInvalidTeamFormationException("This team already has 3 players with position Defender, no more can be added.");
    team.addPlayer(anyPlayer(Position.DEFENDER));
  }

  @Test
  public void ATeamCantHaveMoreThanFourMidfielders() {
    for (int i = 0; i < 4; i++) {
      team.addPlayer(anyPlayer(Position.MIDFIELDER));
    }

    expectInvalidTeamFormationException("This team already has 4 players with position Midfielder, no more can be added.");
    team.addPlayer(anyPlayer(Position.MIDFIELDER));
  }

  @Test
  public void ATeamCantHaveMoreThanThreeForwards() {
    for (int i = 0; i < 3; i++) {
      team.addPlayer(anyPlayer(Position.FORWARD));
    }

    expectInvalidTeamFormationException("This team already has 3 players with position Forward, no more can be added.");
    team.addPlayer(anyPlayer(Position.FORWARD));
  }


  private void expectInvalidTeamFormationException(String substring) {
    expectedException.expect(InvalidTeamFormationException.class);
    expectedException.expectMessage(substring);
  }

  private Player anyPlayer(Position position) {
    return Player.ofTeam(position, anyRealWorldTeam());
  }

  private RealWorldTeam anyRealWorldTeam() {
    return RealWorldTeam.named("Anyone");
  }
}
