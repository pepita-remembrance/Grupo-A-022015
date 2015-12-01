package unq.dapp.supergol.helpers;

import unq.dapp.supergol.model.*;

import java.time.LocalDate;

public class DomainFactory {
  public static Player anyPlayer(Position position) {
    return Player.ofTeam(position, anyRealWorldTeam());
  }

  public static String anyRealWorldTeam() {
    return "Anyone";
  }

  public static Stage anyStage() {
    return Stage.ofDate(LocalDate.now());
  }

  public static Team anyTeam() {
    return new Team();
  }
}
