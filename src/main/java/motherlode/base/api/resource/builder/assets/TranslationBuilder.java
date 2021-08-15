package motherlode.base.api.resource.builder.assets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for a translation file ({@code namespace:lang/language_code.json}).
 *
 * @see <a href="https://minecraft.gamepedia.com/Resource_pack#Language" target="_blank">Minecraft Wiki</a>
 */
@Environment(EnvType.CLIENT)
public interface TranslationBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    TranslationBuilder with(Processor<JsonBuilder> json);

    /**
     * Add a translation entry.
     *
     * @param key         The translation key (e.g. {@code block.example_mod.example_block}).
     * @param translation The translated string (e.g. {@code Example Block}).
     * @return this
     */
    TranslationBuilder entry(String key, String translation);
}
