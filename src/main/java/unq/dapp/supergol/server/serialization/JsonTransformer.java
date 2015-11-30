package unq.dapp.supergol.server.serialization;

import com.google.gson.*;
import spark.ResponseTransformer;

import java.time.LocalDate;

public class JsonTransformer implements ResponseTransformer {

  private Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateJsonSerializer())
    .setExclusionStrategies(new AnnotationExclusionStrategy())
    .create();

  @Override
  public String render(Object model) {
    return gson.toJson(model);
  }
}
