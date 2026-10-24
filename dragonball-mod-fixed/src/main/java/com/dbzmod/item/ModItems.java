package com.dbzmod.item;

import com.dbzmod.DragonBallMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final RegistryKey<ItemGroup> DBZ_GROUP_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP, Identifier.of(DragonBallMod.MOD_ID, "dbz_group"));

    public static final Item KI_BLASTER = register("ki_blaster",
            new KiBlasterItem(new Item.Settings().maxCount(1).maxDamage(100)));

    public static final Item SENZU_BEAN = register("senzu_bean",
            new SenzuBeanItem(new Item.Settings().maxCount(16)));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DragonBallMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        Registry.register(Registries.ITEM_GROUP, DBZ_GROUP_KEY, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.dbzmod.dbz_group"))
                .icon(() -> new ItemStack(KI_BLASTER))
                .build());
        ItemGroupEvents.modifyEntriesEvent(DBZ_GROUP_KEY).register(entries -> {
            entries.add(KI_BLASTER);
            entries.add(SENZU_BEAN);
        });
    }
}
