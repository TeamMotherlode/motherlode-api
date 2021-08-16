package motherlode.base.impl.resource.builder.adapter.artifice.assets;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.assets.ModelBuilder;
import motherlode.base.api.resource.builder.assets.ModelElementBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeModelBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.ModelBuilder model) implements ModelBuilder {
    @Override
    public ModelBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.model));
        return this;
    }

    @Override
    public ModelBuilder parent(Identifier id) {
        this.model.parent(id);
        return this;
    }

    @Override
    public ModelBuilder texture(String name, Identifier path) {
        this.model.texture(name, path);
        return this;
    }

    @Override
    public ModelBuilder display(String name, Processor<Display> settings) {
        this.model.display(name, display -> settings.accept(new ArtificeDisplayAdapter(display)));
        return this;
    }

    @Override
    public ModelBuilder element(Processor<ModelElementBuilder> settings) {
        this.model.element(builder -> settings.accept(new ArtificeModelElementBuilderAdapter(builder)));
        return this;
    }

    @Override
    public ModelBuilder ambientOcclusion(boolean ambientOcclusion) {
        this.model.ambientocclusion(ambientOcclusion);
        return this;
    }

    @Override
    public ModelBuilder override(Processor<PropertyOverride> settings) {
        this.model.override(override -> settings.accept(new ArtificeOverrideAdapter(override)));
        return this;
    }

    public static record ArtificeDisplayAdapter(
        com.swordglowsblue.artifice.api.builder.assets.ModelBuilder.Display display) implements Display {
        @Override
        public Display with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.display));
            return this;
        }

        @Override
        public ModelBuilder.Display rotation(float x, float y, float z) {
            this.display.rotation(x, y, z);
            return this;
        }

        @Override
        public ModelBuilder.Display translation(float x, float y, float z) {
            this.display.translation(x, y, z);
            return this;
        }

        @Override
        public ModelBuilder.Display scale(float x, float y, float z) {
            this.display.scale(x, y, z);
            return this;
        }
    }

    public static record ArtificeOverrideAdapter(
        com.swordglowsblue.artifice.api.builder.assets.ModelBuilder.Override override) implements PropertyOverride {
        @Override
        public PropertyOverride with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.override));
            return this;
        }

        @Override
        public PropertyOverride predicate(String name, int value) {
            this.override.predicate(name, value);
            return this;
        }

        @Override
        public PropertyOverride model(Identifier id) {
            this.override.model(id);
            return this;
        }
    }
}
