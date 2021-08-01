package motherlode.base.api.resource.builder.adapter.artifice.assets;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.Resource;
import motherlode.base.api.resource.builder.ResourcePackBuilder;
import motherlode.base.api.resource.builder.assets.BlockStateBuilder;
import motherlode.base.api.resource.builder.assets.ModelBuilder;
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
}
