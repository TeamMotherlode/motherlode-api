package motherlode.base.api.resource.builder.data;

import net.minecraft.util.Identifier;
import com.swordglowsblue.artifice.api.builder.data.recipe.CookingRecipeBuilder;
import com.swordglowsblue.artifice.api.builder.data.recipe.ShapedRecipeBuilder;
import com.swordglowsblue.artifice.api.builder.data.recipe.ShapelessRecipeBuilder;
import com.swordglowsblue.artifice.api.builder.data.recipe.StonecuttingRecipeBuilder;

/**
 * Builder for a recipe ingredient option list.
 *
 * @see CookingRecipeBuilder
 * @see ShapedRecipeBuilder
 * @see ShapelessRecipeBuilder
 * @see StonecuttingRecipeBuilder
 */
public interface IngredientsBuilder {
    /**
     * Add an item as an option.
     *
     * @param id The item ID.
     * @return this
     */
    IngredientsBuilder item(Identifier id);

    /**
     * Add all items from the given tag as options.
     *
     * @param id The tag ID.
     * @return this
     */
    IngredientsBuilder tag(Identifier id);
}
