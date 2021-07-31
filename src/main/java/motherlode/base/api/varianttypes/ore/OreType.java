package motherlode.base.api.varianttypes.ore;

import java.util.function.UnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import motherlode.base.api.Motherlode;
import motherlode.base.api.Registerable;
import motherlode.base.api.resource.CommonAssets;
import motherlode.base.api.resource.CommonData;
import motherlode.base.api.resource.DataProcessor;
import motherlode.base.api.varianttype.MotherlodeVariantType;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;

/**
 * Variant type that adds blocks and items for an overworld ore type.
 */
public class OreType extends MotherlodeVariantType<Object, OreType> {
    public static final OreType IRON = new OreType(new Identifier("minecraft", "iron"), Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE).withoutBase().register();
    public static final OreType COAL = new OreType(new Identifier("minecraft", "coal"), Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE, "coal").withoutBase().register();
    public static final OreType COPPER = new OreType(new Identifier("minecraft", "copper"), Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE).withoutBase().register();
    public static final OreType GOLD = new OreType(new Identifier("minecraft", "gold"), Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE).withoutBase().register();
    public static final OreType REDSTONE = new OreType(new Identifier("minecraft", "redstone"), Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE, "redstone_dust").withoutBase().register();
    public static final OreType LAPIS_LAZULI = new OreType(new Identifier("minecraft", "lapis_lazuli"), Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE, "lapis_lazuli").withoutBase().register();
    public static final OreType DIAMOND = new OreType(new Identifier("minecraft", "diamond"), Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, "diamond").withoutBase().register();

    private final Block stoneOreBlock;
    private final Block deepslateOreBlock;
    private Item material;

    private final String materialName;

    public OreType(Identifier id, Block stoneOreBlock, Block deepslateOreBlock, String materialName) {
        super(id);
        this.materialName = materialName;

        this.stoneOreBlock = stoneOreBlock;
        this.deepslateOreBlock = deepslateOreBlock;
    }

    public OreType(Identifier id, Block stoneOreBlock, Block deepslateOreBlock) {
        this(id, stoneOreBlock, deepslateOreBlock, id.getPath() + "_ingot");
    }

    @Override
    protected OreType getThis() {
        return this;
    }

    @Override
    protected void registerBase(Identifier id) {
        this.material = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

        registerBlock(this.stoneOreBlock, ItemGroup.BUILDING_BLOCKS, this.getBaseId(), name -> name + "_ore");
        registerBlock(this.deepslateOreBlock, ItemGroup.BUILDING_BLOCKS, this.getBaseId(), name -> "deepslate_" + name + "_ore");
        Registerable.item(this.material).register(Motherlode.id(this.getBaseId(), name -> this.materialName));
    }

    private static void registerBlock(Block block, ItemGroup group, Identifier baseId, UnaryOperator<String> path) {
        Registerable.block(block, group).register(Motherlode.id(baseId, path));
    }

    public Block getStoneOreBlock() {
        return this.stoneOreBlock;
    }

    public Block getDeepslateOreBlock() {
        return this.deepslateOreBlock;
    }

    public Item getMaterial() {
        return this.material;
    }

    public String getMaterialName() {
        return this.materialName;
    }

    public OreType withRawOres() {
        return this.with(new RawOreExtension());
    }

    public OreType oresDropMineral() {
        return this.with(new OreDropsExtension(OreDropsExtension.Drop.MINERAL));
    }

    public OreType oresDropThemselves() {
        return this.with(new OreDropsExtension(OreDropsExtension.Drop.THEMSELVES));
    }

    @Override
    protected Object[] baseVariants() {
        return new Object[] { this.stoneOreBlock, this.deepslateOreBlock, this.material };
    }

    @Override
    public void accept(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        CommonAssets.DEFAULT_BLOCK.accept(pack, Motherlode.id(id, name -> name + "_ore"));
        CommonAssets.DEFAULT_BLOCK.accept(pack, Motherlode.id(id, name -> "deepslate_" + name + "_ore"));
        CommonAssets.DEFAULT_ITEM_MODEL.accept(pack, Motherlode.id(this.getBaseId(), name -> this.materialName));
    }

    @Override
    public void accept(ArtificeResourcePack.ServerResourcePackBuilder pack, Identifier id) {
        Identifier oresTagId = new Identifier(CommonData.COMMON_NAMESPACE, id.getPath() + "_ores");
        Identifier stoneOreId = Motherlode.id(id, name -> name + "_ore");
        Identifier deepslateOreId = Motherlode.id(id, name -> "deepslate_" + name + "_ore");

        CommonData.BLOCK_TAG.apply(oresTagId).accept(pack, stoneOreId);
        CommonData.BLOCK_TAG.apply(oresTagId).accept(pack, deepslateOreId);

        if (this.stoneOreBlock instanceof DataProcessor)
            ((DataProcessor) this.stoneOreBlock).accept(pack, stoneOreId);
        if (this.deepslateOreBlock instanceof DataProcessor)
            ((DataProcessor) this.deepslateOreBlock).accept(pack, deepslateOreId);
    }

    @Override
    protected void registerOnClient(Identifier id) {
    }
}
