package com.example.spellcraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpellStaffItem extends Item {
    public enum StaffElement {
        FLAME,
        FROST,
        STORM
    }

    private final StaffElement element;

    public SpellStaffItem(Settings settings, StaffElement element) {
        super(settings);
        this.element = element;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            applySelfEffect(user);
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 0.8f, 1.2f);
            stack.damage(1, user, player -> player.sendToolBreakStatus(hand));
        }
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            context.getWorld().playSound(null, context.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.9f, 1.0f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
            applyTargetEffect(target, attacker);
        }
        return super.postHit(stack, target, attacker);
    }

    private void applySelfEffect(PlayerEntity player) {
        switch (element) {
            case FLAME -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120, 0));
            case FROST -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
            case STORM -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 1));
        }
    }

    private void applyTargetEffect(LivingEntity target, Entity attacker) {
        switch (element) {
            case FLAME -> target.setOnFireFor(4);
            case FROST -> target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1));
            case STORM -> target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 0));
        }
    }
}
