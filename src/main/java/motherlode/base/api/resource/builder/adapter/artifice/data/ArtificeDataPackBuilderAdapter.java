package motherlode.base.api.resource.builder.adapter.artifice.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.DataPackBuilder;
import motherlode.base.api.resource.builder.Resource;
import motherlode.base.api.resource.builder.data.AdvancementBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;
import motherlode.base.api.resource.builder.data.TagBuilder;
import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.resource.JsonResource;

public record ArtificeDataPackBuilderAdapter(ArtificeResourcePack.ServerResourcePackBuilder builder) implements DataPackBuilder {
    @Override
    public void add(Identifier id, Resource<JsonObject> resource) {
        this.builder.add(id, new JsonResource<>(resource.get()));
    }

    @Override
    public DataPackBuilder addAdvancement(Identifier id, Processor<AdvancementBuilder> f) {
        this.builder.addAdvancement(id, advancement -> f.accept(new ArtificeAdvancementBuilderAdapter(advancement)));
        return this;
    }

    @Override
    public DataPackBuilder addLootTable(Identifier id, Processor<LootTableBuilder> f) {
        this.builder.addLootTable(id, table -> f.accept(new ArtificeLootTableBuilderAdapter(table)));
        return this;
    }

    @Override
    public DataPackBuilder addItemTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addItemTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addBlockTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addBlockTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addEntityTypeTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addEntityTypeTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addFluidTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addFluidTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }

    @Override
    public DataPackBuilder addFunctionTag(Identifier id, Processor<TagBuilder> f) {
        this.builder.addFunctionTag(id, tag -> f.accept(new ArtificeTagBuilderAdapter(tag)));
        return this;
    }
}
