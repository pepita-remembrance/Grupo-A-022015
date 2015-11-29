package unq.dapp.supergol.model.persistence;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.*;
import unq.dapp.supergol.model.repositories.Persistable;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.utils.CollectionUtils.anyOne;

public class PersistenceTest extends BasePersistenceTest {

  private RealWorldTeam sanLorenzo;
  private Player ortigoza;
  private Team losVengadoresDeFlores;
  private Stage october30Stage;

  @Before
  public void setUp() {
    sanLorenzo = RealWorldTeam.named("San Lorenzo");

    ortigoza = Player.midfielder(sanLorenzo);
    ortigoza.setName("Nestor Ortigoza");

    losVengadoresDeFlores = new Team();
    losVengadoresDeFlores.setName("Los vengadores de Flores");
    losVengadoresDeFlores.setLogoUrl("http://foo.com/logo.png");
    losVengadoresDeFlores.addPlayer(ortigoza);

    october30Stage = Stage.ofDate(LocalDate.of(2015, Month.OCTOBER, 30));
  }

  @Test
  public void realWorldTeamsCanBePersisted() {
    RealWorldTeam sanLorenzoFromSQL = saveAndRetrieve(RealWorldTeam.class, sanLorenzo);

    assertEquals(sanLorenzo.getName(), sanLorenzoFromSQL.getName());
  }

  @Test
  public void playersCanBePersisted() {
    Player ortigozaFromSQL = saveAndRetrieve(Player.class, ortigoza);

    assertEquals(ortigoza.getName(), ortigozaFromSQL.getName());
    assertEquals(ortigoza.getPosition(), ortigozaFromSQL.getPosition());
    assertEquals(sanLorenzo.getName(), ortigozaFromSQL.getRealWorldTeam().getName());
  }

  @Test
  public void teamsCanBePersisted() {
    Team teamFromSQL = saveAndRetrieve(Team.class, losVengadoresDeFlores);

    assertEquals(losVengadoresDeFlores.getName(), teamFromSQL.getName());
    assertEquals(losVengadoresDeFlores.getLogoUrl(), teamFromSQL.getLogoUrl());
    assertEquals(ortigoza.getName(), anyOne(teamFromSQL.getPlayers()).getName());
  }

  @Test
  public void stagesCanBePersisted() {
    october30Stage.addGoals(ortigoza, 2);

    persist(ortigoza);
    Stage stageFromSQL = saveAndRetrieve(Stage.class, october30Stage);

    assertEquals(october30Stage.getDate(), stageFromSQL.getDate());
    assertEquals(2, stageFromSQL.goalsOf(ortigoza));
  }

  @Test
  public void matchesCanBePersisted() {
    Team losPibes = Team.named("Los pibes de Villa Luro");
    Match match = Match.versus(october30Stage, losVengadoresDeFlores, losPibes);

    Match matchFromSQL = saveAndRetrieve(Match.class, match);

    assertEquals(losVengadoresDeFlores.getName(), matchFromSQL.getHomeTeam().getName());
    assertEquals(losPibes.getName(), matchFromSQL.getAwayTeam().getName());
    assertEquals(october30Stage.getDate(), matchFromSQL.getStage().getDate());
  }

  @Test
  public void leaguesCanBePersisted() {
    League league = League.withAllowedTeams(2, 10, "Champion Liga")
      .addTeam(new Team()).addTeam(new Team())
      .addStage(Stage.ofDate(LocalDate.now()));

    League leagueFromSQL = saveAndRetrieve(League.class, league);

    assertEquals(league.getName(), leagueFromSQL.getName());
    assertEquals(league.getMinTeams(), leagueFromSQL.getMinTeams());
    assertEquals(league.getMaxTeams(), leagueFromSQL.getMaxTeams());
    assertEquals(2, leagueFromSQL.getTeams().size());
    assertEquals(1, leagueFromSQL.getStages().size());
  }

  private <T extends Persistable> T saveAndRetrieve(Class<T> clazz, T entity) {
    persist(entity);
    entityManager().getTransaction().commit();
    entityManager().clear();

    return find(clazz, entity.getId());
  }
}
