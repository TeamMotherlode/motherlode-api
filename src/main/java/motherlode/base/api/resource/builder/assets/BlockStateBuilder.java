package motherlode.base.api.resource.builder.assets;

import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;

/**
 * Builder for a block state definition file ({@code namespace:blockstates/block_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Model#Block_states" target="_blank">Minecraft Wiki</a>
 */
@Environment(EnvType.CLIENT)
public interface BlockStateBuilder {
    /**
     * Add a builder for the given state key.
     * Calling this multiple times for the same key will modify the existing value.
     * {@code builder} and {@code multipart} are incompatible; calling this will remove any existing {@code multipart} definitions.
     *
     * @param name     The state key ({@code ""} for default or format: {@code "prop1=value,prop2=value"}).
     * @param settings A callback which will be passed a {@link Variant}.
     * @return this
     */
    BlockStateBuilder variant(String name, Processor<Variant> settings);

    /**
     * Add a builder for the given state key, with multiple weighted random options.
     * Calling this multiple times for the same key will add to the list instead of overwriting.
     * {@code builder} and {@code multipart} are incompatible; calling this will remove any existing {@code multipart} definitions.
     *
     * @param name     The state key ({@code ""} for default or format: {@code "prop1=value,prop2=value"}).
     * @param settings A callback which will be passed a {@link Variant}.
     * @return this
     */
    BlockStateBuilder weightedVariant(String name, Processor<Variant> settings);

    /**
     * Add a multipart case.
     * Calling this multiple times will add to the list instead of overwriting.
     * {@code builder} and {@code multipart} are incompatible; calling this will remove any existing {@code builder} definitions.
     *
     * @param settings A callback which will be passed a {@link Case}.
     * @return this
     */
    BlockStateBuilder multipartCase(Processor<Case> settings);

    /**
     * Builder for a blockstate builder definition.
     *
     * @see BlockStateBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Variant {
        /**
         * Set the model this builder should use.
         *
         * @param id The model ID ({@code namespace:block|item/modelid}).
         * @return this
         */
        Variant model(Identifier id);

        /**
         * Set the rotation of this builder around the X axis in increments of 90deg.
         *
         * @param x The X axis rotation.
         * @return this
         * @throws IllegalArgumentException if {@code x} is not divisible by 90.
         */
        Variant rotationX(int x);

        /**
         * Set the rotation of this builder around the Y axis in increments of 90deg.
         *
         * @param y The Y axis rotation.
         * @return this
         * @throws IllegalArgumentException if {@code y} is not divisible by 90.
         */
        Variant rotationY(int y);

        /**
         * Set whether the textures of this model should not rotate with it.
         *
         * @param uvLock Whether to lock texture rotation or not.
         * @return this
         */
        Variant uvLock(boolean uvLock);

        /**
         * Set the relative weight of this builder.
         * This property will be ignored if the builder is not added with {@link BlockStateBuilder#weightedVariant}
         * or {@link Case#weightedApply}.
         *
         * @param weight The weight.
         * @return this
         */
        Variant weight(int weight);
    }

    /**
     * Builder for a blockstate multipart case.
     *
     * @see BlockStateBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Case {
        /**
         * Set the condition for this case to be applied.
         * Calling this multiple times with different keys will require all the specified properties to match.
         *
         * @param name  The state name (e.g. {@code facing}).
         * @param state The state value (e.g. {@code north}).
         * @return this
         */
        Case when(String name, String state);

        /**
         * Set the condition for this case to be applied.
         * Calling this multiple times with different keys will require at least one of the specified properties to match.
         *
         * @param name  The state name (e.g. {@code facing}).
         * @param state The state value (e.g. {@code north}).
         * @return this
         */
        Case whenAny(String name, String state);

        /**
         * Set the builder to be applied if the condition matches.
         * Calling this multiple times for the same key will overwrite the existing value.
         *
         * @param settings A callback which will be passed a {@link Variant}.
         * @return this
         */
        Case apply(Processor<Variant> settings);

        /**
         * Set the builder to be applied if the condition matches, with multiple weighted random options.
         * Calling this multiple times will add to the list instead of overwriting.
         *
         * @param settings A callback which will be passed a {@link Variant}.
         * @return this
         */
        Case weightedApply(Processor<Variant> settings);
    }
}
