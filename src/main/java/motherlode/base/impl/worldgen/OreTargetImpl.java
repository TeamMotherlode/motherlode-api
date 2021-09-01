package motherlode.base.impl.worldgen;

import java.util.function.Predicate;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.GenerationStep;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import motherlode.base.api.worldgen.OreTarget;

@SuppressWarnings("deprecation")
public record OreTargetImpl(Predicate<BiomeSelectionContext> biomeSelector,
                            GenerationStep.Feature generationStep,
                            RuleTest ruleTest) implements OreTarget {
    @Override
    public Predicate<BiomeSelectionContext> getBiomeSelector() {
        return this.biomeSelector;
    }

    @Override
    public GenerationStep.Feature getGenerationStepFeature() {
        return this.generationStep;
    }

    @Override
    public RuleTest getRuleTest() {
        return this.ruleTest;
    }
}
