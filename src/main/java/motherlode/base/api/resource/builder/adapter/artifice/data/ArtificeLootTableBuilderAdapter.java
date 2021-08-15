package motherlode.base.api.resource.builder.adapter.artifice.data;

import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;
import motherlode.base.api.resource.builder.data.LootTableBuilder;

public record ArtificeLootTableBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.LootTableBuilder table) implements LootTableBuilder {
    @Override
    public LootTableBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.table));
        return this;
    }

    @Override
    public LootTableBuilder type(Identifier id) {
        this.table.type(id);
        return this;
    }

    @Override
    public LootTableBuilder pool(Processor<Pool> settings) {
        this.table.pool(pool -> settings.accept(new ArtificePoolAdapter(pool)));
        return this;
    }

    public static record ArtificePoolAdapter(
        com.swordglowsblue.artifice.api.builder.data.LootTableBuilder.Pool pool) implements Pool {

        @Override
        public Pool with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.pool));
            return this;
        }

        @Override
        public Pool rolls(int rolls) {
            this.pool.rolls(rolls);
            return this;
        }

        @Override
        public Pool rolls(int min, int max) {
            this.pool.rolls(min, max);
            return this;
        }

        @Override
        public Pool bonusRolls(float rolls) {
            this.pool.bonusRolls(rolls);
            return this;
        }

        @Override
        public Pool bonusRolls(float min, float max) {
            this.pool.bonusRolls(min, max);
            return this;
        }

        @Override
        public Pool entry(Processor<Entry> settings) {
            this.pool.entry(entry -> settings.accept(new ArtificeEntryAdapter(entry)));
            return this;
        }

        @Override
        public Pool condition(Identifier id, Processor<JsonBuilder> settings) {
            this.pool.condition(id, builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
            return this;
        }

        public static record ArtificeEntryAdapter(
            com.swordglowsblue.artifice.api.builder.data.LootTableBuilder.Pool.Entry entry) implements Entry {
            @Override
            public Entry with(Processor<JsonBuilder> json) {
                json.accept(new ArtificeJsonBuilderAdapter(this.entry));
                return this;
            }

            @Override
            public Entry type(Identifier id) {
                this.entry.type(id);
                return this;
            }

            @Override
            public Entry name(Identifier id) {
                this.entry.name(id);
                return this;
            }

            @Override
            public Entry child(Processor<Entry> settings) {
                this.entry.child(entry -> settings.accept(new ArtificeEntryAdapter(entry)));
                return this;
            }

            @Override
            public Entry expand(boolean expand) {
                this.entry.expand(expand);
                return this;
            }

            @Override
            public Entry weight(int weight) {
                this.entry.weight(weight);
                return this;
            }

            @Override
            public Entry quality(int quality) {
                this.entry.quality(quality);
                return this;
            }

            @Override
            public Entry function(Identifier id, Processor<Function> settings) {
                this.entry.function(id, function -> settings.accept(new ArtificeFunctionAdapter(function)));
                return this;
            }

            @Override
            public Entry condition(Identifier id, Processor<JsonBuilder> settings) {
                this.entry.condition(id, builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
                return this;
            }

            public static record ArtificeFunctionAdapter(
                com.swordglowsblue.artifice.api.builder.data.LootTableBuilder.Pool.Entry.Function function) implements Function {
                @Override
                public Function with(Processor<JsonBuilder> json) {
                    json.accept(new ArtificeJsonBuilderAdapter(this.function));
                    return this;
                }

                @Override
                public Function condition(Identifier id, Processor<JsonBuilder> settings) {
                    this.function.condition(id, builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
                    return this;
                }
            }
        }
    }
}
