package mcjty.theoneprobe.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * You can implement this in your block implementation if you want to support
 * the probe. An alternative to this is to make an IProveInfoProvider.
 * Note that if you implement this then it will be called last (after all the providers)
 */
public interface IProbeInfoAccessor {

    /**
     * Adds information for the probe info for the given block. This method is always
     * called server-side.
     *
     * <p>The {@code probeInfo} object represents a vertical layout, so adding elements to it
     * will cause them to be grouped vertically.
     *
     * @param mode The {@link ProbeMode} (e.g., NORMAL, EXTENDED).
     * @param probeInfo The {@link IProbeInfo} object to which information should be added.
     * @param player The player interacting with the probe.
     * @param world The world in which the block exists.
     * @param blockState The state of the block for which probe information is being added.
     * @param data Additional hit data related to the probe interaction (e.g., hit position, side hit).
     */
    void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data);
}
