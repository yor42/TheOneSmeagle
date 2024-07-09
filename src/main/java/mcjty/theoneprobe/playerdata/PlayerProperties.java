package mcjty.theoneprobe.playerdata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(PlayerGotNote.class)
    public static Capability<PlayerGotNote> PLAYER_GOT_NOTE;

    /**
     * Retrieves the PlayerGotNote capability attached to the given EntityPlayer.
     *
     * @param player The EntityPlayer instance to retrieve the capability from.
     * @return The PlayerGotNote capability instance attached to the player, or null if not present.
     */
    public static PlayerGotNote getPlayerGotNote(EntityPlayer player) {
        return player.getCapability(PLAYER_GOT_NOTE, null);
    }
}
