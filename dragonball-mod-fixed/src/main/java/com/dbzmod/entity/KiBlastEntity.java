package com.dbzmod.entity;

import com.dbzmod.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KiBlastEntity extends ProjectileEntity {

    public KiBlastEntity(EntityType<? extends KiBlastEntity> type, World world) {
        super(type, world);
    }

    public KiBlastEntity(World world, LivingEntity owner) {
        this(ModEntities.KI_BLAST, world);
        this.setOwner(owner);
        this.setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    public void tick() {
        super.tick();
        // ProjectileEntity no se mueve sola: avanzamos y detectamos colisiones
        HitResult hit = ProjectileUtil.getCollision(this, this::canHit);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onCollision(hit);
        }
        Vec3d v = this.getVelocity();
        this.setPosition(this.getX() + v.x, this.getY() + v.y, this.getZ() + v.z);
        if (this.age > 100) {
            this.discard();
            return;
        }
        // Estela de ki azul
        if (this.getWorld() instanceof ServerWorld world) {
            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    this.getX(), this.getY() + 0.2, this.getZ(),
                    3, 0.04, 0.04, 0.04, 0.01);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        super.onEntityHit(hit);
        Entity target = hit.getEntity();
        Entity owner = this.getOwner();
        World world = this.getWorld();
        if (world.isClient) return;
        target.damage(world.getDamageSources().thrown(this,
                owner instanceof LivingEntity living ? living : null), 8.0f);
        world.playSound(null, this.getBlockPos(),
                ModSounds.KI_BLAST_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    this.getX(), this.getY(), this.getZ(), 25, 0.3, 0.3, 0.3, 0.1);
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult hit) {
        super.onBlockHit(hit);
        this.getWorld().playSound(null, this.getBlockPos(),
                ModSounds.KI_BLAST_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        this.discard();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
    }

}
