package motherlode.base.impl.resource.builder.adapter.artifice.assets;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.assets.ModelElementBuilder;
import motherlode.base.impl.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;

public record ArtificeModelElementBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.ModelElementBuilder builder) implements ModelElementBuilder {
    @Override
    public ModelElementBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.builder));
        return this;
    }

    @Override
    public ModelElementBuilder from(float x, float y, float z) {
        this.builder.from(x, y, z);
        return this;
    }

    @Override
    public ModelElementBuilder to(float x, float y, float z) {
        this.builder.to(x, y, z);
        return this;
    }

    @Override
    public ModelElementBuilder rotation(Processor<Rotation> settings) {
        this.builder.rotation(rotation -> settings.accept(new ArtificeRotationAdapter(rotation)));
        return this;
    }

    @Override
    public ModelElementBuilder shade(boolean shade) {
        this.builder.shade(shade);
        return this;
    }

    @Override
    public ModelElementBuilder face(Direction side, Processor<Face> settings) {
        this.builder.face(side, face -> settings.accept(new ArtificeFaceAdapter(face)));
        return this;
    }

    public static record ArtificeRotationAdapter(
        com.swordglowsblue.artifice.api.builder.assets.ModelElementBuilder.Rotation rotation) implements Rotation {
        @Override
        public Rotation with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.rotation));
            return this;
        }

        @Override
        public Rotation origin(float x, float y, float z) {
            this.rotation.origin(x, y, z);
            return this;
        }

        @Override
        public Rotation axis(Direction.Axis axis) {
            this.rotation.axis(axis);
            return this;
        }

        @Override
        public Rotation angle(float angle) {
            this.rotation.angle(angle);
            return this;
        }

        @Override
        public Rotation rescale(boolean rescale) {
            this.rotation.rescale(rescale);
            return this;
        }
    }

    public static record ArtificeFaceAdapter(
        com.swordglowsblue.artifice.api.builder.assets.ModelElementBuilder.Face face) implements Face {
        @Override
        public Face with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.face));
            return this;
        }

        @Override
        public Face uv(int x1, int x2, int y1, int y2) {
            this.face.uv(x1, x2, y1, y2);
            return this;
        }

        @Override
        public Face texture(String varName) {
            this.face.texture(varName);
            return this;
        }

        @Override
        public Face texture(Identifier path) {
            this.face.texture(path);
            return this;
        }

        @Override
        public Face cullFace(Direction side) {
            this.face.cullface(side);
            return this;
        }

        @Override
        public Face rotation(int rotation) {
            this.face.rotation(rotation);
            return this;
        }

        @Override
        public Face tintIndex(int tintIndex) {
            this.face.tintindex(tintIndex);
            return this;
        }
    }
}
