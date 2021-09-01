package motherlode.base.api.varianttype;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.util.Identifier;

/**
 * JavaDoc planned.
 *
 * @param <S>
 */
public interface ExtendableVariantType<S extends ExtendableVariantType<S>> {
    S register();

    S withoutBase();

    S with(Extension<S> extension);

    S conditionallyWith(boolean condition, Supplier<Extension<S>> extension);

    S conditionallyWith(BooleanSupplier condition, Supplier<Extension<S>> extension);

    interface Extension<V extends ExtendableVariantType<V>> {
        void registerExtension(Identifier id, V variantType);

        default void register(Identifier id, V variantType) {
            this.registerExtension(id, variantType);
        }
    }
}
