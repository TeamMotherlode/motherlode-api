package motherlode.base.impl.worldgen;

import java.util.function.Predicate;
import net.minecraft.world.gen.GenerationStep;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import motherlode.base.api.worldgen.FeatureTarget;

@SuppressWarnings("deprecation")
public record FeatureTargetImpl(Predicate<BiomeSelectionContext> biomeSelector,
                                GenerationStep.Feature generationStep) implements FeatureTarget {
    @Override
    public Predicate<BiomeSelectionContext> getBiomeSelector() {
        return this.biomeSelector;
    }

    @Override
    public GenerationStep.Feature getGenerationStepFeature() {
        return this.generationStep;
    }
}
