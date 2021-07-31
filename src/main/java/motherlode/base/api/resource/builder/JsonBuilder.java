package motherlode.base.api.resource.builder;

import java.util.function.Supplier;
import motherlode.base.api.Processor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonBuilder {
    private final JsonObject json;

    public JsonBuilder(JsonObject json) {
        this.json = json;
    }

    public JsonBuilder() {
        this(new JsonObject());
    }

    public <J extends JsonElement> void with(String key, Supplier<J> json, Processor<J> run) {
        with(this.json, key, json, run);
    }

    public JsonBuilder add(String name, JsonElement value) {
        this.json.add(name, value);
        return this;
    }

    public JsonBuilder add(String name, String value) {
        this.json.addProperty(name, value);
        return this;
    }

    public JsonBuilder add(String name, boolean value) {
        this.json.addProperty(name, value);
        return this;
    }

    public JsonBuilder add(String name, Number value) {
        this.json.addProperty(name, value);
        return this;
    }

    public JsonBuilder add(String name, Character value) {
        this.json.addProperty(name, value);
        return this;
    }

    public JsonBuilder addObject(String name, Processor<JsonBuilder> settings) {
        this.json.add(name, settings.process(new JsonBuilder()).build());
        return this;
    }

    public JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
        this.json.add(name, settings.process(new JsonArrayBuilder()).build());
        return this;
    }

    public void write(JsonObject target) {
        json.entrySet().forEach(e -> target.add(e.getKey(), e.getValue()));
    }

    public JsonObject build() {
        JsonObject object = new JsonObject();
        this.write(object);

        return object;
    }

    public Resource<JsonObject> buildResource() {
        return new JsonResource<>(this.build());
    }

    @SuppressWarnings("unchecked")
    public static <J extends JsonElement> void with(JsonObject in, String key, Supplier<J> json, Processor<J> run) {
        in.add(key, run.process(in.has(key) ? (J) in.get(key) : json.get()));
    }

    public static JsonArray arrayOf(boolean... values) {
        JsonArray array = new JsonArray();

        for (boolean i : values) array.add(i);

        return array;
    }

    public static JsonArray arrayOf(Character... values) {
        JsonArray array = new JsonArray();

        for (Character i : values) array.add(i);

        return array;
    }

    public static JsonArray arrayOf(Number... values) {
        JsonArray array = new JsonArray();

        for (Number i : values) array.add(i);

        return array;
    }

    public static JsonArray arrayOf(String... values) {
        JsonArray array = new JsonArray();

        for (String i : values) array.add(i);

        return array;
    }
}