package motherlode.base.api.resource.builder.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;

/**
 * Builder for a shaped crafting recipe ({@code namespace:recipes/recipe_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface ShapedRecipeBuilder extends RecipeBuilder<ShapedRecipeBuilder> {
    /**
     * Set the recipe pattern for this recipe.
     * Each character of the given strings should correspond to a key registered for an ingredient.
     *
     * @param rows The individual rows of the pattern.
     * @return this
     */
    ShapedRecipeBuilder pattern(String... rows);

    /**
     * Add an ingredient item.
     *
     * @param key The key in the recipe pattern corresponding to this ingredient.
     * @param id  The item ID.
     * @return this
     */
    ShapedRecipeBuilder ingredientItem(char key, Identifier id);

    /**
     * Add an ingredient item as any of the given tag.
     *
     * @param key The key in the recipe pattern corresponding to this ingredient.
     * @param id  The tag ID.
     * @return this
     */
    ShapedRecipeBuilder ingredientTag(char key, Identifier id);

    /**
     * Add an ingredient item as one of a list of options.
     *
     * @param key      The key in the recipe pattern corresponding to this ingredient.
     * @param settings A callback which will be passed a {@link IngredientsBuilder}.
     * @return this
     */
    ShapedRecipeBuilder ingredient(char key, Processor<IngredientsBuilder> settings);

    /**
     * Set the item produced by this recipe.
     *
     * @param id    The item ID.
     * @param count The number of result items.
     * @return this
     */
    ShapedRecipeBuilder result(Identifier id, int count);
}
