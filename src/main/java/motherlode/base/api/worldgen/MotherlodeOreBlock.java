package motherlode.base.api.worldgen;

import java.util.function.Function;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import motherlode.base.api.resource.CommonData;
import motherlode.base.api.resource.builder.DataPackBuilder;
import motherlode.base.api.resource.function.DataProcessor;

public class MotherlodeOreBlock extends OreBlock implements DataProcessor {
    private static final UniformIntProvider NULL_INT_PROVIDER = UniformIntProvider.create(0, 0);

    private final int veinSize;
    private final Function<ConfiguredFeature<OreFeatureConfig, ?>, ConfiguredFeature<?, ?>> decorators;
    private final OreTarget target;
    private final String mineral;

    @Deprecated
    public MotherlodeOreBlock(int miningLevel, String mineral) {
        this(FeatureTargets.OVERWORLD, miningLevel, mineral);
    }

    @Deprecated
    public MotherlodeOreBlock(OreTarget target, int miningLevel, String mineral) {
        this(target, 8, 10, 4, 64, miningLevel, mineral);
    }

    @Deprecated
    public MotherlodeOreBlock(OreTarget target, int veinSize, int veinsPerChunk, int minY, int maxY, int miningLevel, String mineral) {
        this(target, NULL_INT_PROVIDER, veinSize, veinsPerChunk, minY, maxY, miningLevel, mineral);
    }

    public MotherlodeOreBlock(OreTarget target, int veinSize, int veinsPerChunk, YOffset minY, YOffset maxY, int miningLevel, String mineral) {
        this(target, NULL_INT_PROVIDER, veinSize, veinsPerChunk, minY, maxY, miningLevel, mineral);
    }

    @Deprecated
    public MotherlodeOreBlock(OreTarget target, UniformIntProvider experience, int veinSize, int veinsPerChunk, int minY, int maxY, int miningLevel, String mineral) {
        this(target, experience, veinSize, veinsPerChunk, YOffset.fixed(minY), YOffset.fixed(maxY), miningLevel, mineral);
    }

    public MotherlodeOreBlock(OreTarget target, UniformIntProvider experience, int veinSize, int veinsPerChunk, YOffset minY, YOffset maxY, int miningLevel, String mineral) {
        this(target, experience, veinSize, f -> f.uniformRange(minY, maxY).spreadHorizontally().repeat(veinsPerChunk), miningLevel, mineral);
    }

    public MotherlodeOreBlock(OreTarget target, int veinSize, Function<ConfiguredFeature<OreFeatureConfig, ?>, ConfiguredFeature<?, ?>> decorators, int miningLevel, String mineral) {
        this(target, NULL_INT_PROVIDER, veinSize, decorators, miningLevel, mineral);
    }

    public MotherlodeOreBlock(OreTarget target, UniformIntProvider experience, int veinSize, Function<ConfiguredFeature<OreFeatureConfig, ?>, ConfiguredFeature<?, ?>> decorators, int miningLevel, String mineral) {
        super(FabricBlockSettings.of(Material.STONE).strength(Math.min(3f, miningLevel), Math.min(3f, miningLevel + 1)).requiresTool().breakByTool(FabricToolTags.PICKAXES, miningLevel), experience);

        this.veinSize = veinSize;
        this.decorators = decorators;

        this.target = target;

        this.mineral = mineral;
    }

    public int getVeinSize() {
        return this.veinSize;
    }

    public Function<ConfiguredFeature<OreFeatureConfig, ?>, ConfiguredFeature<?, ?>> getDecoratorsFunction() {
        return this.decorators;
    }

    public OreTarget getTarget() {
        return this.target;
    }

    @Override
    public void accept(DataPackBuilder pack, Identifier id) {
        Identifier commonId = new Identifier(CommonData.COMMON_NAMESPACE, id.getPath());

        CommonData.BLOCK_TAG.apply(commonId).accept(pack, id);

        /* Identifier mineral = new Identifier(id.getNamespace(), this.mineral);

        pack.addSmeltingRecipe(new Identifier(id.getNamespace(), mineral.getPath() + "_smelting"), recipe -> recipe
            .ingredientTag(commonId)
            .result(mineral)
            .experience(1)
            .cookingTime(200));

        pack.addBlastingRecipe(new Identifier(id.getNamespace(), mineral.getPath() + "_blasting"), recipe -> recipe
            .ingredientTag(commonId)
            .result(mineral)
            .experience(1)
            .cookingTime(100)); */
    }
}
