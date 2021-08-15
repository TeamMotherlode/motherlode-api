package motherlode.base.api.resource.builder.adapter.artifice;

import java.util.function.Supplier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.builder.TypedJsonBuilder;

public record ArtificeJsonBuilderAdapter(TypedJsonBuilder<?> builder) implements JsonBuilder {
    @Override
    public <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run) {
        this.builder.with(key, json, run::accept);
        return this;
    }

    @Override
    public JsonBuilder add(String name, JsonElement value) {
        this.builder.jsonElement(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, String value) {
        this.builder.jsonString(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, boolean value) {
        this.builder.jsonBoolean(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, Number value) {
        this.builder.jsonNumber(name, value);
        return this;
    }

    @Override
    public JsonBuilder add(String name, Character value) {
        this.builder.jsonChar(name, value);
        return this;
    }

    @Override
    public JsonBuilder addObject(String name, Processor<JsonBuilder> settings) {
        this.builder.jsonObject(name, builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
        return this;
    }

    @Override
    public JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
        this.builder.jsonArray(name, builder -> settings.accept(new ArtificeJsonArrayBuilderAdapter(builder)));
        return this;
    }

    @Override
    public JsonBuilder write(JsonObject target) {
        this.builder.buildTo(target);
        return this;
    }

    @Override
    public JsonObject build() {
        JsonObject json = new JsonObject();
        this.builder.buildTo(json);

        return json;
    }
}
