package unq.dapp.supergol.server.serialization;

import com.google.gson.Gson;
import io.gsonfire.GsonFireBuilder;
import spark.ResponseTransformer;

import java.time.LocalDate;

public class JsonTransformer implements ResponseTransformer {

  private Gson gson =
    new GsonFireBuilder().enableExposeMethodResult().createGsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateJsonSerializer())
    .setExclusionStrategies(new AnnotationExclusionStrategy())
    .create();

  @Override
  public String render(Object model) {
    return gson.toJson(model);
  }

  public <T> T parse(Class<T> clazz, String json) {
    return gson.fromJson(json, clazz);
  }
}
