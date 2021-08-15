package motherlode.base.impl.resource.builder;

import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public record JsonArrayBuilderImpl(JsonArray json) implements JsonArrayBuilder {
    public JsonArrayBuilderImpl() {
        this(new JsonArray());
    }

    @Override
    public JsonArrayBuilder add(JsonElement value) {
        this.json.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(String value) {
        this.json.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(boolean value) {
        this.json.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Number value) {
        this.json.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Character value) {
        this.json.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Processor<JsonBuilder> settings) {
        this.json.add(settings.process(new JsonBuilderImpl()).build());
        return this;
    }

    @Override
    public JsonArrayBuilder addArray(Processor<JsonArrayBuilder> settings) {
        this.json.add(settings.process(new JsonArrayBuilderImpl()).build());
        return this;
    }

    public JsonArrayBuilder write(JsonArray target) {
        target.addAll(this.json);
        return this;
    }

    public JsonArray build() {
        JsonArray array = new JsonArray();
        this.write(array);

        return array;
    }
}
