package mcjty.theoneprobe.compat;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Represents the Probe Goggles item, which implements {@link IBauble} for compatibility with baubles.
 */
public class ProbeGoggles extends Item implements IBauble {

    public ProbeGoggles() {
        setUnlocalizedName(TheOneProbe.MODID + ".probe_goggles");
        setRegistryName("probe_goggles");
        setCreativeTab(TheOneProbe.tabProbe);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.HEAD;
    }

}
