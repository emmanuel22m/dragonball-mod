package com.dbzmod.sound;

import com.dbzmod.DragonBallMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent KI_BLAST_FIRE = register("ki_blast_fire");
    public static final SoundEvent KI_BLAST_HIT = register("ki_blast_hit");
    public static final SoundEvent KAIOKEN_TRANSFORM = register("kaioken_transform");

    private static SoundEvent register(String name) {
        Identifier id = Identifier.of(DragonBallMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        // Los sonidos se registran arriba con los static
    }
}
