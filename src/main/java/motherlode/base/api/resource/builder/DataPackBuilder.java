package motherlode.base.api.resource.builder;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.data.AdvancementBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;
import motherlode.base.api.resource.builder.data.TagBuilder;

/**
 * Passed to resource construction callbacks to register server-side resources.
 */
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
}
