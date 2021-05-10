package motherlode.base.api.varianttypes.ore;

import java.util.function.UnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import motherlode.base.api.Motherlode;
import motherlode.base.api.Registerable;
import motherlode.base.api.assets.CommonAssets;
import motherlode.base.api.assets.CommonData;
import motherlode.base.api.assets.DataProcessor;
import motherlode.base.api.varianttype.MotherlodeVariantType;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;

/**
 * JavaDoc planned.
 */
public class OreType extends MotherlodeVariantType<Object, OreType> {
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
