package motherlode.base.api.resource.builder;

import java.util.function.Supplier;
import motherlode.base.api.Processor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("UnusedReturnValue")
public interface JsonBuilder {
    <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run);

    JsonBuilder add(String name, JsonElement value);

    JsonBuilder add(String name, String value);

    JsonBuilder add(String name, boolean value);

    JsonBuilder add(String name, Number value);

    JsonBuilder add(String name, Character value);

    JsonBuilder addObject(String name, Processor<JsonBuilder> settings);

    JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings);

    JsonBuilder write(JsonObject target);

    default JsonObject build() {
        JsonObject object = new JsonObject();
        this.write(object);

        return object;
    }

    default Resource<JsonObject> buildResource() {
        return new JsonResource<>(this.build());
    }

    @SuppressWarnings("unchecked")
    static <J extends JsonElement> void with(JsonObject in, String key, Supplier<J> json, Processor<J> run) {
        in.add(key, run.process(in.has(key) ? (J) in.get(key) : json.get()));
    }

    static void copy(JsonObject input, JsonObject target) {
        input.entrySet().forEach(e -> target.add(e.getKey(), e.getValue()));
    }

    static JsonArray arrayOf(boolean... values) {
        JsonArray array = new JsonArray();

        for (boolean i : values) array.add(i);

        return array;
    }

    static JsonArray arrayOf(char... values) {
        JsonArray array = new JsonArray();

        for (char i : values) array.add(i);

        return array;
    }

    static JsonArray arrayOf(Number... values) {
        JsonArray array = new JsonArray();

        for (Number i : values) array.add(i);

        return array;
    }

    static JsonArray arrayOf(String... values) {
        JsonArray array = new JsonArray();

        for (String i : values) array.add(i);

        return array;
    }
}
