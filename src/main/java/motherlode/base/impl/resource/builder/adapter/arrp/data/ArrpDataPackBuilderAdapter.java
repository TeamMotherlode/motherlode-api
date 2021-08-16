package motherlode.base.impl.resource.builder.adapter.arrp.data;

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
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.ArrpRuntimeResourcePack;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJLootTable;
import motherlode.base.impl.resource.builder.adapter.general.data.CaptureLootTableType;
import com.google.gson.JsonObject;

public record ArrpDataPackBuilderAdapter(ArrpRuntimeResourcePack pack) implements DataPackBuilder {
    @Override
    public void add(Identifier id, Resource<JsonObject> resource) {
    }

    @Override
    public DataPackBuilder addAdvancement(Identifier id, Processor<AdvancementBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addLootTable(Identifier id, Processor<LootTableBuilder> f) {
        ArrpJLootTable table = ArrpJLootTable.loot(CaptureLootTableType.getType(f).toString());
        f.accept(new ArrpLootTableBuilderAdapter(table));

        this.pack.addLootTable(id, table);
        return this;
    }

    @Override
    public DataPackBuilder addItemTag(Identifier id, Processor<TagBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addBlockTag(Identifier id, Processor<TagBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addEntityTypeTag(Identifier id, Processor<TagBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addFluidTag(Identifier id, Processor<TagBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addFunctionTag(Identifier id, Processor<TagBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addRecipe(Identifier id, Processor<GenericRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addShapedRecipe(Identifier id, Processor<ShapedRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addShapelessRecipe(Identifier id, Processor<ShapelessRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addStonecuttingRecipe(Identifier id, Processor<StonecuttingRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addSmeltingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addBlastingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addSmokingRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        return this;
    }

    @Override
    public DataPackBuilder addCampfireRecipe(Identifier id, Processor<CookingRecipeBuilder> f) {
        return this;
    }
}
