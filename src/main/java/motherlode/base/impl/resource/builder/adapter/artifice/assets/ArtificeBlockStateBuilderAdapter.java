package motherlode.base.impl.resource.builder.adapter.artifice.assets;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.assets.BlockStateBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeBlockStateBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.BlockStateBuilder builder) implements BlockStateBuilder {
    @Override
    public BlockStateBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.builder));
        return this;
    }

    @Override
    public BlockStateBuilder variant(String name, Processor<Variant> settings) {
        this.builder.variant(name, variant -> settings.accept(new ArtificeVariantAdapter(variant)));
        return this;
    }

    @Override
    public BlockStateBuilder weightedVariant(String name, Processor<Variant> settings) {
        this.builder.variant(name, variant -> settings.accept(new ArtificeVariantAdapter(variant)));
        return this;
    }

    @Override
    public BlockStateBuilder multipartCase(Processor<Case> settings) {
        this.builder.multipartCase(builder -> settings.accept(new ArtificeCaseAdapter(builder)));
        return this;
    }

    public static record ArtificeVariantAdapter(
        com.swordglowsblue.artifice.api.builder.assets.BlockStateBuilder.Variant builder) implements Variant {
        @Override
        public Variant with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.builder));
            return this;
        }

        @Override
        public Variant model(Identifier id) {
            this.builder.model(id);
            return this;
        }

        @Override
        public Variant rotationX(int x) {
            this.builder.rotationX(x);
            return this;
        }

        @Override
        public Variant rotationY(int y) {
            this.builder.rotationY(y);
            return this;
        }

        @Override
        public Variant uvLock(boolean uvLock) {
            this.builder.uvlock(uvLock);
            return this;
        }

        @Override
        public Variant weight(int weight) {
            this.builder.weight(weight);
            return this;
        }
    }

    public static record ArtificeCaseAdapter(
        com.swordglowsblue.artifice.api.builder.assets.BlockStateBuilder.Case builder) implements Case {
        @Override
        public Case with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.builder));
            return this;
        }

        @Override
        public Case when(String name, String state) {
            this.builder.when(name, state);
            return this;
        }

        @Override
        public Case whenAny(String name, String state) {
            this.builder.whenAny(name, state);
            return this;
        }

        @Override
        public Case apply(Processor<Variant> settings) {
            this.builder.apply(variant -> settings.accept(new ArtificeVariantAdapter(variant)));
            return this;
        }

        @Override
        public Case weightedApply(Processor<Variant> settings) {
            this.builder.weightedApply(variant -> settings.accept(new ArtificeVariantAdapter(variant)));
            return this;
        }
    }
}
