package mcjty.theoneprobe.network;

import io.netty.buffer.ByteBuf;
import mcjty.theoneprobe.apiimpl.ProbeInfo;
import mcjty.theoneprobe.network.helpers.PacketHelper;
import mcjty.theoneprobe.rendering.OverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class PacketReturnEntityInfo implements IMessage {

    private UUID uuid;
    private ProbeInfo probeInfo;

    @Override
    public void fromBytes(ByteBuf buf) {
        // Delegate deserialization to helper class
        PacketReturnEntityInfo packetData = PacketHelper.readPacketReturnEntityInfo(buf);
        this.uuid = packetData.uuid;
        this.probeInfo = packetData.probeInfo;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // Delegate serialization to helper class
        PacketHelper.writePacketData(buf, uuid, probeInfo);
    }

    public PacketReturnEntityInfo() {
    }

    public PacketReturnEntityInfo(UUID uuid, ProbeInfo probeInfo) {
        this.uuid = uuid;
        this.probeInfo = probeInfo;
    }

    public static class Handler implements IMessageHandler<PacketReturnEntityInfo, IMessage> {
        @Override
        public IMessage onMessage(PacketReturnEntityInfo message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> OverlayRenderer.registerProbeInfo(message.uuid, message.probeInfo));
            return null;
        }
    }
}
