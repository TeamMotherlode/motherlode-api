package motherlode.base.api.resource.builder.data.recipe;

import java.util.function.Supplier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Builder for a recipe of an unknown type ({@code namespace:recipes/recipe_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface GenericRecipeBuilder extends RecipeBuilder<GenericRecipeBuilder>, JsonBuilder {
    default <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run) {
        return this.with(builder -> builder.with(key, json, run));
    }

    default GenericRecipeBuilder add(String name, JsonElement value) {
        return this.with(builder -> builder.add(name, value));
    }

    default GenericRecipeBuilder add(String name, String value) {
        return this.with(builder -> builder.add(name, value));
    }

    default GenericRecipeBuilder add(String name, boolean value) {
        return this.with(builder -> builder.add(name, value));
    }

    default GenericRecipeBuilder add(String name, Number value) {
        return this.with(builder -> builder.add(name, value));
    }

    default GenericRecipeBuilder add(String name, Character value) {
        return this.with(builder -> builder.add(name, value));
    }

    default GenericRecipeBuilder addObject(String name, Processor<JsonBuilder> settings) {
        return this.with(builder -> builder.addObject(name, settings));
    }

    default GenericRecipeBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
        return this.with(builder -> builder.addArray(name, settings));
    }

    default GenericRecipeBuilder write(JsonObject target) {
        return this.with(builder -> builder.write(target));
    }

    default JsonObject build() {
        JsonObject json = new JsonObject();
        this.write(json);

        return json;
    }
}
