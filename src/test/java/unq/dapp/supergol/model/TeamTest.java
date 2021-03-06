package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unq.dapp.supergol.model.exceptions.InvalidTeamFormationException;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.helpers.DomainFactory.anyPlayer;
import static unq.dapp.supergol.helpers.DomainFactory.anyStage;

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
    addPlayersToTeam(Position.DEFENDER, 3);

    expectInvalidTeamFormationException("This team already has 3 players with position Defender, no more can be added.");
    team.addPlayer(anyPlayer(Position.DEFENDER));
  }

  @Test
  public void cantHaveMoreThanFourMidfielders() {
    addPlayersToTeam(Position.MIDFIELDER, 4);

    expectInvalidTeamFormationException("This team already has 4 players with position Midfielder, no more can be added.");
    team.addPlayer(anyPlayer(Position.MIDFIELDER));
  }

  @Test
  public void cantHaveMoreThanThreeForwards() {
    addPlayersToTeam(Position.FORWARD, 3);

    expectInvalidTeamFormationException("This team already has 3 players with position Forward, no more can be added.");
    team.addPlayer(anyPlayer(Position.FORWARD));
  }

  @Test
  public void theScoreForAStageIsTheSumOfThePlayersScore() {
    Player diMaria = anyPlayer(Position.MIDFIELDER);
    Player rojo = anyPlayer(Position.DEFENDER);
    Player neymar = anyPlayer(Position.FORWARD);

    Stage stage = anyStage();
    stage.addGoals(diMaria, 2);
    stage.addGoals(rojo, 1);
    stage.addGoals(neymar, 2);
    stage.addGoals(anyPlayer(Position.DEFENDER), 3);

    Team team = new Team().addPlayer(diMaria).addPlayer(rojo).addPlayer(neymar);
    assertEquals(team.scoreFor(stage), 7); //2 diMaria + 3 rojo + 2 neymar
  }

  private void addPlayersToTeam(Position position, int quantity) {
    for (int i = 0; i < quantity; i++) {
      team.addPlayer(anyPlayer(position));
    }
  }

  private void expectInvalidTeamFormationException(String substring) {
    expectedException.expect(InvalidTeamFormationException.class);
    expectedException.expectMessage(substring);
  }
}
