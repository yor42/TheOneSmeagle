package mcjty.theoneprobe.compat;

import cofh.redstoneflux.api.IEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


/**
 * Utility class for handling operations related to Redstone Flux (RF) energy.
 */
public class RedstoneFluxTools {

    /**
     * Gets the amount of energy stored in the given TileEntity.
     *
     * @param te The {@link TileEntity} to query for energy storage.
     * @return The amount of energy stored in the TileEntity.
     */
    public static int getEnergy(TileEntity te) {
        IEnergyHandler handler = (IEnergyHandler) te;
        return handler.getEnergyStored(EnumFacing.DOWN);
    }

    /**
     * Gets the maximum amount of energy that can be stored in the given TileEntity.
     *
     * @param te The {@link TileEntity} to query for maximum energy storage.
     * @return The maximum amount of energy that can be stored in the TileEntity.
     */
    public static int getMaxEnergy(TileEntity te) {
        IEnergyHandler handler = (IEnergyHandler) te;
        return handler.getMaxEnergyStored(EnumFacing.DOWN);
    }

    /**
     * Checks if the given TileEntity is an energy handler.
     *
     * @param te The {@link TileEntity} to check.
     * @return True if the TileEntity is an instance of {@link IEnergyHandler}, false otherwise.
     */
    public static boolean isEnergyHandler(TileEntity te) {
        return te instanceof IEnergyHandler;
    }
}
