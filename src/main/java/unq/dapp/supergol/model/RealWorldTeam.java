package unq.dapp.supergol.model;

import unq.dapp.supergol.model.repositories.Persistable;
import javax.persistence.Entity;

@Entity
public class RealWorldTeam extends Persistable {
  public String getName() {
    return name;
  }

  private String name;

  public static RealWorldTeam named(String name) {
    RealWorldTeam realWorldTeam = new RealWorldTeam();
    realWorldTeam.name = name;

    return realWorldTeam;
  }

  @Override
  public String toString() {
    return name;
  }

  public boolean includes(Player player) {
    return player.getRealWorldTeam() == this;
  }
}
