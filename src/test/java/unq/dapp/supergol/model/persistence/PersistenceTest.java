package unq.dapp.supergol.model.persistence;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.RealWorldTeam;

import static org.junit.Assert.assertEquals;

public class PersistenceTest extends BasePersistenceTest {

  private RealWorldTeam sanLorenzo;

  @Before
  public void setUp() throws Exception {
    sanLorenzo = RealWorldTeam.named("San Lorenzo");
  }

  @Test
  public void realWorldTeamsCanBePersisted() {
    persist(sanLorenzo);

    assertEquals(1l, sanLorenzo.getId());
    assertEquals(sanLorenzo, find(RealWorldTeam.class, 1l));
  }

  @Test
  public void playersCanBePersisted() {
    Player ortigoza = Player.midfielder(sanLorenzo);
    ortigoza.setName("Nestor Ortigoza");

    persist(ortigoza);

    assertEquals(1l, ortigoza.getId());
    assertEquals(ortigoza, find(Player.class, 1l));
  }
}
