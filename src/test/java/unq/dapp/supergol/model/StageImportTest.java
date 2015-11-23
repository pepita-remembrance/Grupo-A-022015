package unq.dapp.supergol.model;

import org.junit.Before;
import org.junit.Test;
import unq.dapp.supergol.model.exceptions.UnexistentPlayerException;
import unq.dapp.supergol.model.repositories.Repository;

import java.sql.Date;
import java.time.LocalDate;

import static unq.dapp.supergol.helpers.DomainFactory.anyRealWorldTeam;

public class StageImportTest {
  private Player milito;
  private Player mancuello;
  private Stage stage;
  private Repository<Player> repo;

  @Before
  public void setUp() {
    repo = new Repository<>();

    RealWorldTeam racing = anyRealWorldTeam();
    milito = Player.forward(racing);
    saveWithId(milito, 1);

    RealWorldTeam independiente = anyRealWorldTeam();
    mancuello = Player.midfielder(independiente);
    saveWithId(mancuello, 2);

    RealWorldTeam sanLorenzo = anyRealWorldTeam();
    RealWorldTeam huracan = anyRealWorldTeam();

    stage = Stage.ofDate(
      Date.valueOf(LocalDate.of(2015, 10, 25)),
      Match.versus(racing, independiente),
      Match.versus(sanLorenzo, huracan)
    );
  }

  @Test(expected = UnexistentPlayerException.class)
  public void theImportFailsIfAPlayerDoesntExist() {
    new StageImport(repo, "89,Midfielder,3", stage).execute();
  }

  private void saveWithId(Player player, int id) {
    player.setId(id);
    repo.add(player);
  }
}
