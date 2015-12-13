package unq.dapp.supergol.server.dependencyInjection;

import spark.ResponseTransformer;

public interface WithResponseTransformer {
  public ResponseTransformer responseTransformer();
}
