package mcjty.theoneprobe.config;

import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Config GUI for The One Probe
 *
 * @author McJty
 */
@SideOnly(Side.CLIENT)
public class TopModConfigGui extends GuiConfig {

    public TopModConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(), TheOneProbe.MODID, false, false, I18n.format("config.theoneprobe.config.title"));
    }

    /**
     * This method creates a list of config elements, each corresponding to a category.
     * Each element will be shown as a separate button in the GUI.
     */
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();

        // Add a button for CATEGORY_CLIENT
        configElements.add(new ConfigElement(ConfigSetup.mainConfig.getCategory(ConfigSetup.CATEGORY_CLIENT)));

        // Add a button for CATEGORY_THEONEPROBE
        configElements.add(new ConfigElement(ConfigSetup.mainConfig.getCategory(ConfigSetup.CATEGORY_THEONEPROBE)));

        return configElements;
    }
}
