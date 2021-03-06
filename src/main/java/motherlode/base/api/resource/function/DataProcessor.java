package motherlode.base.api.resource.function;

import java.util.Objects;
import net.minecraft.util.Identifier;
import motherlode.base.api.resource.builder.DataPackBuilder;

@FunctionalInterface
public interface DataProcessor {
    /**
     * This is called to register data.
     *
     * @param pack Data pack builder to register data to.
     * @param id   Identifier passed together with the {@code DataProcessor}.
     */
    void accept(DataPackBuilder pack, Identifier id);

    /**
     * Calls the {@link #accept} method and returns the given data pack builder.
     *
     * @param pack Data pack builder to register data to.
     * @param id   Identifier passed together with the {@code DataProcessor}.
     * @return The given data pack builder.
     */
    default DataPackBuilder process(DataPackBuilder pack, Identifier id) {
        this.accept(pack, id);
        return pack;
    }

    /**
     * Composes a new {@code DataProcessor} that will first apply the given {@code DataProcessors}'s changes, then this changes.
     *
     * @param before The {@code DataProcessor} to apply before this one.
     * @return The composed {@code DataProcessor}
     */
    default DataProcessor after(DataProcessor before) {
        Objects.requireNonNull(before);

        return (pack, id) -> {
            before.accept(pack, id);
            this.accept(pack, id);
        };
    }

    /**
     * Composes a new {@code DataProcessor} that will first apply this changes, then the given {@code DataProcessors}'s changes.
     *
     * @param after The {@code DataProcessor} to apply after this one.
     * @return The composed {@code DataProcessor}
     */
    default DataProcessor andThen(DataProcessor after) {
        Objects.requireNonNull(after);

        return (pack, id) -> {
            this.accept(pack, id);
            after.accept(pack, id);
        };
    }
}
