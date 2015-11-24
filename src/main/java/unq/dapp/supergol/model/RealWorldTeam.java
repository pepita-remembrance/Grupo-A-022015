package unq.dapp.supergol.model;

public class RealWorldTeam {
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
