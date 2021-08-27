package motherlode.base.api.resource.builder.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for tag files ({@code namespace:tags/type/tag_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Tag" target="_blank">Minecraft Wiki</a>
 */
public interface TagBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    TagBuilder with(Processor<JsonBuilder> json);

    /**
     * Set whether this tag should override or append to versions of the same tag in lower priority data packs.
     *
     * @param replace Whether to replace.
     * @return this
     */
    TagBuilder replace(boolean replace);

    /**
     * Add a value to this tag.
     *
     * @param id The value ID.
     * @return this
     */
    TagBuilder value(Identifier id);

    /**
     * Add multiple values to this tag.
     *
     * @param ids The value IDs.
     * @return this
     */
    TagBuilder values(Identifier... ids);

    /**
     * Include another tag into this tag's values.
     *
     * @param tagId The tag ID.
     * @return this
     */
    TagBuilder include(Identifier tagId);
}
