package com.dbzmod.entity;

import com.dbzmod.DragonBallMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<KiBlastEntity> KI_BLAST = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DragonBallMod.MOD_ID, "ki_blast"),
            EntityType.Builder.<KiBlastEntity>create(KiBlastEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .maxTrackingRange(64)
                    .trackingTickInterval(10)
                    .build("ki_blast")
    );

    public static void registerEntities() {
        // Registro automatico arriba
    }
}
