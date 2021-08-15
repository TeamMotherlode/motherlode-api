package motherlode.base.api.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;
import motherlode.base.api.resource.builder.data.recipe.StonecuttingRecipeBuilder;

public record ArtificeStonecuttingRecipeBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.recipe.StonecuttingRecipeBuilder recipe) implements StonecuttingRecipeBuilder {
    @Override
    public StonecuttingRecipeBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.recipe));
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder type(Identifier id) {
        this.recipe.type(id);
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder group(Identifier id) {
        this.recipe.group(id);
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder ingredientItem(Identifier id) {
        this.recipe.ingredientItem(id);
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder ingredientTag(Identifier id) {
        this.recipe.ingredientTag(id);
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder ingredient(Processor<IngredientsBuilder> settings) {
        this.recipe.multiIngredient(ingredients -> settings.accept(new ArtificeIngredientsBuilderAdapter(ingredients)));
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder result(Identifier id) {
        this.recipe.result(id);
        return this;
    }

    @Override
    public StonecuttingRecipeBuilder count(int count) {
        this.recipe.count(count);
        return this;
    }
}
