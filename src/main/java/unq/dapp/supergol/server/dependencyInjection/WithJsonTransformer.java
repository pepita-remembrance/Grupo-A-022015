package unq.dapp.supergol.server.dependencyInjection;

import spark.Filter;
import unq.dapp.supergol.server.serialization.JsonTransformer;

public interface WithJsonTransformer extends WithResponseTransformer {
  public default JsonTransformer responseTransformer(){
    return new JsonTransformer();
  };

  default Filter jsonResponseType(){
    return (request, response) -> response.type("application/json");
  }
}
