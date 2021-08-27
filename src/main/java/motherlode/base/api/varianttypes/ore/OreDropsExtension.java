package motherlode.base.api.varianttypes.ore;

import net.minecraft.util.Identifier;
import motherlode.base.api.Motherlode;
import motherlode.base.api.resource.CommonData;
import motherlode.base.api.resource.builder.DataPackBuilder;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.ResourcePackBuilder;
import motherlode.base.api.varianttype.MotherlodeVariantType;

public class OreDropsExtension implements MotherlodeVariantType.Extension<Object, OreType> {
    private final Drop drop;
    private String materialName;

    public OreDropsExtension(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void registerExtension(Identifier id, OreType oreType) {
        this.materialName = oreType.getMaterialName();
    }

    @Override
    public void accept(ResourcePackBuilder pack, Identifier id) {
    }

    @Override
    public void accept(DataPackBuilder pack, Identifier id) {
        Identifier oresTagId = new Identifier(CommonData.COMMON_NAMESPACE, id.getPath() + "_ores");
        Identifier stoneOreId = Motherlode.id(id, name -> name + "_ore");
        Identifier deepslateOreId = Motherlode.id(id, name -> "deepslate_" + name + "_ore");
        Identifier mineralId = new Identifier(id.getNamespace(), this.materialName);

        pack.addLootTable(stoneOreId, table -> table
            .type(new Identifier("minecraft", "block"))
            .pool(pool -> pool
                .rolls(1)
                .entry(entry -> entry
                    .type(new Identifier("minecraft", "item"))
                    .name(this.drop.choose(mineralId, stoneOreId))
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
                    .name(this.drop.choose(mineralId, deepslateOreId))
                )
                .condition(new Identifier("minecraft", "survives_explosion"), JsonBuilder::build)
            )
        );

        pack.addSmeltingRecipe(Motherlode.id(id, name -> this.materialName + "_smelting"), recipe -> recipe
            .ingredientTag(oresTagId)
            .result(new Identifier(id.getNamespace(), this.materialName))
            .experience(1)
            .cookingTime(200)
        );

        pack.addBlastingRecipe(Motherlode.id(id, name -> this.materialName + "_blasting"), recipe -> recipe
            .ingredientTag(oresTagId)
            .result(new Identifier(id.getNamespace(), this.materialName))
            .experience(1)
            .cookingTime(100)
        );
    }

    @Override
    public void registerOnClient(Identifier id) {
    }

    @Override
    public Object[] variants() {
        return new Object[0];
    }

    enum Drop {
        MINERAL, THEMSELVES;

        public Identifier choose(Identifier mineralId, Identifier oreId) {
            return switch (this) {
                case MINERAL -> mineralId;
                case THEMSELVES -> oreId;
            };
        }
    }
}
