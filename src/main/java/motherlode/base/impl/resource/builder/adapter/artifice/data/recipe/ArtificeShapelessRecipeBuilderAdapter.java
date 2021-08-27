package motherlode.base.impl.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapelessRecipeBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeShapelessRecipeBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.recipe.ShapelessRecipeBuilder recipe) implements ShapelessRecipeBuilder {
    @Override
    public ShapelessRecipeBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.recipe));
        return this;
    }

    @Override
    public ShapelessRecipeBuilder type(Identifier id) {
        this.recipe.type(id);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder group(Identifier id) {
        this.recipe.group(id);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder ingredientItem(Identifier id) {
        this.recipe.ingredientItem(id);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder ingredientTag(Identifier id) {
        this.recipe.ingredientTag(id);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder ingredient(Processor<IngredientsBuilder> settings) {
        this.recipe.multiIngredient(ingredients -> settings.accept(new ArtificeIngredientsBuilderAdapter(ingredients)));
        return this;
    }

    @Override
    public ShapelessRecipeBuilder result(Identifier id, int count) {
        this.recipe.result(id, count);
        return this;
    }
}
