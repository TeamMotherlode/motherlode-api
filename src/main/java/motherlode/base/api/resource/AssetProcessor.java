package motherlode.base.api.resource;

import java.util.Objects;
import net.minecraft.util.Identifier;
import motherlode.base.api.resource.builder.ResourcePackBuilder;

@FunctionalInterface
public interface AssetProcessor {
    /**
     * This is called to register assets using Artifice.
     *
     * @param pack Resource pack builder to register assets to.
     * @param id   Identifier passed together with the {@code AssetProcessor}.
     */
    void accept(ResourcePackBuilder pack, Identifier id);

    /**
     * Calls the {@link #accept} method and returns the given resource pack builder.
     *
     * @param pack Resource pack builder to register assets to.
     * @param id   Identifier passed together with the {@code AssetProcessor}.
     * @return The given resource pack builder.
     */
    default ResourcePackBuilder process(ResourcePackBuilder pack, Identifier id) {
        this.accept(pack, id);
        return pack;
    }

    /**
     * Composes a new {@code AssetProcessor} that will first apply the given {@code AssetProcessors}'s changes, then this changes.
     *
     * @param before The {@code AssetProcessor} to apply before this one.
     * @return The composed {@code AssetProcessor}
     */
    default AssetProcessor after(AssetProcessor before) {
        Objects.requireNonNull(before);

        return (pack, id) -> {
            before.accept(pack, id);
            this.accept(pack, id);
        };
    }

    /**
     * Composes a new {@code AssetProcessor} that will first apply this changes, then the given {@code AssetProcessors}'s changes.
     *
     * @param after The {@code AssetProcessor} to apply after this one.
     * @return The composed {@code AssetProcessor}
     */
    default AssetProcessor andThen(AssetProcessor after) {
        Objects.requireNonNull(after);

        return (pack, id) -> {

            this.accept(pack, id);
            after.accept(pack, id);
        };
    }
}
