package motherlode.base.api.resource.builder.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for loot table files ({@code namespace:loot_tables/type/loot_table_id.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Loot_table" target="_blank">Minecraft Wiki</a>
 */
public interface LootTableBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    LootTableBuilder with(Processor<JsonBuilder> json);

    /**
     * Set the type of this loot table.
     *
     * @param id The type ID.
     * @return this
     */
    LootTableBuilder type(Identifier id);

    /**
     * Add a pool to this loot table.
     *
     * @param settings A callback which will be passed a {@link Pool}.
     * @return this
     */
    LootTableBuilder pool(Processor<Pool> settings);

    interface Pool {
        /**
         * Allows adding any JSON properties.
         *
         * @param json A callback which will be passed a {@link JsonBuilder}.
         * @return this
         */
        Pool with(Processor<JsonBuilder> json);

        /**
         * Set the number of rolls to apply this pool for.
         *
         * @param rolls The number of rolls.
         * @return this
         */
        Pool rolls(int rolls);

        /**
         * Set the number of rolls to apply this pool for as a range from which to randomly select a number.
         *
         * @param min The minimum number of rolls (inclusive).
         * @param max The maximum number of rolls (inclusive).
         * @return this
         */
        Pool rolls(int min, int max);

        /**
         * Set the number of bonus rolls to apply this pool for per point of luck.
         *
         * @param rolls The number of rolls.
         * @return this
         */
        Pool bonusRolls(float rolls);

        /**
         * Set the number of bonus rolls to apply this pool for per point of luck as a range from which to randomly select a number.
         *
         * @param min The minimum number of rolls (inclusive).
         * @param max The maximum number of rolls (inclusive).
         * @return this
         */
        Pool bonusRolls(float min, float max);

        /**
         * Add an entry to this pool.
         *
         * @param settings A callback which will be passed an {@link Entry}.
         * @return this
         */
        Pool entry(Processor<Entry> settings);

        /**
         * Add a condition to this pool. All conditions must pass for the pool to be used.
         * The specific properties of this vary by condition, so this falls through to direct JSON building.
         *
         * @param id       The condition ID.
         * @param settings A callback which will be passed a {@link JsonBuilder}.
         * @return this
         * @see <a href="https://minecraft.gamepedia.com/Loot_table#Conditions" target="_blank">Minecraft Wiki</a>
         */
        Pool condition(Identifier id, Processor<JsonBuilder> settings);

        /**
         * Builder for a loot table pool entry.
         *
         * @see Pool
         */
        interface Entry {
            /**
             * Allows adding any JSON properties.
             *
             * @param json A callback which will be passed a {@link JsonBuilder}.
             * @return this
             */
            Entry with(Processor<JsonBuilder> json);

            /**
             * Set the type of this entry.
             *
             * @param id The type ID.
             * @return this
             */
            Entry type(Identifier id);

            /**
             * Set the name of this entry's value. Expected value varies by type.
             *
             * @param id The name of the value as an ID.
             * @return this
             */
            Entry name(Identifier id);

            /**
             * Add a child to this entry.
             *
             * @param settings A callback which will be passed an {@link Entry}.
             * @return this
             */
            Entry child(Processor<Entry> settings);

            /**
             * For type {@code tag}, set whether to use the given tag as a list of equally weighted options or to use all tag entries.
             *
             * @param expand Whether to expand.
             * @return this
             */
            Entry expand(boolean expand);

            /**
             * Set the relative weight of this entry.
             *
             * @param weight The weight.
             * @return this
             */
            Entry weight(int weight);

            /**
             * Set the quality of this entry (modifies the weight based on the player's luck attribute).
             *
             * @param quality The quality.
             * @return this
             */
            Entry quality(int quality);

            /**
             * Add a function to be applied to this entry.
             *
             * @param id       The function ID.
             * @param settings A callback which will be passed a {@link Function}.
             * @return this
             * @see <a href="https://minecraft.gamepedia.com/Loot_table#Functions" target="_blank">Minecraft Wiki</a>
             */
            Entry function(Identifier id, Processor<Function> settings);

            /**
             * Add a condition to this entry. All conditions must pass for the entry to be used.
             * The specific properties of this vary by condition, so this falls through to direct JSON building.
             *
             * @param id       The condition ID.
             * @param settings A callback which will be passed a {@link JsonBuilder}.
             * @return this
             * @see <a href="https://minecraft.gamepedia.com/Loot_table#Conditions" target="_blank">Minecraft Wiki</a>
             */
            Entry condition(Identifier id, Processor<JsonBuilder> settings);

            /**
             * Builder for loot table entry functions.
             *
             * @see Entry
             * @see <a href="https://minecraft.gamepedia.com/Loot_table#Functions" target="_blank">Minecraft Wiki</a>
             */
            interface Function {
                /**
                 * Allows adding any JSON properties.
                 *
                 * @param json A callback which will be passed a {@link JsonBuilder}.
                 * @return this
                 */
                Function with(Processor<JsonBuilder> json);

                /**
                 * Add a condition to this function. All conditions must pass for the function to be applied.
                 * The specific properties of this vary by condition, so this falls through to direct JSON building.
                 *
                 * @param id       The condition ID.
                 * @param settings A callback which will be passed a {@link JsonBuilder}.
                 * @return this
                 * @see <a href="https://minecraft.gamepedia.com/Loot_table#Conditions" target="_blank">Minecraft Wiki</a>
                 */
                Function condition(Identifier id, Processor<JsonBuilder> settings);
            }
        }
    }
}
