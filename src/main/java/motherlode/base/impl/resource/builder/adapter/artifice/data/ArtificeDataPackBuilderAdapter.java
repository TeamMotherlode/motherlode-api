package motherlode.base.impl.resource.builder.adapter.artifice.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.DataPackBuilder;
import motherlode.base.api.resource.builder.Resource;
import motherlode.base.api.resource.builder.data.AdvancementBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;
import motherlode.base.api.resource.builder.data.TagBuilder;
import motherlode.base.api.resource.builder.data.recipe.CookingRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.GenericRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapedRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapelessRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.StonecuttingRecipeBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.data.recipe.ArtificeCookingRecipeBuilderAdapter;
import motherlode.base.impl.resource.builder.adapter.artifice.data.recipe.ArtificeGenericRecipeBuilderAdapter;
import motherlode.base.impl.resource.builder.adapter.artifice.data.recipe.ArtificeShapedRecipeBuilderAdapter;
import motherlode.base.impl.resource.builder.adapter.artifice.data.recipe.ArtificeShapelessRecipeBuilderAdapter;
import motherlode.base.impl.resource.builder.adapter.artifice.data.recipe.ArtificeStonecuttingRecipeBuilderAdapter;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public record ArtificeDataPackBuilderAdapter(ArtificeResourcePack.ServerResourcePackBuilder builder) implements DataPackBuilder {
    @Override
    public void add(Identifier id, Resource<JsonObject> resource) {
        this.builder.add(id, new JsonResource<>(resource.get()));
    }

    @Override
    public DataPackBuilder addAdvancement(Identifier id, Processor<AdvancementBuilder> f) {
        this.builder.addAdvancement(id, advancement -> f.accept(new ArtificeAdvancementBuilderAdapter(advancement)));
        return this;
    }

    @Override
    public DataPackBuilder addLootTable(Identifier id, Processor<LootTableBuilder> f) {
        this.builder.addLootTable(id, table -> f.accept(new ArtificeLootTableBuilderAdapter(table)));
        return this;
    }

    @Override
    public DataPackBuilder addItemTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addItemTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addBlockTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addBlockTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addEntityTypeTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addEntityTypeTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addFluidTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addFluidTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addFunctionTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addFunctionTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addRecipe(Identifier id, Processor<GenericRecipeBuilder> f) {
        this.builder.addGenericRecipe(id, recipe -> f.accept(new ArtificeGenericRecipeBuilderAdapter(recipe)));
        return this;
    }

    @Override
    public DataPackBuilder addShapedRecipe(Identifier id, Processor<ShapedRecipeBuilder> f) {
        this.builder.addShapedRecipe(id, recipe -> f.accept(new ArtificeShapedRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "crafting_shaped"))));
        return this;
    }

    @Override
    public DataPackBuilder addShapelessRecipe(Identifier id, Processor<ShapelessRecipeBuilder> f) {
        this.builder.addShapelessRecipe(id, recipe -> f.accept(new ArtificeShapelessRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "crafting_shapeless"))));
        return this;
    }

    @Override
    public DataPackBuilder addStonecuttingRecipe(Identifier id, Processor<StonecuttingRecipeBuilder> f) {
        this.builder.addStonecuttingRecipe(id, recipe -> f.accept(new ArtificeStonecuttingRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "stonecutting"))));
        return this;
    }

    @Override
    public DataPackBuilder addSmeltingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        this.builder.addSmeltingRecipe(id, recipe -> f.accept(new ArtificeCookingRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "smelting"))));
        return this;
    }

    @Override
    public DataPackBuilder addBlastingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        this.builder.addBlastingRecipe(id, recipe -> f.accept(new ArtificeCookingRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "blasting"))));
        return this;
    }

    @Override
    public DataPackBuilder addSmokingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        this.builder.addSmokingRecipe(id, recipe -> f.accept(new ArtificeCookingRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "smoking"))));
        return this;
    }

    @Override
    public DataPackBuilder addCampfireRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        this.builder.addCampfireRecipe(id, recipe -> f.accept(new ArtificeCookingRecipeBuilderAdapter(recipe)
            .type(new Identifier("minecraft", "campfire_cooking"))));
        return this;
    }
}
