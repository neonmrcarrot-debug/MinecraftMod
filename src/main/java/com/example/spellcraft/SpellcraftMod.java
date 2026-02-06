package com.example.spellcraft;

import com.example.spellcraft.entity.ArcaneWardenEntity;
import com.example.spellcraft.entity.EmberlingEntity;
import com.example.spellcraft.entity.GlacierWispEntity;
import com.example.spellcraft.item.SpellStaffItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SpellcraftMod implements ModInitializer {
    public static final String MOD_ID = "spellcraft";

    public static final Item FLAME_STAFF = new SpellStaffItem(new FabricItemSettings().maxCount(1), SpellStaffItem.StaffElement.FLAME);
    public static final Item FROST_STAFF = new SpellStaffItem(new FabricItemSettings().maxCount(1), SpellStaffItem.StaffElement.FROST);
    public static final Item STORM_STAFF = new SpellStaffItem(new FabricItemSettings().maxCount(1), SpellStaffItem.StaffElement.STORM);

    public static final EntityType<EmberlingEntity> EMBERLING = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "emberling"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EmberlingEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.4f))
                    .trackRangeBlocks(8)
                    .build()
    );

    public static final EntityType<GlacierWispEntity> GLACIER_WISP = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "glacier_wisp"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GlacierWispEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f))
                    .trackRangeBlocks(8)
                    .build()
    );

    public static final EntityType<ArcaneWardenEntity> ARCANE_WARDEN = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "arcane_warden"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ArcaneWardenEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 3.0f))
                    .trackRangeBlocks(12)
                    .build()
    );

    public static final Item EMBERLING_SPAWN_EGG = new SpawnEggItem(EMBERLING, 0x8b2c14, 0xf0b35a, new FabricItemSettings());
    public static final Item GLACIER_WISP_SPAWN_EGG = new SpawnEggItem(GLACIER_WISP, 0x345a80, 0xa6dbff, new FabricItemSettings());
    public static final Item ARCANE_WARDEN_SPAWN_EGG = new SpawnEggItem(ARCANE_WARDEN, 0x2b233c, 0xc18cff, new FabricItemSettings());

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "flame_staff"), FLAME_STAFF);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "frost_staff"), FROST_STAFF);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "storm_staff"), STORM_STAFF);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "emberling_spawn_egg"), EMBERLING_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "glacier_wisp_spawn_egg"), GLACIER_WISP_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "arcane_warden_spawn_egg"), ARCANE_WARDEN_SPAWN_EGG);

        FabricDefaultAttributeRegistry.register(EMBERLING, EmberlingEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(GLACIER_WISP, GlacierWispEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ARCANE_WARDEN, ArcaneWardenEntity.createAttributes());
    }
}
