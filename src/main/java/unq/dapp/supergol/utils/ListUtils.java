package unq.dapp.supergol.utils;

import java.util.List;

public class ListUtils {
  public static List<String> tail(List<String> self) {
    return self.subList(1, self.size());
  }
}
