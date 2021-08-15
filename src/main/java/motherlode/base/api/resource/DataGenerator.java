package motherlode.base.api.resource;

import motherlode.base.api.resource.builder.DataPackBuilder;

@FunctionalInterface
public interface DataGenerator {
    /**
     * This is called to register data.
     *
     * @param pack Data pack builder to register assets to.
     */
    void accept(DataPackBuilder pack);

    /**
     * Calls the {@link #accept} method and returns the given data pack builder.
     *
     * @param pack Data pack builder to register assets to.
     * @return The given data pack builder.
     */
    default DataPackBuilder process(DataPackBuilder pack) {
        this.accept(pack);
        return pack;
    }
}
