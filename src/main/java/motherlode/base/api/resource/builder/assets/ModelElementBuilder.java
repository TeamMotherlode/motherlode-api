package motherlode.base.api.resource.builder.assets;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import com.swordglowsblue.artifice.api.builder.assets.ModelBuilder;

/**
 * Builder for an individual model element.
 *
 * @see ModelBuilder
 */
@Environment(EnvType.CLIENT)
public interface ModelElementBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    ModelElementBuilder with(Processor<JsonBuilder> json);

    /**
     * Set the start point of this cuboid.
     *
     * @param x The start point on the X axis. Clamped to between -16 and 32.
     * @param y The start point on the Y axis. Clamped to between -16 and 32.
     * @param z The start point on the Z axis. Clamped to between -16 and 32.
     * @return this
     */
    ModelElementBuilder from(float x, float y, float z);

    /**
     * Set the end point of this cuboid.
     *
     * @param x The end point on the X axis. Clamped to between -16 and 32.
     * @param y The end point on the Y axis. Clamped to between -16 and 32.
     * @param z The end point on the Z axis. Clamped to between -16 and 32.
     * @return this
     */
    ModelElementBuilder to(float x, float y, float z);

    /**
     * Set the rotation of this cuboid.
     *
     * @param settings A callback which will be passed a {@link Rotation}.
     * @return this
     */
    ModelElementBuilder rotation(Processor<Rotation> settings);

    /**
     * Set whether to render shadows on this cuboid.
     *
     * @param shade Whether to shade (default: true).
     * @return this
     */
    ModelElementBuilder shade(boolean shade);

    /**
     * Define properties of the face in the given direction.
     *
     * @param side     The direction of the face.
     * @param settings A callback which will be passed a {@link Face}.
     * @return this
     */
    ModelElementBuilder face(Direction side, Processor<Face> settings);

    /**
     * Builder for model element rotation.
     *
     * @see ModelElementBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Rotation {
        /**
         * Allows adding any JSON properties.
         *
         * @param json A callback which will be passed a {@link JsonBuilder}.
         * @return this
         */
        Rotation with(Processor<JsonBuilder> json);

        /**
         * Set the origin point of this rotation.
         *
         * @param x The origin point on the X axis. Clamped to between -16 and 32.
         * @param y The origin point on the Y axis. Clamped to between -16 and 32.
         * @param z The origin point on the Z axis. Clamped to between -16 and 32.
         * @return this
         */
        Rotation origin(float x, float y, float z);

        /**
         * Set the axis to rotate around.
         *
         * @param axis The axis.
         * @return this
         */
        Rotation axis(Direction.Axis axis);

        /**
         * Set the rotation angle in 22.5deg increments.
         *
         * @param angle The angle.
         * @return this
         * @throws IllegalArgumentException if the angle is not between -45 and 45 or is not divisible by 22.5.
         */
        Rotation angle(float angle);

        /**
         * Set whether to rescale this element's faces across the whole block.
         *
         * @param rescale Whether to rescale (default: false).
         * @return this
         */
        Rotation rescale(boolean rescale);
    }

    /**
     * Builder for a model element face.
     *
     * @see ModelElementBuilder
     */
    @Environment(EnvType.CLIENT)
    interface Face {
        /**
         * Allows adding any JSON properties.
         *
         * @param json A callback which will be passed a {@link JsonBuilder}.
         * @return this
         */
        Face with(Processor<JsonBuilder> json);

        /**
         * Set the texture UV to apply to this face. Detected by position within the block if not specified.
         *
         * @param x1 The start point on the X axis. Clamped to between 0 and 16.
         * @param x2 The end point on the X axis. Clamped to between 0 and 16.
         * @param y1 The start point on the Y axis. Clamped to between 0 and 16.
         * @param y2 The end point on the Y axis. Clamped to between 0 and 16.
         * @return this
         */
        Face uv(int x1, int x2, int y1, int y2);

        /**
         * Set the texture of this face to the given texture variable.
         *
         * @param varName The variable name (e.g. {@code particle}).
         * @return this
         */
        Face texture(String varName);

        /**
         * Set the texture of this face to the given texture id.
         *
         * @param path The texture path ({@code namespace:type/texture_id}).
         * @return this
         */
        Face texture(Identifier path);

        /**
         * Set the side of the block for which this face should be culled if touching another block.
         *
         * @param side The side to cull on (defaults to the side specified for this face).
         * @return this
         */
        Face cullFace(Direction side);

        /**
         * Set the rotation of this face's texture in 90deg increments.
         *
         * @param rotation The texture rotation.
         * @return this
         * @throws IllegalArgumentException if the rotation is not between 0 and 270 or is not divisible by 90.
         */
        Face rotation(int rotation);

        /**
         * Set the tint index of this face. Used by color providers and only applicable for blocks with defined color providers (e.g. grass).
         *
         * @param tintIndex The tint index.
         * @return this
         */
        Face tintIndex(int tintIndex);
    }
}
