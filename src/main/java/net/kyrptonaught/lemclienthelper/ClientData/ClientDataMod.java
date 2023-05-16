package net.kyrptonaught.lemclienthelper.ClientData;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;

public class ClientDataMod {

    public static void onInitialize() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            FabricLoader loader = FabricLoader.getInstance();
            boolean hasOptifine = isOptifineLoaded(loader);
            boolean hasControllerMod = isControllerModLoaded(loader);
            ClientDataNetworking.sendHasLEMPacket(sender, hasOptifine, hasControllerMod);
        });
    }

    public static boolean isOptifineLoaded(FabricLoader loader) {
        return loader.isModLoaded("optifabric") || loader.isModLoaded("optifine");
    }

    public static boolean isControllerModLoaded(FabricLoader loader) {
        return loader.isModLoaded("midnightcontrols") ||
                loader.isModLoaded("lambdacontrols") ||
                loader.isModLoaded("controllable") ||
                loader.isModLoaded("controllermod");
    }
}
