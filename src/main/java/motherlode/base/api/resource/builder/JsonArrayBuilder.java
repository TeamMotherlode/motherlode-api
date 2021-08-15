package motherlode.base.api.resource.builder;

import motherlode.base.api.Processor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public interface JsonArrayBuilder {
    JsonArrayBuilder add(JsonElement value);

    JsonArrayBuilder add(String value);

    JsonArrayBuilder add(boolean value);

    JsonArrayBuilder add(Number value);

    JsonArrayBuilder add(Character value);

    JsonArrayBuilder add(Processor<JsonBuilder> settings);

    JsonArrayBuilder addArray(Processor<JsonArrayBuilder> settings);

    JsonArrayBuilder write(JsonArray target);

    JsonArray build();

    default Resource<JsonArray> buildResource() {
        return new JsonResource<>(this.build());
    }
}
