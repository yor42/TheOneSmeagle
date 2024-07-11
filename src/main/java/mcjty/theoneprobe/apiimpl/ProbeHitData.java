package mcjty.theoneprobe.apiimpl;

import mcjty.theoneprobe.api.IProbeHitData;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

/**
 * Represents data related to probing a block.
 */
public class ProbeHitData implements IProbeHitData {

    private final BlockPos pos;
    private final Vec3d hitVec;
    private final EnumFacing side;
    private final ItemStack pickBlock;

    /**
     * Constructs a new instance of ProbeHitData with the given parameters.
     *
     * @param pos The {@link BlockPos} position where the hit occurred.
     * @param hitVec The {@link Vec3d} coordinates indicating where the hit occurred on the block.
     * @param side The side of the block that was hit, or {@code null} if not applicable.
     * @param pickBlock The {@link ItemStack} representing the block as an item, or {@code null} if not applicable.
     */
    public ProbeHitData(BlockPos pos, Vec3d hitVec, EnumFacing side, ItemStack pickBlock) {
        this.pos = pos;
        this.hitVec = hitVec;
        this.side = side;
        this.pickBlock = pickBlock;
    }

    @Override
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public Vec3d getHitVec() {
        return hitVec;
    }

    @Override
    public EnumFacing getSideHit() {
        return side;
    }

    @Nullable
    @Override
    public ItemStack getPickBlock() {
        return pickBlock;
    }
}
