package mcjty.theoneprobe.config;

import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TopModConfigGui extends GuiConfig {

    public TopModConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(ConfigSetup.mainConfig.getCategory(ConfigSetup.CATEGORY_CLIENT)).getChildElements(),
                TheOneProbe.MODID, false, false, I18n.format("config.theoneprobe.config.title"));
    }
}
