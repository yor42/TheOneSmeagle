package mcjty.theoneprobe.network;

import io.netty.buffer.ByteBuf;
import mcjty.theoneprobe.apiimpl.ProbeInfo;
import mcjty.theoneprobe.network.helpers.PacketHelper;
import mcjty.theoneprobe.rendering.OverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketReturnInfo implements IMessage {

    private int dim;
    private BlockPos pos;
    private ProbeInfo probeInfo;

    @Override
    public void fromBytes(ByteBuf buf) {
        // Delegate deserialization to helper class
        PacketReturnInfo packetData = PacketHelper.readPacketReturnInfo(buf);
        this.dim = packetData.dim;
        this.pos = packetData.pos;
        this.probeInfo = packetData.probeInfo;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Delegate serialization to helper class
        PacketHelper.writePacketData(buf, dim, pos, probeInfo);
    }

    public PacketReturnInfo() {
    }

    public PacketReturnInfo(int dim, BlockPos pos, ProbeInfo probeInfo) {
        this.dim = dim;
        this.pos = pos;
        this.probeInfo = probeInfo;
    }

    public static class Handler implements IMessageHandler<PacketReturnInfo, IMessage> {
        @Override
        public IMessage onMessage(PacketReturnInfo message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> OverlayRenderer.registerProbeInfo(message.dim, message.pos, message.probeInfo));
            return null;
        }
    }
}