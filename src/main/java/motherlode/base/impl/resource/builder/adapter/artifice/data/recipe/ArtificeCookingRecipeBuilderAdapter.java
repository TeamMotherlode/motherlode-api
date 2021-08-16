package motherlode.base.impl.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.IngredientsBuilder;
import motherlode.base.api.resource.builder.data.recipe.CookingRecipeBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeCookingRecipeBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.recipe.CookingRecipeBuilder recipe) implements CookingRecipeBuilder {
    @Override
    public CookingRecipeBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.recipe));
        return this;
    }

    @Override
    public CookingRecipeBuilder type(Identifier id) {
        this.recipe.type(id);
        return this;
    }

    @Override
    public CookingRecipeBuilder group(Identifier id) {
        this.recipe.group(id);
        return this;
    }

    @Override
    public CookingRecipeBuilder ingredientItem(Identifier id) {
        this.recipe.ingredientItem(id);
        return this;
    }

    @Override
    public CookingRecipeBuilder ingredientTag(Identifier id) {
        this.recipe.ingredientTag(id);
        return this;
    }

    @Override
    public CookingRecipeBuilder ingredient(Processor<IngredientsBuilder> settings) {
        this.recipe.multiIngredient(ingredients -> settings.accept(new ArtificeIngredientsBuilderAdapter(ingredients)));
        return this;
    }

    @Override
    public CookingRecipeBuilder result(Identifier id) {
        this.recipe.result(id);
        return this;
    }

    @Override
    public CookingRecipeBuilder experience(double experience) {
        this.recipe.experience(experience);
        return this;
    }

    @Override
    public CookingRecipeBuilder cookingTime(int time) {
        this.recipe.cookingTime(time);
        return this;
    }
}
