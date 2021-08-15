package motherlode.base.api.resource.builder;

import net.minecraft.util.Identifier;
import com.google.gson.JsonObject;

public interface AssetPackBuilder {
    /**
     * Add a resource at the given path.
     *
     * @param id       The resource path.
     * @param resource The resource to add.
     */
    void add(Identifier id, Resource<JsonObject> resource);
}
