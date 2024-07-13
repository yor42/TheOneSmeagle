# The One Smeagle - Minecraft Mod
_More immersive alternative for WAILA, fork of The One Probe_

## Introduction to The One Smeagle (TOS)

The One Smeagle is a fork of The One Probe (or TOP in short) that is meant to be a more immersive version of WAILA/HWYLA.

The purpose of this mod is to show on-screen information about the block you are looking at whenever you hold the probe in your hand (or off-hand). The mod itself will show basic information like the name of the block, the mod the block comes from, and also the tool to use for harvesting the block. In addition this mod will also show the amount of RF energy that is stored in the block (if the block supports RF), and if you are sneaking it will also give a list of all items that are in the block if it is an inventory (like a chest).

This mod is very configurable so you can disable all the features mentioned above if they do not fit your playing style or modpack.

This mod also has a flexible API that other mods can use to add more information. The API can be found [here](https://github.com/McJty/TheOneProbe/tree/master/src/main/java/mcjty/theoneprobe/api).

## Changes from The One Probe
* Features from TOP Extras
    * View Enchantment power of Bookshelfs
    * View Inventorys of Minecarts and Mules
    * View how long the fuse of lit TNT has left
    * View how much water is in a Cauldron
    * View what disk is playing in a Jukebox
    * View a paintings name             
* Updated gradle version from 2.7 to 4.1
* View more infomation with the Creative Probe
* New theme presets
   * Jade: based on the [mod](https://www.curseforge.com/minecraft/mc-mods/jade) of the same name
   * Crazy: please don't use this, your eyeballs will thank me.
   * Soft Pastels: WIP
   * Ocean Blue: colors of the ocean will relax you
* New config options:
   * showDebugUUID (Defualt: False) If you want to see the UUID of a NPC with the Creative Probe
   * probeNoteStackSize (Defualt: 1) Change the stack size of the One Probe Read Me. 
* Replaced the hardcoded strings with lanugague translatable versions
* Updated ru_ru.lang (By: @bigenergy)
* New en_ud.lang
* New uk_ua.lang (By: @SKZGx)
* New zh_tw.lang (By: @HJ-zhtw)
* Javadoc's for the random methods lying around to make working easier
* Many Progress Bars for slower computers (Almost impossible to see if you have a mid-teir computer)

Note: Languages other than english many not be fully translated, feel free to submit a PR

***

Mods that built have compatabily for TOP will also have built in compatabily for TOS
## Maven

    repositories {
        maven { // TOP
            name 'tterrag maven'
            url "https://maven.tterrag.com/"
        }

    dependencies {
        deobfCompile "mcjty.theoneprobe:TheOneProbe-${top_version}"
    }

## Licence

#### MIT

This mod is licenced under the MIT licence. To see the full terms of the licence click [here](https://github.com/McJty/TheOneProbe/blob/1.10/LICENCE).

#### Modpack Permission

You're free to use the mod in your modpack.

***

## Credits

- [McJty](https://twitter.com/McJty) - Project Owner

**Copyright © 2016 McJty**

**Copyright © 2022 TechLord22**

**Copyright © 2024 Strubium**
