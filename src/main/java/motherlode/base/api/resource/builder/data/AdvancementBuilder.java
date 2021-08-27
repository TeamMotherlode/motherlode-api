package motherlode.base.api.resource.builder.data;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for advancement files ({@code namespace:advancements/advancement_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Advancements#JSON_Format" target="_blank">Minecraft Wiki</a>
 */
public interface AdvancementBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    AdvancementBuilder with(Processor<JsonBuilder> json);

    /**
     * Set the display options for this advancement.
     *
     * @param settings A callback which will be passed a {@link Display}.
     * @return this
     */
    AdvancementBuilder display(Processor<Display> settings);

    /**
     * Set the parent advancement for this to inherit from.
     *
     * @param id The parent advancement ID ({@code namespace:advancement_id}).
     * @return this
     */
    AdvancementBuilder parent(Identifier id);

    /**
     * Add a criterion for this advancement to be received.
     *
     * @param name     The name of this criterion.
     * @param settings A callback which will be passed a {@link Criteria}.
     * @return this
     */
    AdvancementBuilder criteria(String name, Processor<Criteria> settings);

    /**
     * Set which criteria are required to receive this advancement.
     * Passing multiple criteria names will allow the advancement to be received if any of the given criteria are completed.
     * Calling this multiple times will add a new set of requirements. Each set must have at least one contained criteria completed
     * to receive the advancement.
     * If this is not called, all criteria will be required by default.
     *
     * @param anyOf A list of criteria names, any of which can be completed to fulfill this requirement.
     * @return this
     */
    AdvancementBuilder requirement(String... anyOf);

    /**
     * Builder for advancement display properties.
     *
     * @see AdvancementBuilder
     */
    interface Display {
        /**
         * Allows adding any JSON properties.
         *
         * @param json A callback which will be passed a {@link JsonBuilder}.
         * @return this
         */
        Display with(Processor<JsonBuilder> json);

        /**
         * Set the icon item of this advancement.
         *
         * @param item The item ID.
         * @return this
         */
        Display icon(Identifier item);

        /**
         * Set the icon item of this advancement.
         *
         * @param item The item ID.
         * @param nbt  A string containing the JSON-serialized NBT of the item.
         * @return this
         */
        Display icon(Identifier item, String nbt);

        /**
         * Set the title of this advancement.
         *
         * @param title The title.
         * @return this
         */
        Display title(String title);

        /**
         * Set the title of this advancement.
         *
         * @param title The title.
         * @return this
         */
        Display title(Text title);

        /**
         * Set the frame type of this advancement.
         *
         * @param frame The frame type.
         * @return this
         */
        Display frame(Frame frame);

        /**
         * Set the background texture of this advancement. Only applicable for root advancements.
         *
         * @param id The texture path ({@code namespace:textures/gui/advancements/backgrounds/background_name.png}).
         * @return this
         */
        Display background(Identifier id);

        /**
         * Set the description of this advancement.
         *
         * @param description The description.
         * @return this
         */
        Display description(String description);

        /**
         * Set the description of this advancement.
         *
         * @param description The description.
         * @return this
         */
        Display description(Text description);

        /**
         * Set whether this advancement should show a popup onscreen when received.
         *
         * @param show Whether to show the toast.
         * @return this
         */
        Display showToast(boolean show);

        /**
         * Set whether achieving this advancement should post a message in chat.
         *
         * @param announce Whether to announce.
         * @return this
         */
        Display announceToChat(boolean announce);

        /**
         * Set whether this advancement should be hidden from the advancement menu until received.
         *
         * @param hidden Whether to hide.
         * @return this
         */
        Display hidden(boolean hidden);

        /**
         * Options for {@link #frame}.
         */
        enum Frame {
            CHALLENGE("challenge"),
            GOAL("goal"),
            TASK("task");

            /**
             * The name of this frame when outputted to JSON.
             */
            private final String name;

            Frame(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }
    }

    /**
     * Builder for advancement criteria.
     *
     * @see AdvancementBuilder
     * @see <a href="https://minecraft.gamepedia.com/Advancements#List_of_triggers" target="_blank">Minecraft Wiki</a>
     */
    interface Criteria {
        /**
         * Allows adding any JSON properties.
         *
         * @param json A callback which will be passed a {@link JsonBuilder}.
         * @return this
         */
        Criteria with(Processor<JsonBuilder> json);

        /**
         * Set the trigger condition of this criterion.
         *
         * @param id The trigger ID ({@code namespace:trigger_id}).
         * @return this
         * @see <a href="https://minecraft.gamepedia.com/Advancements#List_of_triggers" target="_blank">Minecraft Wiki</a>
         */
        Criteria trigger(Identifier id);

        /**
         * Set the condition values for the given trigger.
         * These vary from trigger to trigger, so this falls through to direct JSON building.
         *
         * @param settings A callback which will be passed a {@link JsonBuilder}.
         * @return this
         * @see <a href="https://minecraft.gamepedia.com/Advancements#List_of_triggers" target="_blank">Minecraft Wiki</a>
         */
        Criteria conditions(Processor<JsonBuilder> settings);
    }
}
