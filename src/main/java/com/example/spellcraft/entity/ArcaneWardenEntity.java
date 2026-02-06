package com.example.spellcraft.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ArcaneWardenEntity extends PathAwareEntity {
    public ArcaneWardenEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 180.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.24)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.6)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new AttackGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.add(9, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    @Override
    public boolean tryAttack(LivingEntity target) {
        boolean hit = super.tryAttack(target);
        if (hit && !this.getWorld().isClient) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
            this.playSound(SoundEvents.ENTITY_WITHER_HURT, 1.0f, 0.8f);
        }
        return hit;
    }
}
