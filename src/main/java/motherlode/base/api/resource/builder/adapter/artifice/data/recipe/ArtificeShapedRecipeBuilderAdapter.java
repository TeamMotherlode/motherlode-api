package motherlode.base.api.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapedRecipeBuilder;

public record ArtificeShapedRecipeBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.recipe.ShapedRecipeBuilder recipe) implements ShapedRecipeBuilder {
    @Override
    public ShapedRecipeBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.recipe));
        return this;
    }

    @Override
    public ShapedRecipeBuilder type(Identifier id) {
        this.recipe.type(id);
        return this;
    }

    @Override
    public ShapedRecipeBuilder group(Identifier id) {
        this.recipe.group(id);
        return this;
    }

    @Override
    public ShapedRecipeBuilder pattern(String... rows) {
        this.recipe.pattern(rows);
        return this;
    }

    @Override
    public ShapedRecipeBuilder ingredientItem(char key, Identifier id) {
        this.recipe.ingredientItem(key, id);
        return this;
    }

    @Override
    public ShapedRecipeBuilder ingredientTag(char key, Identifier id) {
        this.recipe.ingredientTag(key, id);
        return this;
    }

    @Override
    public ShapedRecipeBuilder ingredient(char key, Processor<IngredientsBuilder> settings) {
        this.recipe.multiIngredient(key, ingredients -> settings.accept(new ArtificeIngredientsBuilderAdapter(ingredients)));
        return this;
    }

    @Override
    public ShapedRecipeBuilder result(Identifier id, int count) {
        this.recipe.result(id, count);
        return this;
    }
}
