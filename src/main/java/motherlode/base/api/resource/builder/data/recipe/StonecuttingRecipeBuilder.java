package motherlode.base.api.resource.builder.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;

/**
 * Builder for a stonecutting recipe ({@code namespace:recipes/recipe_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface StonecuttingRecipeBuilder extends RecipeBuilder<StonecuttingRecipeBuilder> {
    /**
     * Set the item being cut.
     *
     * @param id The item ID.
     * @return this
     */
    StonecuttingRecipeBuilder ingredientItem(Identifier id);

    /**
     * Set the item being cut as any of the given tag.
     *
     * @param id The tag ID.
     * @return this
     */
    StonecuttingRecipeBuilder ingredientTag(Identifier id);

    /**
     * Set the item being cut as one of a list of options.
     *
     * @param settings A callback which will be passed a {@link IngredientsBuilder}.
     * @return this
     */
    StonecuttingRecipeBuilder ingredient(Processor<IngredientsBuilder> settings);

    /**
     * Set the item produced by this recipe.
     *
     * @param id The item ID.
     * @return this
     */
    StonecuttingRecipeBuilder result(Identifier id);

    /**
     * Set the number of items produced by this recipe.
     *
     * @param count The number of result items.
     * @return this
     */
    StonecuttingRecipeBuilder count(int count);
}
