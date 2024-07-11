package mcjty.theoneprobe.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * You can implement IProbeInfoEntityAccessor in your entities or else you can use
 * this and register that to the {@link ITheOneProbe}. Note that TheOneProbe already
 * adds default providers which will give basic entity information.
 */
public interface IProbeInfoEntityProvider {

    /**
     * Get a unique ID (usually combined with the modid) to identify this provider.
     * @return The unique ID
     */
    String getID();

    /**
     * Add information for the probe info for the given entity. This is always called
     * server side.
     * The given probeInfo object represents a vertical layout. So adding elements to that
     * will cause them to be grouped vertically.
     */
    void addProbeEntityInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data);
}
