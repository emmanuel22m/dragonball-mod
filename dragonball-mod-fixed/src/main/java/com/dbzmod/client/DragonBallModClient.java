package com.dbzmod.client;

import com.dbzmod.entity.ModEntities;
import com.dbzmod.network.KaiokenPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DragonBallModClient implements ClientModInitializer {
    public static KeyBinding kaiokenKey;

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.KI_BLAST, KiBlastRenderer::new);

        // Tecla K = Kaio-ken
        kaiokenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.dbzmod.kaioken",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "key.categories.dbzmod"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (kaiokenKey.wasPressed()) {
                if (ClientPlayNetworking.canSend(KaiokenPayload.ID)) {
                    ClientPlayNetworking.send(KaiokenPayload.INSTANCE);
                }
            }
        });
    }
}
