package motherlode.base.impl.resource.builder.adapter.artifice;

import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public record ArtificeJsonArrayBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.JsonArrayBuilder array) implements JsonArrayBuilder {
    @Override
    public JsonArrayBuilder add(JsonElement value) {
        this.array.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(String value) {
        this.array.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(boolean value) {
        this.array.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Number value) {
        this.array.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Character value) {
        this.array.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(Processor<JsonBuilder> settings) {
        this.array.addObject(builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
        return this;
    }

    @Override
    public JsonArrayBuilder addArray(Processor<JsonArrayBuilder> settings) {
        this.array.addArray(builder -> settings.accept(new ArtificeJsonArrayBuilderAdapter(builder)));
        return this;
    }

    @Override
    public JsonArrayBuilder write(JsonArray target) {
        this.array.buildTo(target);
        return this;
    }

    @Override
    public JsonArray build() {
        return this.array.build();
    }
}
