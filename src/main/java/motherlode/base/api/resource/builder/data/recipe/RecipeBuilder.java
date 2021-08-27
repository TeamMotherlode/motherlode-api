package motherlode.base.api.resource.builder.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for a recipe ({@code namespace:recipes/recipe_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface RecipeBuilder<T extends RecipeBuilder<T>> {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    T with(Processor<JsonBuilder> json);

    /**
     * Set the type of this recipe.
     *
     * @param id The type ID.
     * @return this
     */
    T type(Identifier id);

    /**
     * Set the recipe book group of this recipe.
     *
     * @param id The group ID.
     * @return this
     */
    T group(Identifier id);
}
