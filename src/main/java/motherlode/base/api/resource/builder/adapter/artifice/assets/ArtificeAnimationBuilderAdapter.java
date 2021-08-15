package motherlode.base.api.resource.builder.adapter.artifice.assets;

import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;
import motherlode.base.api.resource.builder.assets.AnimationBuilder;

public record ArtificeAnimationBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.assets.AnimationBuilder animation) implements AnimationBuilder {
    @Override
    public AnimationBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.animation));
        return this;
    }

    @Override
    public AnimationBuilder interpolate(boolean interpolate) {
        this.animation.interpolate(interpolate);
        return this;
    }

    @Override
    public AnimationBuilder width(int width) {
        this.animation.width(width);
        return this;
    }

    @Override
    public AnimationBuilder height(int height) {
        this.animation.height(height);
        return this;
    }

    @Override
    public AnimationBuilder frameTime(int frameTime) {
        this.animation.frametime(frameTime);
        return this;
    }

    @Override
    public AnimationBuilder frames(Processor<FrameOrder> settings) {
        this.animation.frames(frames -> settings.accept(new ArtificeFrameOrderAdapter(frames)));
        return this;
    }

    public static record ArtificeFrameOrderAdapter(
        com.swordglowsblue.artifice.api.builder.assets.AnimationBuilder.FrameOrder order) implements FrameOrder {
        @Override
        public FrameOrder frame(int index) {
            this.order.frame(index);
            return this;
        }

        @Override
        public FrameOrder frame(int index, int frameTime) {
            this.order.frame(index, frameTime);
            return this;
        }

        @Override
        public FrameOrder frameRange(int start, int endExclusive) {
            this.order.frameRange(start, endExclusive);
            return this;
        }
    }
}
