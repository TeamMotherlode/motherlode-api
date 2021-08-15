package motherlode.base.api.resource.builder.assets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for a texture animation file ({@code namespace:textures/block|item/texture_id.mcmeta}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Resource_pack#Animation" target="_blank">Minecraft Wiki</a>
 */
@Environment(EnvType.CLIENT)
public interface AnimationBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    AnimationBuilder with(Processor<JsonBuilder> json);

    /**
     * Set whether this animation should interpolate between frames with a frame time &gt; 1 between them.
     *
     * @param interpolate Whether to interpolate (default: false).
     * @return this
     */
    AnimationBuilder interpolate(boolean interpolate);

    /**
     * Set the frame width of this animation as a ratio of its frame height.
     *
     * @param width The width (default: unset).
     * @return this
     */
    AnimationBuilder width(int width);

    /**
     * Set the frame height of this animation as a ratio of its total pixel height.
     *
     * @param height The height (default: unset).
     * @return this
     */
    AnimationBuilder height(int height);

    /**
     * Set the default time to spend on each frame.
     *
     * @param frameTime The number of ticks the frame should last (default: 1)
     * @return this
     */
    AnimationBuilder frameTime(int frameTime);

    /**
     * Set the frame order and/or frame-specific timings of this animation.
     *
     * @param settings A callback which will be passed a {@link FrameOrder}.
     * @return this
     */
    AnimationBuilder frames(Processor<FrameOrder> settings);

    /**
     * Builder for the `frames` property of a texture animation file.
     *
     * @see AnimationBuilder
     */
    @Environment(EnvType.CLIENT)
    interface FrameOrder {
        /**
         * Add a single frame to end of the order.
         *
         * @param index The frame index (starting from 0 at the top of the texture).
         * @return this
         */
        FrameOrder frame(int index);

        /**
         * Add a single frame to the end of the order, with a modified frame time specified.
         *
         * @param index     The frame index (starting from 0 at the top of the texture).
         * @param frameTime The number of ticks the frame should last.
         * @return this
         */
        FrameOrder frame(int index, int frameTime);

        /**
         * Add a range of frame indexes to this animation.
         *
         * @param start        The first frame index to add (inclusive).
         * @param endExclusive The last frame index to add (exclusive).
         * @return this
         * @see FrameOrder#frame
         */
        FrameOrder frameRange(int start, int endExclusive);
    }
}
