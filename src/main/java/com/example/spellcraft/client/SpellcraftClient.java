package com.example.spellcraft.client;

import com.example.spellcraft.SpellcraftMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class SpellcraftClient implements ClientModInitializer {
    private static final Identifier EMBERLING_TEXTURE = new Identifier(SpellcraftMod.MOD_ID, "textures/entity/emberling.png");
    private static final Identifier GLACIER_WISP_TEXTURE = new Identifier(SpellcraftMod.MOD_ID, "textures/entity/glacier_wisp.png");
    private static final Identifier ARCANE_WARDEN_TEXTURE = new Identifier(SpellcraftMod.MOD_ID, "textures/entity/arcane_warden.png");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SpellcraftMod.EMBERLING, context ->
                new MobEntityRenderer<>(context, new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.6f) {
                    @Override
                    public Identifier getTexture(net.minecraft.entity.mob.PathAwareEntity entity) {
                        return EMBERLING_TEXTURE;
                    }
                }
        );

        EntityRendererRegistry.register(SpellcraftMod.GLACIER_WISP, context ->
                new MobEntityRenderer<>(context, new SlimeEntityModel<>(context.getPart(EntityModelLayers.SLIME)), 0.6f) {
                    @Override
                    public Identifier getTexture(net.minecraft.entity.mob.PathAwareEntity entity) {
                        return GLACIER_WISP_TEXTURE;
                    }
                }
        );

        EntityRendererRegistry.register(SpellcraftMod.ARCANE_WARDEN, context ->
                new MobEntityRenderer<>(context, new IronGolemEntityModel<>(context.getPart(EntityModelLayers.IRON_GOLEM)), 0.9f) {
                    @Override
                    public Identifier getTexture(net.minecraft.entity.mob.PathAwareEntity entity) {
                        return ARCANE_WARDEN_TEXTURE;
                    }
                }
        );
    }
}
