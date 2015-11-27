package unq.dapp.supergol.model.persistence;

import org.junit.Test;
import unq.dapp.supergol.model.RealWorldTeam;

import static org.junit.Assert.assertEquals;

public class PersistenceTest extends BasePersistenceTest {
  @Test
  public void realWorldTeamsCanBePersisted() {
    RealWorldTeam sanLorenzo = RealWorldTeam.named("San Lorenzo");
    persist(sanLorenzo);

    assertEquals(1l, sanLorenzo.getId());
    assertEquals(sanLorenzo, find(RealWorldTeam.class, 1l));
  }
}
