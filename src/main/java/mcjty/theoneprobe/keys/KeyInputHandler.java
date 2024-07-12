package mcjty.theoneprobe.keys;

import mcjty.theoneprobe.config.ConfigSetup;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.toggleLiquids.isPressed()) {
            ConfigSetup.setLiquids(!ConfigSetup.showLiquids);
        } else if (KeyBindings.toggleVisible.isPressed()) {
            if (!ConfigSetup.holdKeyToMakeVisible) {
                ConfigSetup.setVisible(!ConfigSetup.isVisible);
            }
        }
    }
}
