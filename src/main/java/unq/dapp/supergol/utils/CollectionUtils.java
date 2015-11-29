package unq.dapp.supergol.utils;

import java.util.Collection;
import java.util.List;

public class CollectionUtils {
  public static <T> List<T> tail(List<T> self) {
    return self.subList(1, self.size());
  }

  public static <T> T anyOne(Collection<T> self) {
    return self.iterator().next();
  }
}
