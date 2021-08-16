package motherlode.base.impl.resource.builder.adapter.general.data;

import java.util.Optional;
import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class CaptureLootTableType implements LootTableBuilder {
    private Optional<Identifier> type;

    public CaptureLootTableType() {
        this.type = Optional.empty();
    }

    @Override
    public LootTableBuilder with(Processor<JsonBuilder> json) {
        return this;
    }

    @Override
    public LootTableBuilder type(Identifier id) {
        this.type = Optional.of(id);
        return this;
    }

    @Override
    public LootTableBuilder pool(Processor<Pool> settings) {
        return this;
    }

    public Identifier getType() {
        if (this.type.isEmpty())
            throw new IllegalArgumentException("Loot table without type");

        return this.type.get();
    }

    public static Identifier getType(Processor<LootTableBuilder> f) {
        CaptureLootTableType capture = new CaptureLootTableType();
        f.accept(capture);

        return capture.getType();
    }
}
