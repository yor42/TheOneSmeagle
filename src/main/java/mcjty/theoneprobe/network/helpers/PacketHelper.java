package mcjty.theoneprobe.network.helpers;

import io.netty.buffer.ByteBuf;
import mcjty.theoneprobe.apiimpl.ProbeInfo;
import mcjty.theoneprobe.network.PacketReturnEntityInfo;
import mcjty.theoneprobe.network.PacketReturnInfo;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class PacketHelper {

    // Serialize dimension, BlockPos, and ProbeInfo into ByteBuf
    public static void writePacketData(ByteBuf buf, int dim, BlockPos pos, ProbeInfo probeInfo) {
        buf.writeInt(dim);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        writeProbeInfo(buf, probeInfo);
    }

    // Serialize UUID and ProbeInfo into ByteBuf
    public static void writePacketData(ByteBuf buf, UUID uuid, ProbeInfo probeInfo) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
        writeProbeInfo(buf, probeInfo);
    }

    // Serialize ProbeInfo if available
    private static void writeProbeInfo(ByteBuf buf, ProbeInfo probeInfo) {
        buf.writeBoolean(probeInfo != null);
        if (probeInfo != null) {
            probeInfo.toBytes(buf);
        }
    }

    // Deserialize dimension, BlockPos, and ProbeInfo from ByteBuf
    public static PacketReturnInfo readPacketReturnInfo(ByteBuf buf) {
        int dim = buf.readInt();
        BlockPos pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        ProbeInfo probeInfo = readProbeInfo(buf);
        return new PacketReturnInfo(dim, pos, probeInfo);
    }

    // Deserialize UUID and ProbeInfo from ByteBuf
    public static PacketReturnEntityInfo readPacketReturnEntityInfo(ByteBuf buf) {
        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        ProbeInfo probeInfo = readProbeInfo(buf);
        return new PacketReturnEntityInfo(uuid, probeInfo);
    }

    // Deserialize ProbeInfo from ByteBuf
    private static ProbeInfo readProbeInfo(ByteBuf buf) {
        if (buf.readBoolean()) {
            ProbeInfo probeInfo = new ProbeInfo();
            probeInfo.fromBytes(buf);
            return probeInfo;
        }
        return null;
    }

    // Write Vec3d to ByteBuf
    public static void writeVec3d(ByteBuf buf, Vec3d vec) {
        buf.writeBoolean(vec != null);
        if (vec != null) {
            buf.writeDouble(vec.x);
            buf.writeDouble(vec.y);
            buf.writeDouble(vec.z);
        }
    }

    // Read Vec3d from ByteBuf
    public static Vec3d readVec3d(ByteBuf buf) {
        if (buf.readBoolean()) {
            return new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        }
        return null;
    }

    // Write EnumFacing to ByteBuf
    public static void writeEnumFacing(ByteBuf buf, EnumFacing facing) {
        buf.writeByte(facing == null ? 127 : facing.ordinal());
    }

    // Read EnumFacing from ByteBuf
    public static EnumFacing readEnumFacing(ByteBuf buf) {
        byte sideByte = buf.readByte();
        return (sideByte == 127) ? null : EnumFacing.values()[sideByte];
    }

    // Write ItemStack to ByteBuf
    public static void writeItemStack(ByteBuf buf, ItemStack stack) {
        ByteBufUtils.writeItemStack(buf, stack);
    }

    // Read ItemStack from ByteBuf
    public static ItemStack readItemStack(ByteBuf buf) {
        return ByteBufUtils.readItemStack(buf);
    }
}
