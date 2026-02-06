# Spellcraft Mod

Spellcraft is a Fabric mod for Minecraft 1.20.1 that adds custom mobs, a boss, and three spell staffs with crafted recipes and unique textures.

## Features
- **Custom mobs**: Emberling and Glacier Wisp with elemental effects.
- **Boss**: Arcane Warden with heavy armor and debuffs.
- **Spell staffs**: Flame, Frost, and Storm staffs that buff the player and debuff targets.
- **Recipes**: Craft staffs from vanilla materials.

## Crafting Recipes
- **Flame Staff**: Blaze powder, blaze rod, and sticks.
- **Frost Staff**: Packed ice, ice, and sticks.
- **Storm Staff**: Glowstone dust, lightning rod, and sticks.

## Running
Use the standard Fabric Loom workflow:

```bash
./gradlew runClient
```

Spawn mobs with the provided spawn eggs from the creative inventory.

## Assets and Binary Files
This repo stores texture and icon images as base64 text in `src/main/resources_base64`. The Gradle task `generateTextures` decodes them into PNGs at build time so you can keep the project compatible with text-only review systems.
