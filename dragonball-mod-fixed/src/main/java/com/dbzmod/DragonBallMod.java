package com.dbzmod;

import com.dbzmod.entity.ModEntities;
import com.dbzmod.item.ModItems;
import com.dbzmod.network.KaiokenPayload;
import com.dbzmod.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonBallMod implements ModInitializer {
    public static final String MOD_ID = "dbzmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModSounds.registerSounds();
        ModEntities.registerEntities();

        // Kaio-ken: el cliente (tecla K) avisa al servidor y este aplica los efectos
        PayloadTypeRegistry.playC2S().register(KaiokenPayload.ID, KaiokenPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(KaiokenPayload.ID, (payload, context) -> {
            var player = context.player();
            if (player.hasStatusEffect(StatusEffects.STRENGTH)
                    && player.getStatusEffect(StatusEffects.STRENGTH).getAmplifier() >= 1) {
                return; // Ya esta en Kaio-ken, no recargar
            }
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30 * 20, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30 * 20, 1));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 30 * 20, 1));
            context.server().execute(() -> {
                player.getWorld().playSound(null, player.getBlockPos(),
                        ModSounds.KAIOKEN_TRANSFORM, SoundCategory.PLAYERS, 1.0f, 1.0f);
                player.sendMessage(Text.translatable("msg.dbzmod.kaioken"), true);
            });
        });

        LOGGER.info("[Dragon Ball Mod] Ki cargado y listo para pelear!");
    }
}
