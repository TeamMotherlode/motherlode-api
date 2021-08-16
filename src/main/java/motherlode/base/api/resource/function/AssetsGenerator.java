package motherlode.base.api.resource.function;

import motherlode.base.api.resource.builder.ResourcePackBuilder;

@FunctionalInterface
public interface AssetsGenerator {
    /**
     * This is called to register assets.
     *
     * @param pack Resource pack builder to register assets to.
     */
    void accept(ResourcePackBuilder pack);

    /**
     * Calls the {@link #accept} method and returns the given resource pack builder.
     *
     * @param pack Resource pack builder to register assets to.
     * @return The given resource pack builder.
     */
    default ResourcePackBuilder process(ResourcePackBuilder pack) {
        this.accept(pack);
        return pack;
    }
}
