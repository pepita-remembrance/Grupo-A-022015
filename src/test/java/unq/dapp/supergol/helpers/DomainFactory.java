package unq.dapp.supergol.helpers;

import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Position;
import unq.dapp.supergol.model.RealWorldTeam;
import unq.dapp.supergol.model.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class DomainFactory {
  public static Player anyPlayer(Position position) {
    return Player.ofTeam(position, anyRealWorldTeam());
  }

  public static RealWorldTeam anyRealWorldTeam() {
    return RealWorldTeam.named("Anyone");
  }

  public static Stage anyStage() {
    return Stage.ofDate(Date.valueOf(LocalDate.now()));
  }
}
