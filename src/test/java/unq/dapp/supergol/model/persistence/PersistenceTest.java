package unq.dapp.supergol.model.persistence;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static unq.dapp.supergol.utils.CollectionUtils.anyOne;

public class PersistenceTest extends BasePersistenceTest {

  private RealWorldTeam sanLorenzo;
  private Player ortigoza;

  @Before
  public void setUp() throws Exception {
    sanLorenzo = RealWorldTeam.named("San Lorenzo");

    ortigoza = Player.midfielder(sanLorenzo);
    ortigoza.setName("Nestor Ortigoza");
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
    Team losVengadoresDeFlores = new Team();
    losVengadoresDeFlores.setName("Los vengadores de Flores");
    losVengadoresDeFlores.setLogoUrl("http://foo.com/logo.png");
    losVengadoresDeFlores.addPlayer(ortigoza);

    Team teamFromSQL = saveAndRetrieve(Team.class, losVengadoresDeFlores);

    assertEquals(losVengadoresDeFlores.getName(), teamFromSQL.getName());
    assertEquals(losVengadoresDeFlores.getLogoUrl(), teamFromSQL.getLogoUrl());
    assertEquals(ortigoza.getName(), anyOne(teamFromSQL.getPlayers()).getName());
  }

  @Test
  public void stagesCanBePersisted() {
    Stage stage = Stage.ofDate(Date.valueOf(LocalDate.of(2015, Month.OCTOBER, 30)));
    stage.addGoals(ortigoza, 2);

    persist(ortigoza);
    Stage stageFromSQL = saveAndRetrieve(Stage.class, stage);

    assertEquals(stage.getDate(), stageFromSQL.getDate());
    assertEquals(2, stageFromSQL.goalsOf(ortigoza));
  }

  private <T> T saveAndRetrieve(Class<T> clazz, T entity) {
    persist(entity);
    entityManager().getTransaction().commit();
    entityManager().clear();

    return find(clazz, 1l);
  }
}
