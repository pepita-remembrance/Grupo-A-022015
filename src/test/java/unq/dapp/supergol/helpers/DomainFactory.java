package unq.dapp.supergol.helpers;

import unq.dapp.supergol.model.Player;
import unq.dapp.supergol.model.Position;
import unq.dapp.supergol.model.RealWorldTeam;

public class DomainFactory {
  public static Player anyPlayer(Position position) {
    return Player.ofTeam(position, anyRealWorldTeam());
  }

  public static RealWorldTeam anyRealWorldTeam() {
    return RealWorldTeam.named("Anyone");
  }
}
