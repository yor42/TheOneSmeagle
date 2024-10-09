package mcjty.theoneprobe.apiimpl.providers;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IIconStyle;
import mcjty.theoneprobe.api.ILayoutStyle;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.config.ConfigSetup;
import mcjty.theoneprobe.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static mcjty.theoneprobe.api.TextStyleClass.*;

public class HarvestInfoTools {

    private static final ResourceLocation ICONS = new ResourceLocation(TheOneProbe.MODID, "textures/gui/icons.png");
    private static final String[] harvestLevels = new String[]{
            "harvestlevel.stone",
            "harvestlevel.iron",
            "harvestlevel.diamond",
            "harvestlevel.obsidian",
            "harvestlevel.cobalt",
            "harvestlevel.duranite",
            "harvestlevel.valyrium",
            "harvestlevel.vibranium"
    };

    private static final HashMap<String, ItemStack> testTools = new HashMap<>();
    static {
        testTools.put("Shovel", new ItemStack(Items.WOODEN_SHOVEL));
        testTools.put("Axe", new ItemStack(Items.WOODEN_AXE));
        testTools.put("Pickaxe", new ItemStack(Items.WOODEN_PICKAXE));
    }

    static void showHarvestLevel(IProbeInfo probeInfo, IBlockState blockState, Block block) {
        String harvestTool = block.getHarvestTool(blockState);
        if (harvestTool != null) {
            int harvestLevel = block.getHarvestLevel(blockState);
            String harvestName;

            // Handle out-of-bounds or negative harvest levels by converting to string
            if (harvestLevel < 0 || harvestLevel >= harvestLevels.length) {
                harvestName = Integer.toString(harvestLevel);
            } else {
                // Use I18n to translate the harvest level
                harvestName = I18n.format(harvestLevels[harvestLevel]);
            }

            // Add text information to the probe with translated tool and level
            probeInfo.text(LABEL + I18n.format("theoneprobe.probe.tool_indicator") + " " + INFO + harvestTool + " (level " + harvestName + ")");
        }
    }

    static void showCanBeHarvested(IProbeInfo probeInfo, World world, BlockPos pos, Block block, EntityPlayer player) {
        if (ModItems.isProbeInHand(player.getHeldItemMainhand())) {
            return; // No need to show harvestability for the probe itself
        }

        boolean harvestable = block.canHarvestBlock(world, pos, player) && world.getBlockState(pos).getBlockHardness(world, pos) >= 0;
        if (harvestable) {
            probeInfo.text(OK + I18n.format("theoneprobe.probe.harvestable_indicator"));
        } else {
            probeInfo.text(WARNING + I18n.format("theoneprobe.probe.not_harvestable_indicator"));
        }
    }

    static void showHarvestInfo(IProbeInfo probeInfo, World world, BlockPos pos, Block block, IBlockState blockState, EntityPlayer player) {
        boolean harvestable = block.canHarvestBlock(world, pos, player) && world.getBlockState(pos).getBlockHardness(world, pos) >= 0;

        String harvestTool = block.getHarvestTool(blockState);
        String harvestName = null;

        if (harvestTool == null) {
            // The block doesn't have an explicitly-set harvest tool, so we're going to test our wooden tools against the block.
            float blockHardness = blockState.getBlockHardness(world, pos);
            if (blockHardness > 0f) {
                for (Map.Entry<String, ItemStack> testToolEntry : testTools.entrySet()) {
                    ItemStack testTool = testToolEntry.getValue();

                    if (testTool != null && testTool.getItem() instanceof ItemTool) {
                        ItemTool toolItem = (ItemTool) testTool.getItem();
                        if (testTool.getDestroySpeed(blockState) >= toolItem.toolMaterial.getEfficiency()) {
                            harvestTool = testToolEntry.getKey();
                            break;
                        }
                    }
                }
            }
        }

        if (harvestTool != null) {
            int harvestLevel = block.getHarvestLevel(blockState);
            if (harvestLevel < 0) {
                // If harvest level is out of bounds, set the name manually
            } else if (harvestLevel >= harvestLevels.length) {
                harvestName = I18n.format(harvestLevels[harvestLevels.length - 1]);
            } else {
                harvestName = I18n.format(harvestLevels[harvestLevel]);
            }
        }

        boolean v = ConfigSetup.harvestStyleVanilla;
        int offs = v ? 16 : 0;
        int dim = v ? 13 : 16;

        ILayoutStyle alignment = probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER);
        IIconStyle iconStyle = probeInfo.defaultIconStyle().width(v ? 18 : 20).height(v ? 14 : 16).textureWidth(32).textureHeight(32);
        IProbeInfo horizontal = probeInfo.horizontal(alignment);
        if (harvestable) {
            horizontal.icon(ICONS, 0, offs, dim, dim, iconStyle)
                    .text(OK + ((harvestTool != null) ? harvestTool : I18n.format("theoneprobe.probe.notool_indicator")));
        } else {
            if (harvestName == null || harvestName.isEmpty()) {
                horizontal.icon(ICONS, 16, offs, dim, dim, iconStyle)
                        .text(WARNING + ((harvestTool != null) ? harvestTool : I18n.format("theoneprobe.probe.notool_indicator")));
            } else {
                horizontal.icon(ICONS, 16, offs, dim, dim, iconStyle)
                        .text(WARNING + ((harvestTool != null) ? harvestTool : I18n.format("theoneprobe.probe.notool_indicator")) + " (level " + harvestName + ")");
            }
        }
    }
}
