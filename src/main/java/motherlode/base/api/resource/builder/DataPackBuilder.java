package motherlode.base.api.resource.builder;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.AdvancementBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;
import motherlode.base.api.resource.builder.data.TagBuilder;
import motherlode.base.api.resource.builder.data.recipe.CookingRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.GenericRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapedRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.ShapelessRecipeBuilder;
import motherlode.base.api.resource.builder.data.recipe.StonecuttingRecipeBuilder;

/**
 * Passed to resource construction callbacks to register server-side resources.
 */
@SuppressWarnings("UnusedReturnValue")
public interface DataPackBuilder extends AssetPackBuilder {
    /**
     * Add an advancement with the given ID.
     *
     * @param id The ID of the advancement, which will be converted into the correct path.
     * @param f  A callback which will be passed an {@link AdvancementBuilder} to create the advancement.
     * @return this
     */
    DataPackBuilder addAdvancement(Identifier id, Processor<AdvancementBuilder> f);

    /**
     * Add a loot table with the given ID.
     *
     * @param id The ID of the loot table, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link LootTableBuilder} to create the loot table.
     * @return this
     */
    DataPackBuilder addLootTable(Identifier id, Processor<LootTableBuilder> f);

    /**
     * Add an item tag with the given ID.
     *
     * @param id The ID of the tag, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link TagBuilder} to create the tag.
     * @return this
     */
    DataPackBuilder addItemTag(Identifier id, Processor<TagBuilder> f);

    /**
     * Add a block tag with the given ID.
     *
     * @param id The ID of the tag, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link TagBuilder} to create the tag.
     * @return this
     */
    DataPackBuilder addBlockTag(Identifier id, Processor<TagBuilder> f);

    /**
     * Add an entity type tag with the given ID.
     *
     * @param id The ID of the tag, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link TagBuilder} to create the tag.
     * @return this
     */
    DataPackBuilder addEntityTypeTag(Identifier id, Processor<TagBuilder> f);

    /**
     * Add a fluid tag with the given ID.
     *
     * @param id The ID of the tag, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link TagBuilder} to create the tag.
     * @return this
     */
    DataPackBuilder addFluidTag(Identifier id, Processor<TagBuilder> f);

    /**
     * Add a function tag with the given ID.
     *
     * @param id The ID of the tag, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link TagBuilder} to create the tag.
     * @return this
     */
    DataPackBuilder addFunctionTag(Identifier id, Processor<TagBuilder> f);

    /**
     * Add a recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link GenericRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addRecipe(Identifier id, Processor<GenericRecipeBuilder> f);

    /**
     * Add a shaped crafting recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ShapedRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addShapedRecipe(Identifier id, Processor<ShapedRecipeBuilder> f);

    /**
     * Add a shapeless crafting recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ShapelessRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addShapelessRecipe(Identifier id, Processor<ShapelessRecipeBuilder> f);

    /**
     * Add a stonecutter recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link StonecuttingRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addStonecuttingRecipe(Identifier id, Processor<StonecuttingRecipeBuilder> f);

    /**
     * Add a smelting recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link CookingRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addSmeltingRecipe(Identifier id, Processor<CookingRecipeBuilder> f);

    /**
     * Add a blast furnace recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link CookingRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addBlastingRecipe(Identifier id, Processor<CookingRecipeBuilder> f);

    /**
     * Add a smoker recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link CookingRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addSmokingRecipe(Identifier id, Processor<CookingRecipeBuilder> f);

    /**
     * Add a campfire recipe with the given ID.
     *
     * @param id The ID of the recipe, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link CookingRecipeBuilder} to create the recipe.
     * @return this
     */
    DataPackBuilder addCampfireRecipe(Identifier id, Processor<CookingRecipeBuilder> f);
}
