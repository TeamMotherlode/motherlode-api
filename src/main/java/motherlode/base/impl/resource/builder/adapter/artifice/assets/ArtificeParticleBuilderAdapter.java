package motherlode.base.impl.resource.builder.adapter.artifice.assets;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.assets.ParticleBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeParticleBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.ParticleBuilder particle) implements ParticleBuilder {
    @Override
    public ParticleBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.particle));
        return this;
    }

    @Override
    public ParticleBuilder texture(Identifier id) {
        this.particle.texture(id);
        return this;
    }
}
