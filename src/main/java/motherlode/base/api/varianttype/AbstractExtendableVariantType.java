package motherlode.base.api.varianttype;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

/**
 * JavaDoc planned.
 *
 * @param <S>
 */
public abstract class AbstractExtendableVariantType<S extends AbstractExtendableVariantType<S>> implements ExtendableVariantType<S> {
    private boolean withoutBase;

    private final String baseNamespace;
    private String currentNamespace;
    private final String name;

    private final List<ExtensionEntry<S, Extension<S>>> extensions;

    public AbstractExtendableVariantType(Identifier id) {
        this.withoutBase = false;

        this.baseNamespace = id.getNamespace();
        this.currentNamespace = id.getNamespace();
        this.name = id.getPath();

        this.extensions = new ArrayList<>();
    }

    protected abstract S getThis();

    protected abstract void registerBase(Identifier id);

    public S withNamespace(String namespace) {
        this.currentNamespace = namespace;
        return getThis();
    }

    public boolean isWithoutBase() {
        return this.withoutBase;
    }

    public String getCurrentNamespace() {
        return this.currentNamespace;
    }

    protected Identifier getBaseId() {
        return new Identifier(this.baseNamespace, this.name);
    }

    @Override
    public S register() {
        if (!this.withoutBase) {
            this.registerBase();
            this.withoutBase();
        }

        this.extensions.stream()
            .filter(extension -> extension.getExtension() != null)
            .filter(extension -> !extension.isRegistered())
            .forEach(extension -> extension.getExtension().register(new Identifier(extension.getNamespace(), this.name), getThis()));

        for (int i = 0; i < this.extensions.size(); i++) {
            this.extensions.set(i, new ExtensionEntry<>(true,
                this.extensions.get(i).getNamespace(), this.extensions.get(i).getExtension()));
        }

        this.currentNamespace = this.baseNamespace;

        return getThis();
    }

    @ApiStatus.OverrideOnly
    protected void registerBase() {
        this.registerBase(new Identifier(this.baseNamespace, this.name));
    }

    @Override
    public S withoutBase() {
        this.withoutBase = true;
        return getThis();
    }

    @Override
    public S with(Extension<S> extension) {
        this.extensions.add(new ExtensionEntry<>(this.currentNamespace, extension));
        return getThis();
    }

    @Override
    public S conditionallyWith(boolean condition, Supplier<Extension<S>> extension) {
        if (condition)
            this.extensions.add(new ExtensionEntry<>(this.currentNamespace, extension.get()));
        return getThis();
    }

    @Override
    public S conditionallyWith(BooleanSupplier condition, Supplier<Extension<S>> extension) {
        if (condition.getAsBoolean())
            this.extensions.add(new ExtensionEntry<>(this.currentNamespace, extension.get()));
        return getThis();
    }

    public S extend(Extension<S> extension, String namespace) {
        return this.withNamespace(namespace).with(extension);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>> S extend(S variantType, String namespace) {
        return MotherlodeVariantType.extend(variantType, namespace);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>, E extends Extension<S>> E extend(S variantType, String namespace, E extension) {
        return MotherlodeVariantType.extend(variantType, namespace, extension);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>, E extends Extension<S>> Optional<E> conditionallyExtend(BooleanSupplier condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return MotherlodeVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>, E extends Extension<S>> Optional<E> conditionallyExtend(boolean condition, S variantType, String namespace, Supplier<E> extensionFunction) {
        return MotherlodeVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>, E extends Extension<S>> Optional<E> conditionallyExtend(BooleanSupplier condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        return MotherlodeVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    @Deprecated
    public static <S extends AbstractExtendableVariantType<S>, E extends Extension<S>> Optional<E> conditionallyExtend(boolean condition, Supplier<S> variantType, String namespace, Supplier<E> extensionFunction) {
        return MotherlodeVariantType.conditionallyExtend(condition, variantType, namespace, extensionFunction);
    }

    protected static class ExtensionEntry<S extends AbstractExtendableVariantType<S>, E extends Extension<S>> {
        private final boolean registered;
        private final String namespace;
        private final E extension;

        public ExtensionEntry(String namespace, E extension) {
            this(false, namespace, extension);
        }

        public ExtensionEntry(boolean registered, String namespace, E extension) {
            this.registered = registered;
            this.namespace = namespace;
            this.extension = extension;
        }

        public boolean isRegistered() {
            return this.registered;
        }

        public String getNamespace() {
            return this.namespace;
        }

        public E getExtension() {
            return this.extension;
        }
    }
}
