package motherlode.base.impl.resource.builder.adapter.artifice.data.recipe;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.recipe.GenericRecipeBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeGenericRecipeBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.recipe.GenericRecipeBuilder recipe) implements GenericRecipeBuilder {
    @Override
    public GenericRecipeBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.recipe));
        return this;
    }

    @Override
    public GenericRecipeBuilder type(Identifier id) {
        this.recipe.type(id);
        return this;
    }

    @Override
    public GenericRecipeBuilder group(Identifier id) {
        this.recipe.group(id);
        return this;
    }
}
