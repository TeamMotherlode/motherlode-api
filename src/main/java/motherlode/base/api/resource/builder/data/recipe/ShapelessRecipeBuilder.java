package motherlode.base.api.resource.builder.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;

/**
 * Builder for a shapeless crafting recipe ({@code namespace:recipes/recipe_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface ShapelessRecipeBuilder extends RecipeBuilder<ShapelessRecipeBuilder> {
    /**
     * Add an ingredient item.
     *
     * @param id The item ID.
     * @return this
     */
    ShapelessRecipeBuilder ingredientItem(Identifier id);

    /**
     * Add an ingredient item as any of the given tag.
     *
     * @param id The tag ID.
     * @return this
     */
    ShapelessRecipeBuilder ingredientTag(Identifier id);

    /**
     * Add an ingredient item as one of a list of options.
     *
     * @param settings A callback which will be passed a {@link IngredientsBuilder}.
     * @return this
     */
    ShapelessRecipeBuilder ingredient(Processor<IngredientsBuilder> settings);

    /**
     * Set the item produced by this recipe.
     *
     * @param id    The item ID.
     * @param count The number of result items.
     * @return this
     */
    ShapelessRecipeBuilder result(Identifier id, int count);
}
