package motherlode.base.api.varianttype;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.util.Identifier;
import motherlode.base.api.Motherlode;
import motherlode.base.api.resource.function.AssetProcessor;
import motherlode.base.api.resource.function.DataProcessor;

/**
 * JavaDoc planned.
 *
 * @param <T>
 * @param <S>
 */
public abstract class MotherlodeVariantType<T, S extends MotherlodeVariantType<T, S>> extends AbstractExtendableVariantType<T, S> implements AssetProcessor, DataProcessor {
    public MotherlodeVariantType(Identifier id) {
        super(id);
    }

    protected abstract void registerOnClient(Identifier id);

    @Override
    protected void registerBase() {
        super.registerBase();
        Motherlode.getAssetsManager().addAssets(this.getBaseId(), this);
        Motherlode.getAssetsManager().addData(this.getBaseId(), this);
        Motherlode.registerOnClient(this.getBaseId(), this::registerOnClient);
    }

    @Deprecated
    public static <T, S extends AbstractExtendableVariantType<T, S>> S extend(S variantType, String namespace) {
        return AbstractExtendableVariantType.extend(variantType, namespace);
    }

    public static <T, S extends AbstractExtendableVariantType<T, S>, E extends ExtendableVariantType.Extension<T, S>> E extend(S variantType, String namespace, E extension) {
        return AbstractExtendableVariantType.extend(variantType, namespace, extension);
    }

    public static <T, S extends AbstractExtendableVariantType<T, S>, E extends ExtendableVariantType.Extension<T, S>> Optional<E> conditionallyExtend(BooleanSupplier condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return AbstractExtendableVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    public static <T, S extends AbstractExtendableVariantType<T, S>, E extends ExtendableVariantType.Extension<T, S>> Optional<E> conditionallyExtend(boolean condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return AbstractExtendableVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    public static <T, S extends AbstractExtendableVariantType<T, S>, E extends ExtendableVariantType.Extension<T, S>> Optional<E> conditionallyExtend(BooleanSupplier condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        return AbstractExtendableVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    public static <T, S extends AbstractExtendableVariantType<T, S>, E extends ExtendableVariantType.Extension<T, S>> Optional<E> conditionallyExtend(boolean condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        return AbstractExtendableVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    public interface Extension<T, V extends ExtendableVariantType<T, V>> extends ExtendableVariantType.Extension<T, V>, AssetProcessor, DataProcessor {
        @Override
        default void register(Identifier id, V variantType) {
            ExtendableVariantType.Extension.super.register(id, variantType);

            Motherlode.getAssetsManager().addAssets(id, this);
            Motherlode.getAssetsManager().addData(id, this);
            Motherlode.registerOnClient(id, this::registerOnClient);
        }

        void registerOnClient(Identifier id);
    }
}
