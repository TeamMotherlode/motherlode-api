package motherlode.base.impl.resource.builder.adapter.artifice.assets;

import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.assets.TranslationBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeTranslationBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.TranslationBuilder translations) implements TranslationBuilder {
    @Override
    public TranslationBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.translations));
        return this;
    }

    @Override
    public TranslationBuilder entry(String key, String translation) {
        this.translations.entry(key, translation);
        return this;
    }
}
