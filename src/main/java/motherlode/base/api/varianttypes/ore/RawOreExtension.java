package motherlode.base.api.varianttypes.ore;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import motherlode.base.api.Motherlode;
import motherlode.base.api.Registerable;
import motherlode.base.api.resource.CommonAssets;
import motherlode.base.api.resource.CommonData;
import motherlode.base.api.resource.builder.DataPackBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.ResourcePackBuilder;
import motherlode.base.api.varianttype.MotherlodeVariantType;

public class RawOreExtension implements MotherlodeVariantType.Extension<OreType> {
    private Item rawOreItem;
    private Block rawOreBlock;
    private String materialName;

    @Override
    public void registerExtension(Identifier id, OreType oreType) {
        this.rawOreItem = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
        this.rawOreBlock = new Block(Block.Settings.of(Material.STONE, oreType.getStoneOreBlock().getDefaultMapColor()).requiresTool().strength(5.0F, 6.0F));

        Registerable.item(this.rawOreItem).register(Motherlode.id(id, name -> "raw_" + name));
        Registerable.block(this.rawOreBlock, ItemGroup.BUILDING_BLOCKS).register(Motherlode.id(id, name -> "raw_" + name + "_block"));

        this.materialName = oreType.getMaterialName();
    }

    @Override
    public void accept(ResourcePackBuilder pack, Identifier id) {
        CommonAssets.DEFAULT_ITEM_MODEL.accept(pack, Motherlode.id(id, name -> "raw_" + name));
        CommonAssets.DEFAULT_BLOCK.accept(pack, Motherlode.id(id, name -> "raw_" + name + "_block"));
    }

    @Override
    public void accept(DataPackBuilder pack, Identifier id) {
        Identifier rawItemId = Motherlode.id(id, name -> "raw_" + name);
        Identifier rawBlockId = Motherlode.id(id, name -> "raw_" + name + "_block");
        Identifier stoneOreId = Motherlode.id(id, name -> name + "_ore");
        Identifier deepslateOreId = Motherlode.id(id, name -> "deepslate_" + name + "_ore");

        CommonData.DEFAULT_BLOCK_LOOT_TABLE.accept(pack, rawBlockId);

        pack.addLootTable(stoneOreId, table -> table
            .type(new Identifier("minecraft", "block"))
            .pool(pool -> pool
                .rolls(1)
                .entry(entry -> entry
                    .type(new Identifier("minecraft", "item"))
                    .name(rawItemId)
                )
                .condition(new Identifier("minecraft", "survives_explosion"), JsonBuilder::build)
            )
        );

        pack.addLootTable(deepslateOreId, table -> table
            .type(new Identifier("minecraft", "block"))
            .pool(pool -> pool
                .rolls(1)
                .entry(entry -> entry
                    .type(new Identifier("minecraft", "item"))
                    .name(rawItemId)
                )
                .condition(new Identifier("minecraft", "survives_explosion"), JsonBuilder::build)
            )
        );

        pack.addShapedRecipe(rawBlockId, recipe -> recipe
            .pattern(
                "###",
                "###",
                "###"
            )
            .ingredientTag('#', new Identifier(CommonData.COMMON_NAMESPACE, id.getPath() + "_ores"))
            .result(rawBlockId, 1)
        );

        pack.addShapelessRecipe(Motherlode.id(rawItemId, name -> name + "_from_block"), recipe -> recipe
            .ingredientItem(rawBlockId)
            .result(rawItemId, 9)
        );

        pack.addSmeltingRecipe(Motherlode.id(id, name -> this.materialName + "_from_raw_" + name + "_smelting"), recipe -> recipe
            .ingredientTag(rawItemId)
            .result(new Identifier(id.getNamespace(), this.materialName))
            .experience(1)
            .cookingTime(200)
        );

        pack.addBlastingRecipe(Motherlode.id(id, name -> this.materialName + "_from_raw_" + name + "_blasting"), recipe -> recipe
            .ingredientTag(rawItemId)
            .result(new Identifier(id.getNamespace(), this.materialName))
            .experience(1)
            .cookingTime(100)
        );
    }

    @Override
    public void registerOnClient(Identifier id) {
    }

    public Item getRawOreItem() {
        return this.rawOreItem;
    }

    public Block getRawOreBlock() {
        return this.rawOreBlock;
    }
}
