package motherlode.base.api.resource.builder.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;

/**
 * Builder for cooking recipes ({@code namespace:recipes/id.json}).
 * Used for all types of cooking (smelting, blasting, smoking, campfire_cooking).
 *
 * @see <a href="https://minecraft.gamepedia.com/Recipe#JSON_format" target="_blank">Minecraft Wiki</a>
 */
public interface CookingRecipeBuilder extends RecipeBuilder<CookingRecipeBuilder> {
    /**
     * Set the item being cooked.
     *
     * @param id The item ID.
     * @return this
     */
    CookingRecipeBuilder ingredientItem(Identifier id);

    /**
     * Set the item being cooked as any of the given tag.
     *
     * @param id The tag ID.
     * @return this
     */
    CookingRecipeBuilder ingredientTag(Identifier id);

    /**
     * Set the item being cooked as one of a list of options.
     *
     * @param settings A callback which will be passed a {@link IngredientsBuilder}.
     * @return this
     */
    CookingRecipeBuilder ingredient(Processor<IngredientsBuilder> settings);

    /**
     * Set the item produced by this recipe.
     *
     * @param id The item ID.
     * @return this
     */
    CookingRecipeBuilder result(Identifier id);

    /**
     * Set the amount of experience given by this recipe.
     *
     * @param experience The amount of experience.
     * @return this
     */
    CookingRecipeBuilder experience(double experience);

    /**
     * Set how long this recipe should take to complete in ticks.
     *
     * @param time The number of ticks.
     * @return this
     */
    CookingRecipeBuilder cookingTime(int time);
}
