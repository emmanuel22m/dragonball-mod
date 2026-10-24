package com.dbzmod.item;

import com.dbzmod.entity.KiBlastEntity;
import com.dbzmod.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class KiBlasterItem extends Item {

    public KiBlasterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        world.playSound(null, player.getBlockPos(),
                ModSounds.KI_BLAST_FIRE, SoundCategory.PLAYERS, 1.0f, 1.0f);

        if (!world.isClient) {
            KiBlastEntity blast = new KiBlastEntity(world, player);
            blast.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 1.6f, 0.2f);
            world.spawnEntity(blast);
            stack.damage(1, player, player.getPreferredEquipmentSlot(stack));
            player.getItemCooldownManager().set(this, 8);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(stack, world.isClient());
    }
}
