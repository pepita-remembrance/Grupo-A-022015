package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import unq.dapp.supergol.model.exceptions.NotEnoughTeamsException;
import unq.dapp.supergol.model.exceptions.TooManyTeamsException;

import static unq.dapp.supergol.helpers.DomainFactory.anyTeam;

public class LeagueTest {
  private League league;

  @Before
  public void setUp() {
    league = League.withAllowedTeams(2, 3, "any");
  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void aLeagueCantHaveLessTeamsThanTheMinimum() {
    league.addTeam(anyTeam());

    expectedException.expect(NotEnoughTeamsException.class);
    expectedException.expectMessage("This League has 1 teams, but the minimum is 2.");
    league.validateTeams();
  }

  @Test
  public void aLeagueCantHaveMoreTeamsThanTheMaximum() {
    league.addTeam(anyTeam());
    league.addTeam(anyTeam());
    league.addTeam(anyTeam());
    league.addTeam(anyTeam());

    expectedException.expect(TooManyTeamsException.class);
    expectedException.expectMessage("This League has 4 teams, but the maximum is 3.");

    league.validateTeams();
  }

  @Test
  public void aValidLeagueDoesntFailWhenValidating() {
    league.addTeam(anyTeam());
    league.addTeam(anyTeam());

    league.validateTeams();
  }
}
