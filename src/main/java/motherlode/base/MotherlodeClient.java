package motherlode.base;

import net.fabricmc.api.ClientModInitializer;
import motherlode.base.api.MotherlodeInitEvents;

public class MotherlodeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MotherlodeInitEvents.CLIENT.invoker().initialize();
    }
}
