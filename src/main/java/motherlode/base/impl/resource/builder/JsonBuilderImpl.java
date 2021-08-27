package motherlode.base.impl.resource.builder;

import java.util.function.Supplier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonBuilderImpl implements JsonBuilder {
    private final JsonObject json;

    public JsonBuilderImpl(JsonObject json) {
        this.json = json;
    }

    public JsonBuilderImpl() {
        this(new JsonObject());
    }

    @Override
    public <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run) {
        JsonBuilder.with(this.json, key, json, run);
        return this;
    }

    @Override
    public JsonBuilder add(String name, JsonElement value) {
        this.json.add(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, String value) {
        this.json.addProperty(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, boolean value) {
        this.json.addProperty(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, Number value) {
        this.json.addProperty(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, Character value) {
        this.json.addProperty(name, value);
        return this;
    }

    @Override
    public JsonBuilder addObject(String name, Processor<JsonBuilder> settings) {
        this.json.add(name, settings.process(new JsonBuilderImpl()).build());
        return this;
    }

    @Override
    public JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
        this.json.add(name, settings.process(new JsonArrayBuilderImpl()).build());
        return this;
    }

    @Override
    public JsonBuilder write(JsonObject target) {
        JsonBuilder.copy(this.json, target);
        return this;
    }
}
