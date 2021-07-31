package motherlode.base.api.resource.builder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public record JsonResource<T extends JsonElement>(T json) implements Resource<T> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public T get() {
        return this.json;
    }

    @Override
    public String asString() {
        return GSON.toJson(this.json);
    }

    @Override
    public InputStream toInputStream() {
        return new ByteArrayInputStream(this.get().toString().getBytes());
    }
}
