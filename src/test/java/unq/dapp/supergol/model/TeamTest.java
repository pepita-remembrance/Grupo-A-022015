package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;

import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;

public class TeamTest {

  private Team team;

  @Before
  public void setUp() throws Exception {
    team = new Team();
  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void cantHaveMoreThanOneGoalkeeper() {
    team.addPlayer(anyPlayer(Position.GOALKEEPER));

    expectInvalidTeamFormationException("This team already has 1 player with position Goalkeeper, no more can be added.");
    team.addPlayer(anyPlayer(Position.GOALKEEPER));
  }

  @Test
  public void cantHaveMoreThanThreeDefenders() {
    teamWithMany(Position.DEFENDER, 3);

    expectInvalidTeamFormationException("This team already has 3 players with position Defender, no more can be added.");
    team.addPlayer(anyPlayer(Position.DEFENDER));
  }

  @Test
  public void cantHaveMoreThanFourMidfielders() {
    teamWithMany(Position.MIDFIELDER, 4);

    expectInvalidTeamFormationException("This team already has 4 players with position Midfielder, no more can be added.");
    team.addPlayer(anyPlayer(Position.MIDFIELDER));
  }

  @Test
  public void cantHaveMoreThanThreeForwards() {
    teamWithMany(Position.FORWARD, 3);

    expectInvalidTeamFormationException("This team already has 3 players with position Forward, no more can be added.");
    team.addPlayer(anyPlayer(Position.FORWARD));
  }

  private void teamWithMany(Position position, int quantity) {
    for (int i = 0; i < quantity; i++) {
      team.addPlayer(anyPlayer(position));
    }
  }

  private void expectInvalidTeamFormationException(String substring) {
    expectedException.expect(InvalidTeamFormationException.class);
    expectedException.expectMessage(substring);
  }
}
