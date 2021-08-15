package motherlode.base.api.resource.builder.adapter.artifice.data;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import motherlode.base.api.Processor;
import motherlode.base.api.resource.builder.JsonBuilder;
import motherlode.base.api.resource.builder.adapter.artifice.ArtificeJsonBuilderAdapter;
import motherlode.base.api.resource.builder.data.AdvancementBuilder;

public record ArtificeAdvancementBuilderAdapter(
    com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder advancement) implements AdvancementBuilder {
    @Override
    public AdvancementBuilder with(Processor<JsonBuilder> json) {
        json.accept(new ArtificeJsonBuilderAdapter(this.advancement));
        return this;
    }

    @Override
    public AdvancementBuilder display(Processor<Display> settings) {
        this.advancement.display(display -> settings.accept(new ArtificeDisplayAdapter(display)));
        return this;
    }

    @Override
    public AdvancementBuilder parent(Identifier id) {
        this.advancement.parent(id);
        return this;
    }

    @Override
    public AdvancementBuilder criteria(String name, Processor<Criteria> settings) {
        this.advancement.criteria(name, criterion -> settings.accept(new ArtificeCriteriaAdapter(criterion)));
        return this;
    }

    @Override
    public AdvancementBuilder requirement(String... anyOf) {
        this.advancement.requirement(anyOf);
        return this;
    }

    public static record ArtificeDisplayAdapter(
        com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Display display) implements Display {
        @Override
        public Display with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.display));
            return this;
        }

        @Override
        public Display icon(Identifier item) {
            this.display.icon(item);
            return this;
        }

        @Override
        public Display icon(Identifier item, String nbt) {
            this.display.icon(item, nbt);
            return this;
        }

        @Override
        public Display title(String title) {
            this.display.title(title);
            return this;
        }

        @Override
        public Display title(Text title) {
            this.display.title(title);
            return this;
        }

        @Override
        public Display frame(Frame frame) {
            this.display.frame(getArtificeFrame(frame));
            return this;
        }

        @Override
        public Display background(Identifier id) {
            this.display.background(id);
            return this;
        }

        @Override
        public Display description(String description) {
            this.display.description(description);
            return this;
        }

        @Override
        public Display description(Text description) {
            this.display.description(description);
            return this;
        }

        @Override
        public Display showToast(boolean show) {
            this.display.showToast(show);
            return this;
        }

        @Override
        public Display announceToChat(boolean announce) {
            this.display.announceToChat(announce);
            return this;
        }

        @Override
        public Display hidden(boolean hidden) {
            this.display.hidden(hidden);
            return this;
        }

        public static com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Display.Frame getArtificeFrame(Frame frame) {
            return switch (frame) {
                case CHALLENGE -> com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Display.Frame.CHALLENGE;
                case GOAL -> com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Display.Frame.GOAL;
                case TASK -> com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Display.Frame.TASK;
            };
        }
    }

    public static record ArtificeCriteriaAdapter(
        com.swordglowsblue.artifice.api.builder.data.AdvancementBuilder.Criteria criterion) implements AdvancementBuilder.Criteria {
        @Override
        public Criteria with(Processor<JsonBuilder> json) {
            json.accept(new ArtificeJsonBuilderAdapter(this.criterion));
            return this;
        }

        @Override
        public Criteria trigger(Identifier id) {
            this.criterion.trigger(id);
            return this;
        }

        @Override
        public Criteria conditions(Processor<JsonBuilder> settings) {
            this.criterion.conditions(builder -> settings.accept(new ArtificeJsonBuilderAdapter(builder)));
            return this;
        }
    }
}
