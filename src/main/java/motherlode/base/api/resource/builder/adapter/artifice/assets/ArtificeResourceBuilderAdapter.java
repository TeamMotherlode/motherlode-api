package motherlode.base.api.resource.builder.adapter.artifice.assets;

import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.Resource;
import motherlode.base.api.resource.builder.ResourcePackBuilder;
import motherlode.base.api.resource.builder.assets.AnimationBuilder;
import motherlode.base.api.resource.builder.assets.BlockStateBuilder;
import motherlode.base.api.resource.builder.assets.ModelBuilder;
import motherlode.base.api.resource.builder.assets.ParticleBuilder;
import motherlode.base.api.resource.builder.assets.TranslationBuilder;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.ArtificeResourcePack.ClientResourcePackBuilder;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public record ArtificeResourceBuilderAdapter(ClientResourcePackBuilder builder) implements ResourcePackBuilder {
    @Override
    public void add(Identifier id, Resource<JsonObject> resource) {
        this.builder.add(id, new JsonResource<>(resource.get()));
    }

    @Override
    public ResourcePackBuilder addItemModel(Identifier id, Processor<ModelBuilder> f) {
        this.builder.addItemModel(id, model -> f.accept(new ArtificeModelBuilderAdapter(model)));
        return this;
    }

    @Override
    public ResourcePackBuilder addBlockModel(Identifier id, Processor<ModelBuilder> f) {
        this.builder.addBlockModel(id, model -> f.accept(new ArtificeModelBuilderAdapter(model)));
        return this;
    }

    @Override
    public ResourcePackBuilder addBlockState(Identifier id, Processor<BlockStateBuilder> f) {
        this.builder.addBlockState(id, state -> f.accept(new ArtificeBlockStateBuilderAdapter(state)));
        return this;
    }

    @Override
    public ResourcePackBuilder addTranslations(Identifier id, Processor<TranslationBuilder> f) {
        this.builder.addTranslations(id, translations -> f.accept(new ArtificeTranslationBuilderAdapter(translations)));
        return this;
    }

    @Override
    public ResourcePackBuilder addParticle(Identifier id, Processor<ParticleBuilder> f) {
        this.builder.addParticle(id, particle -> f.accept(new ArtificeParticleBuilderAdapter(particle)));
        return this;
    }

    @Override
    public ResourcePackBuilder addItemAnimation(Identifier id, Processor<AnimationBuilder> f) {
        this.builder.addItemAnimation(id, animation -> f.accept(new ArtificeAnimationBuilderAdapter(animation)));
        return this;
    }

    @Override
    public ResourcePackBuilder addBlockAnimation(Identifier id, Processor<AnimationBuilder> f) {
        this.builder.addBlockAnimation(id, animation -> f.accept(new ArtificeAnimationBuilderAdapter(animation)));
        return this;
    }

    @Override
    public ResourcePackBuilder addLanguage(LanguageDefinition definition) {
        this.builder.addLanguage(definition);
        return this;
    }

    @Override
    public ResourcePackBuilder addLanguage(String code, String region, String name, boolean rtl) {
        this.builder.addLanguage(code, region, name, rtl);
        return this;
    }
}
