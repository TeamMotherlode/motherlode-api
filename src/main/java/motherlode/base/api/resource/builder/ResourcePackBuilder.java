package motherlode.base.api.resource.builder;

import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.assets.AnimationBuilder;
import motherlode.base.api.resource.builder.assets.BlockStateBuilder;
import motherlode.base.api.resource.builder.assets.ModelBuilder;
import motherlode.base.api.resource.builder.assets.ParticleBuilder;
import motherlode.base.api.resource.builder.assets.TranslationBuilder;

/**
 * Passed to resource construction callbacks to register client-side resources.
 */
@Environment(EnvType.CLIENT)
public interface ResourcePackBuilder extends AssetPackBuilder {
    /**
     * Add an item model for the given item ID.
     *
     * @param id An item ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ModelBuilder} to create the item model.
     * @return this
     */
    ResourcePackBuilder addItemModel(Identifier id, Processor<ModelBuilder> f);

    /**
     * Add a block model for the given block ID.
     *
     * @param id A block ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ModelBuilder} to create the block model.
     * @return this
     */
    ResourcePackBuilder addBlockModel(Identifier id, Processor<ModelBuilder> f);

    /**
     * Add a blockstate definition for the given block ID.
     *
     * @param id A block ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link BlockStateBuilder} to create the blockstate definition.
     * @return this
     */
    ResourcePackBuilder addBlockState(Identifier id, Processor<BlockStateBuilder> f);

    /**
     * Add a translation file for the given language.
     *
     * @param id The namespace and language code of the desired language.
     * @param f  A callback which will be passed a {@link TranslationBuilder} to create the language file.
     * @return this
     */
    ResourcePackBuilder addTranslations(Identifier id, Processor<TranslationBuilder> f);

    /**
     * Add a particle definition for the given particle ID.
     *
     * @param id A particle ID, which will be converted into the correct path.
     * @param f  A callback which will be passed a {@link ParticleBuilder} to create the particle definition.
     * @return this
     */
    ResourcePackBuilder addParticle(Identifier id, Processor<ParticleBuilder> f);

    /**
     * Add a texture animation for the given item ID.
     *
     * @param id An item ID, which will be converted into the correct path.
     * @param f  A callback which will be passed an {@link AnimationBuilder} to create the texture animation.
     * @return this
     */
    ResourcePackBuilder addItemAnimation(Identifier id, Processor<AnimationBuilder> f);

    /**
     * Add a texture animation for the given block ID.
     *
     * @param id A block ID, which will be converted into the correct path.
     * @param f  A callback which will be passed an {@link AnimationBuilder} to create the texture animation.
     * @return this
     */
    ResourcePackBuilder addBlockAnimation(Identifier id, Processor<AnimationBuilder> f);

    /**
     * Add a custom language. Translations must be added separately with {@link #addTranslations}.
     *
     * @param definition A {@link LanguageDefinition} for the desired language.
     * @return this
     */
    ResourcePackBuilder addLanguage(LanguageDefinition definition);

    /**
     * Add a custom language. Translations must be added separately with {@link #addTranslations}.
     *
     * @param code   The language code for the custom language (i.e. {@code en_us}). Must be all lowercase alphanumerical / underscores.
     * @param region The name of the language region (i.e. United States).
     * @param name   The name of the language (i.e. English).
     * @param rtl    Whether the language is written right-to-left instead of left-to-right.
     * @return this
     */
    ResourcePackBuilder addLanguage(String code, String region, String name, boolean rtl);
}
