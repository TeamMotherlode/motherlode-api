package motherlode.base.impl.resource.builder.adapter.arrp.data;

import java.util.function.Supplier;
import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonArrayBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.data.LootTableBuilder;
import motherlode.base.impl.resource.builder.JsonArrayBuilderImpl;
import motherlode.base.impl.resource.builder.JsonBuilderImpl;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJCondition;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJEntry;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJFunction;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJLootTable;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJPool;
import motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable.ArrpJRoll;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public record ArrpLootTableBuilderAdapter(ArrpJLootTable table) implements LootTableBuilder {
    @Override
    public LootTableBuilder with(Processor<JsonBuilder> json) {
        return this;
    }

    @Override
    public LootTableBuilder type(Identifier id) {
        return this;
    }

    @Override
    public LootTableBuilder pool(Processor<Pool> settings) {
        ArrpJPool pool = ArrpJLootTable.pool();
        settings.accept(new ArrpPoolAdapter(pool));

        this.table.pool(pool);
        return this;
    }

    public static record ArrpPoolAdapter(ArrpJPool pool) implements Pool {
        @Override
        public Pool with(Processor<JsonBuilder> json) {
            return this;
        }

        @Override
        public Pool rolls(int rolls) {
            this.pool.rolls(rolls);
            return this;
        }

        @Override
        public Pool rolls(int min, int max) {
            this.pool.rolls(new ArrpJRoll(min, max));
            return this;
        }

        @Override
        public Pool bonusRolls(float rolls) {
            this.pool.bonus((int) rolls);
            return this;
        }

        @Override
        public Pool bonusRolls(float min, float max) {
            this.pool.bonus(new ArrpJRoll((int) min, (int) max));
            return this;
        }

        @Override
        public Pool entry(Processor<Entry> settings) {
            ArrpJEntry entry = ArrpJLootTable.entry();
            settings.accept(new ArrpEntryAdapter(entry));

            this.pool.entry(entry);
            return this;
        }

        @Override
        public Pool condition(Identifier id, Processor<JsonBuilder> settings) {
            ArrpJCondition condition = ArrpJLootTable.predicate(id.toString());
            settings.accept(new ArrpConditionAdapter(condition));

            this.pool.condition(condition);
            return this;
        }

        public static record ArrpEntryAdapter(ArrpJEntry entry) implements Entry {
            @Override
            public Entry with(Processor<JsonBuilder> json) {
                return this;
            }

            @Override
            public Entry type(Identifier id) {
                this.entry.type(id.toString());
                return this;
            }

            @Override
            public Entry name(Identifier id) {
                this.entry.name(id.toString());
                return this;
            }

            @Override
            public Entry child(Processor<Entry> settings) {
                ArrpJEntry entry = ArrpJLootTable.entry();
                settings.accept(new ArrpEntryAdapter(entry));

                this.entry.child(entry);
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
                ArrpJFunction function = ArrpJLootTable.function(id.toString());
                settings.accept(new ArrpFunctionAdapter(function));

                this.entry.function(function);
                return this;
            }

            @Override
            public Entry condition(Identifier id, Processor<JsonBuilder> settings) {
                ArrpJCondition condition = ArrpJLootTable.predicate(id.toString());
                settings.accept(new ArrpConditionAdapter(condition));

                this.entry.condition(condition);
                return this;
            }

            public static record ArrpFunctionAdapter(ArrpJFunction function) implements Function {
                @Override
                public Function with(Processor<JsonBuilder> json) {
                    json.accept(new ArrpFunctionJsonAdapter(this.function));
                    return this;
                }

                @Override
                public Function condition(Identifier id, Processor<JsonBuilder> settings) {
                    ArrpJCondition condition = ArrpJLootTable.predicate(id.toString());
                    settings.accept(new ArrpConditionAdapter(condition));

                    this.function.condition(condition);
                    return this;
                }

                public static record ArrpFunctionJsonAdapter(ArrpJFunction function) implements JsonBuilder {
                    @Override
                    public <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run) {
                        JsonBuilder.with(this.function.properties, key, json, run);
                        return this;
                    }

                    @Override
                    public JsonBuilder add(String name, JsonElement value) {
                        this.function.parameter(name, value);
                        return this;
                    }

                    @Override
                    public JsonBuilder add(String name, String value) {
                        this.function.parameter(name, value);
                        return this;
                    }

                    @Override
                    public JsonBuilder add(String name, boolean value) {
                        this.function.parameter(name, value);
                        return this;
                    }

                    @Override
                    public JsonBuilder add(String name, Number value) {
                        this.function.parameter(name, value);
                        return this;
                    }

                    @Override
                    public JsonBuilder add(String name, Character value) {
                        this.function.parameter(name, value);
                        return this;
                    }

                    @Override
                    public JsonBuilder addObject(String name, Processor<JsonBuilder> settings) {
                        this.function.parameter(name, settings.process(new JsonBuilderImpl()).build());
                        return this;
                    }

                    @Override
                    public JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
                        this.function.parameter(name, settings.process(new JsonArrayBuilderImpl()).build());
                        return this;
                    }

                    @Override
                    public JsonBuilder write(JsonObject target) {
                        JsonBuilder.copy(this.function.properties, target);
                        return this;
                    }

                    @Override
                    public JsonObject build() {
                        return this.function.properties;
                    }
                }
            }
        }

        public static record ArrpConditionAdapter(ArrpJCondition condition) implements JsonBuilder {
            @Override
            public <J extends JsonElement> JsonBuilder with(String key, Supplier<J> json, Processor<J> run) {
                JsonBuilder.with(this.condition.parameters, key, json, run);
                return this;
            }

            @Override
            public JsonBuilder add(String name, JsonElement value) {
                this.condition.parameter(name, value);
                return this;
            }

            @Override
            public JsonBuilder add(String name, String value) {
                this.condition.parameter(name, value);
                return this;
            }

            @Override
            public JsonBuilder add(String name, boolean value) {
                this.condition.parameter(name, value);
                return this;
            }

            @Override
            public JsonBuilder add(String name, Number value) {
                this.condition.parameter(name, value);
                return this;
            }

            @Override
            public JsonBuilder add(String name, Character value) {
                this.condition.parameter(name, value);
                return this;
            }

            @Override
            public JsonBuilder addObject(String name, Processor<JsonBuilder> settings) {
                this.condition.parameter(name, settings.process(new JsonBuilderImpl()).build());
                return this;
            }

            @Override
            public JsonBuilder addArray(String name, Processor<JsonArrayBuilder> settings) {
                this.condition.parameter(name, settings.process(new JsonArrayBuilderImpl()).build());
                return this;
            }

            @Override
            public JsonBuilder write(JsonObject target) {
                JsonBuilder.copy(this.condition.parameters, target);
                return this;
            }

            @Override
            public JsonObject build() {
                return this.condition.parameters;
            }
        }
    }
}
