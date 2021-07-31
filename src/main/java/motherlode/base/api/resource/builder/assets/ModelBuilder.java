package motherlode.base.api.resource.builder.assets;

import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import com.swordglowsblue.artifice.api.builder.assets.ModelElementBuilder;

/**
 * Builder for a model file ({@code namespace:models/block|item/model_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Model" target="_blank">Minecraft Wiki</a>
 */
@Environment(EnvType.CLIENT)
public interface ModelBuilder {
    /**
     * Set the parent model for this model to inherit from.
     *
     * @param id The parent model ID ({@code namespace:block|item/model_id}
     * @return this
     */
    ModelBuilder parent(Identifier id);

    /**
     * Associate a texture with the given variable name.
     *
     * @param name The texture variable name.
     * @param path The texture ID ({@code namespace:type/texture_id}).
     * @return this
     */
    ModelBuilder texture(String name, Identifier path);

    /**
     * Modify the display transformation properties of this model for the given display position.
     *
     * @param name     The position name (e.g. {@code thirdperson_righthand}).
     * @param settings A callback which will be passed a {@link ModelBuilder.Display}.
     * @return this
     */
    ModelBuilder display(String name, Processor<Display> settings);

    /**
     * Add an element to this model.
     *
     * @param settings A callback which will be passed a {@link ModelElementBuilder}.
     * @return this
     */
    ModelBuilder element(Processor<ModelElementBuilder> settings);

    /**
     * Set whether this model should use ambient occlusion for lighting. Only applicable for block models.
     *
     * @param ambientOcclusion Whether to use ambient occlusion.
     * @return this
     */
    ModelBuilder ambientOcclusion(boolean ambientOcclusion);

    /**
     * Add a property override to this model. Only applicable for item models.
     *
     * @param settings A callback which will be passed a {@link ModelBuilder.Override}.
     * @return this
     */
    ModelBuilder override(Processor<Override> settings);

    /**
     * Builder for model display settings.
     *
     * @see ModelBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Display {
        /**
         * Set the rotation of this model around each axis.
         *
         * @param x The rotation around the X axis.
         * @param y The rotation around the Y axis.
         * @param z The rotation around the Z axis.
         * @return this
         */
        Display rotation(float x, float y, float z);

        /**
         * Set the translation of this model along each axis.
         *
         * @param x The translation along the X axis. Clamped to between -80 and 80.
         * @param y The translation along the Y axis. Clamped to between -80 and 80.
         * @param z The translation along the Z axis. Clamped to between -80 and 80.
         * @return this
         */
        Display translation(float x, float y, float z);

        /**
         * Set the scale of this model on each axis.
         *
         * @param x The scale on the X axis. Clamped to &lt;= 4.
         * @param y The scale on the Y axis. Clamped to &lt;= 4.
         * @param z The scale on the Z axis. Clamped to &lt;= 4.
         * @return this
         */
        Display scale(float x, float y, float z);
    }

    /**
     * Builder for an item model property override.
     *
     * @see ModelBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Override {
        /**
         * Set the required value of the given property.
         * Calling this multiple times will require all properties to match.
         *
         * @param name  The item property tag.
         * @param value The required integer value.
         * @return this
         * @see <a href="https://minecraft.gamepedia.com/Model#Item_tags">Minecraft Wiki</a>
         */
        Override predicate(String name, int value);

        /**
         * Set the model to be used instead of this one if the predicate matches.
         *
         * @param id The model id ({@code namespace:block|item/model_id}).
         * @return this
         */
        ModelBuilder.Override model(Identifier id);
    }
}
