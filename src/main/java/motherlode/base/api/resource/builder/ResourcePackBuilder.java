package motherlode.base.api.resource.builder;

import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.assets.ModelBuilder;

/**
 * Passed to resource construction callbacks to register client-side resources.
 */
@Environment(EnvType.CLIENT)
public interface ResourcePackBuilder {
    /**
     * Add an item model for the given item ID.
     *
     * @param id An item ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ModelBuilder} to create the item model.
     */
    ResourcePackBuilder addItemModel(Identifier id, Processor<ModelBuilder> f);

    /**
     * Add a block model for the given block ID.
     *
     * @param id A block ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ModelBuilder} to create the block model.
     */
    ResourcePackBuilder addBlockModel(Identifier id, Processor<ModelBuilder> f);
}
