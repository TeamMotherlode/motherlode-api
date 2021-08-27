package motherlode.base.impl.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;
import com.swordglowsblue.artifice.api.builder.data.recipe.MultiIngredientBuilder;

public record ArtificeIngredientsBuilderAdapter(MultiIngredientBuilder ingredients) implements IngredientsBuilder {
    @Override
    public IngredientsBuilder item(Identifier id) {
        this.ingredients.item(id);
        return this;
    }

    @Override
    public IngredientsBuilder tag(Identifier id) {
        this.ingredients.tag(id);
        return this;
    }
}
