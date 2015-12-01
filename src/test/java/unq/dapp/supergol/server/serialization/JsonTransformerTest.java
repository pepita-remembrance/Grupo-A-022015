package unq.dapp.supergol.server.serialization;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JsonTransformerTest {
  public class Example {
    private LocalDate date;
    @JsonIgnore private String ignored;

    public Example(LocalDate date, String ignored) {
      this.date = date;
      this.ignored = ignored;
    }
  }

  private JsonTransformer transformer;

  @Before
  public void setUp() throws Exception {
    transformer = new JsonTransformer();
  }

  @Test
  public void datesAreSerializedInIso8601Format() {
    JsonObject jsonObject = renderJson(new Example(LocalDate.of(2014, Month.DECEMBER, 28), ""));

    assertEquals("2014-12-28", jsonObject.get("date").getAsString());
  }

  @Test
  public void fieldsCanBeIgnored() {
    JsonObject jsonObject = renderJson(new Example(LocalDate.of(2014, Month.DECEMBER, 28), "I will be ignored"));

    assertFalse(jsonObject.has("ignored"));
  }

  private JsonObject renderJson(Example model) {
    String json = transformer.render(model);
    return new JsonParser().parse(json).getAsJsonObject();
  }
}
