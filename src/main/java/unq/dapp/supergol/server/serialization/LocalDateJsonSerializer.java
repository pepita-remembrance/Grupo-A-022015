package unq.dapp.supergol.server.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJsonSerializer implements JsonSerializer<LocalDate> {
  @Override
  public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE));
  }
}
