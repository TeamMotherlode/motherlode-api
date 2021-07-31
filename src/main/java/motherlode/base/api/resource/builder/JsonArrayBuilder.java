package motherlode.base.api.resource.builder;

import motherlode.base.api.Processor;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public record JsonArrayBuilder(JsonArray json) {
    public JsonArrayBuilder() {
        this(new JsonArray());
    }

    public void write(JsonArray target) {
        target.addAll(this.json);
    }

    public JsonArray build() {
        JsonArray array = new JsonArray();
        this.write(array);

        return array;
    }

    public JsonArrayBuilder add(JsonElement value) {
        this.json.add(value);
        return this;
    }

    public JsonArrayBuilder add(String value) {
        this.json.add(value);
        return this;
    }

    public JsonArrayBuilder add(boolean value) {
        this.json.add(value);
        return this;
    }

    public JsonArrayBuilder add(Number value) {
        this.json.add(value);
        return this;
    }

    public JsonArrayBuilder add(Character value) {
        this.json.add(value);
        return this;
    }

    public JsonArrayBuilder add(Processor<JsonBuilder> settings) {
        this.json.add(settings.process(new JsonBuilder()).build());
        return this;
    }

    public JsonArrayBuilder addArray(Processor<JsonArrayBuilder> settings) {
        this.json.add(settings.process(new JsonArrayBuilder()).build());
        return this;
    }
}
