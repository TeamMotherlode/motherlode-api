package motherlode.base.api.resource;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.ResourcePackBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.assets.ArtificeModelBuilderAdapter;
import motherlode.base.api.resource.builder.assets.ModelBuilder;
import com.swordglowsblue.artifice.api.ArtificeResourcePack.ClientResourcePackBuilder;

public record ArtificeResourceBuilderAdapter(ClientResourcePackBuilder builder) implements ResourcePackBuilder {
    @Override
    public ResourcePackBuilder addItemModel(Identifier id, Processor<ModelBuilder> f) {
        this.builder.addItemModel(id, model -> f.accept(new ArtificeModelBuilderAdapter(model)));
        return this;
    }

    @Override
    public ResourcePackBuilder addBlockModel(Identifier id, Processor<ModelBuilder> f) {
        this.builder.addBlockModel(id, model -> f.accept(new ArtificeModelBuilderAdapter(model)));
        return null;
    }
}
