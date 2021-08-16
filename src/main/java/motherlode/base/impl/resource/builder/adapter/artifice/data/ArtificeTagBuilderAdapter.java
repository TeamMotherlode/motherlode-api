package motherlode.base.impl.resource.builder.adapter.artifice.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.TagBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeTagBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.TagBuilder tag) implements TagBuilder {
    @Override
    public TagBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.tag));
        return this;
    }

    @Override
    public TagBuilder replace(boolean replace) {
        this.tag.replace(replace);
        return this;
    }

    @Override
    public TagBuilder value(Identifier id) {
        this.tag.value(id);
        return this;
    }

    @Override
    public TagBuilder values(Identifier... ids) {
        this.tag.values(ids);
        return this;
    }

    @Override
    public TagBuilder include(Identifier tagId) {
        this.tag.include(tagId);
        return this;
    }
}
