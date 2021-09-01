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
 * @param <S>
 */
public abstract class MotherlodeVariantType<S extends MotherlodeVariantType<S>> extends AbstractExtendableVariantType<S> implements AssetProcessor, DataProcessor {
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

    /**
     * @deprecated Use {@link #withNamespace(String)}
     */
    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>> S extend(S variantType, String namespace) {
        return variantType.withNamespace(namespace);
    }

    public static <S extends AbstractExtendableVariantType<S>, E extends ExtendableVariantType.Extension<S>> E extend(S variantType, String namespace, E extension) {
        variantType.extend(extension, namespace).register();
        return extension;
    }

    public static <S extends AbstractExtendableVariantType<S>, E extends ExtendableVariantType.Extension<S>> Optional<E> extendConditionally(BooleanSupplier condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return extendConditionally(condition.getAsBoolean(), variantType, namespace, extensionFunction);
    }

    public static <S extends AbstractExtendableVariantType<S>, E extends ExtendableVariantType.Extension<S>> Optional<E> extendConditionally(boolean condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return extendConditionally(condition, (Supplier<S>) () -> variantType, namespace, extensionFunction);
    }

    public static <S extends AbstractExtendableVariantType<S>, E extends ExtendableVariantType.Extension<S>> Optional<E> extendConditionally(BooleanSupplier condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        return extendConditionally(condition.getAsBoolean(), variantType, namespace, extensionFunction);
    }

    public static <S extends AbstractExtendableVariantType<S>, E extends ExtendableVariantType.Extension<S>> Optional<E> extendConditionally(boolean condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        E extension = condition ? extensionFunction.get() : null;
        (condition ? Optional.of(variantType.get()) : Optional.<S>empty())
            .map(v -> v.withNamespace(namespace)).map(type -> type.with(extension));

        return condition ? Optional.of(extension) : Optional.empty();
    }

    public interface Extension<V extends ExtendableVariantType<V>> extends ExtendableVariantType.Extension<V>, AssetProcessor, DataProcessor {
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
