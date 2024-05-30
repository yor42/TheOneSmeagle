package mcjty.theoneprobe.apiimpl.providers;

import mcjty.theoneprobe.Utilities;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CauldronInfoProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Utilities.getProviderId("cauldron");
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, @Nonnull IBlockState blockState, IProbeHitData data) {
        if (blockState.getBlock() instanceof BlockCauldron) {
            for (IProperty<?> property : blockState.getProperties().keySet()) {
                if (!"level".equals(property.getName())) continue;
                if (property.getValueClass() == Integer.class) {
                    //noinspection unchecked
                    IProperty<Integer> integerProperty = (IProperty<Integer>) property;
                    int fill = blockState.getValue(integerProperty);

                    if (fill > 0) {
                        probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
                        .text(TextStyleClass.LABEL + ((fill == 1) ? "{*theoneprobe.probe.bottle_indicator*}" : "{*theoneprobe.probe.bottles_indicator*}"));
                    } else {
                        probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER))
                        .text(TextStyleClass.LABEL + "{*theoneprobe.probe.empty_indicator*} ");
                    }
                    return;
                }
            }
        }
    }
}
