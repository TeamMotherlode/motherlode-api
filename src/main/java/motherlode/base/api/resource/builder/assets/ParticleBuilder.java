package motherlode.base.api.resource.builder.assets;

import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;

/**
 * Builder for a particle definition ({@code namespace:particles/particle_id.json}).
 */
@Environment(EnvType.CLIENT)
public interface ParticleBuilder {
    /**
     * Allows adding any JSON properties.
     *
     * @param json A callback which will be passed a {@link JsonBuilder}.
     * @return this
     */
    ParticleBuilder with(Processor<JsonBuilder> json);

    /**
     * Add a texture to this particle.
     * Calling this multiple times will add to the list instead of overwriting.
     *
     * @param id The texture ID ({@code namespace:texture_id}).
     * @return this
     */
    ParticleBuilder texture(Identifier id);
}
