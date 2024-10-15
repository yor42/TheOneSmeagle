package mcjty.theoneprobe.gui;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.Tools;
import mcjty.theoneprobe.api.IOverlayStyle;
import mcjty.theoneprobe.api.TextStyleClass;
import mcjty.theoneprobe.apiimpl.ProbeInfo;
import mcjty.theoneprobe.config.ConfigSetup;
import mcjty.theoneprobe.rendering.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static mcjty.theoneprobe.api.TextStyleClass.*;

/**
 * The GUI uses for the Probe Note's config screen
 *
 * @since 11/10/2016
 * @author McJty, strubium
 */
@SideOnly(Side.CLIENT)
public class GuiConfig extends GuiScreen {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 230;

    private int guiLeft;
    private int guiTop;

    private static final ResourceLocation background = new ResourceLocation(TheOneProbe.MODID, "textures/gui/config.png");
    private static final ResourceLocation scene = new ResourceLocation(TheOneProbe.MODID, "textures/gui/scene.png");

    private List<HitBox> hitboxes = Collections.emptyList();

    static {
        // "Default" preset
        new PresetBuilder()
                .setName("Default")
                .setBoxBorderColor(0xff999999)
                .setBoxFillColor(0x55006699)
                .setBoxThickness(2)
                .setBoxOffset(0)
                .build();

        // "WAILA" preset
        new PresetBuilder()
                .setName("WAILA")
                .setBoxBorderColor(0xff4503d0)
                .setBoxFillColor(0xff000000)
                .setBoxThickness(1)
                .setBoxOffset(1)
                .build();

        // "Jade" preset
        new PresetBuilder()
                .setName("Jade")
                .setBoxBorderColor(0xff323331)
                .setBoxFillColor(0xff20261a)
                .setBoxThickness(1)
                .setBoxOffset(1)
                .build();

        // "Fully transparent" preset
        new PresetBuilder()
                .setName("Fully transparent")
                .setBoxBorderColor(0x00000000)
                .setBoxFillColor(0x00000000)
                .setBoxThickness(0)
                .setBoxOffset(0)
                .build();

        // "Black & White" preset
        new PresetBuilder()
                .setName("Black & White")
                .setBoxBorderColor(0xffffffff)
                .setBoxFillColor(0xff000000)
                .setBoxThickness(2)
                .setBoxOffset(0)
                .addTextStyleClass(TextStyleClass.MODNAME, "white,italic")
                .addTextStyleClass(TextStyleClass.NAME, "white,bold")
                .addTextStyleClass(TextStyleClass.INFO, "white")
                .addTextStyleClass(TextStyleClass.INFOIMP, "white,bold")
                .addTextStyleClass(TextStyleClass.WARNING, "white")
                .addTextStyleClass(TextStyleClass.ERROR, "white,underline")
                .addTextStyleClass(TextStyleClass.OBSOLETE, "white,strikethrough")
                .addTextStyleClass(TextStyleClass.LABEL, "white,bold")
                .addTextStyleClass(TextStyleClass.OK, "white")
                .addTextStyleClass(TextStyleClass.PROGRESS, "white")
                .build();

        // "Crazy!" preset
        new PresetBuilder()
                .setName("Crazy!")
                .setBoxBorderColor(0xff00ff00)
                .setBoxFillColor(0x55ff0000)
                .setBoxThickness(2)
                .setBoxOffset(0)
                .addTextStyleClass(TextStyleClass.MODNAME, "green")
                .addTextStyleClass(TextStyleClass.NAME, "yellow,bold")
                .addTextStyleClass(TextStyleClass.INFO, "cyan,bold")
                .addTextStyleClass(TextStyleClass.INFOIMP, "magenta,bold")
                .addTextStyleClass(TextStyleClass.WARNING, "orange,bold")
                .addTextStyleClass(TextStyleClass.ERROR, "red,bold")
                .addTextStyleClass(TextStyleClass.OBSOLETE, "gray,bold")
                .addTextStyleClass(TextStyleClass.LABEL, "blue,bold")
                .addTextStyleClass(TextStyleClass.OK, "green,bold")
                .addTextStyleClass(TextStyleClass.PROGRESS, "white,bold")
                .build();

        // "Soft Pastels" preset
        new PresetBuilder()
                .setName("Soft Pastels")
                .setBoxBorderColor(0xffe0bbff)
                .setBoxFillColor(0x00000000)
                .setBoxThickness(1)
                .setBoxOffset(1)
                .addTextStyleClass(TextStyleClass.MODNAME, "pink,bold,italic")
                .build();

        // "Ocean Blue" preset
        new PresetBuilder()
                .setName("Ocean Blue")
                .setBoxBorderColor(0xff003366)
                .setBoxFillColor(0x556699cc)
                .setBoxThickness(2)
                .setBoxOffset(0)
                .addTextStyleClass(TextStyleClass.MODNAME, "cyan")
                .addTextStyleClass(TextStyleClass.NAME, "light_blue,bold")
                .addTextStyleClass(TextStyleClass.INFO, "white")
                .addTextStyleClass(TextStyleClass.INFOIMP, "white,bold")
                .addTextStyleClass(TextStyleClass.WARNING, "yellow,bold")
                .addTextStyleClass(TextStyleClass.ERROR, "red,bold")
                .addTextStyleClass(TextStyleClass.OBSOLETE, "gray,bold,italic")
                .addTextStyleClass(TextStyleClass.LABEL, "aqua,bold")
                .addTextStyleClass(TextStyleClass.OK, "green,bold")
                .addTextStyleClass(TextStyleClass.PROGRESS, "white,bold")
                .build();
    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        guiLeft = (this.width - WIDTH - WIDTH) / 2;
        guiTop = (this.height - HEIGHT) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft + WIDTH, guiTop, 0, 0, WIDTH, HEIGHT);
        mc.getTextureManager().bindTexture(scene);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);

        renderProbe(ConfigSetup.getProbeNoteBlock());

        int x = WIDTH + guiLeft + 10;
        int y = guiTop + 10;
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, TextFormatting.GOLD + I18n.format("gui.theoneprobe.gui_note_config.title.placement"));
        y += 12;
        RenderHelper.renderText(Minecraft.getMinecraft(), x+10, y, I18n.format("gui.theoneprobe.gui_note_config.body.1"));
        y += 10;
        RenderHelper.renderText(Minecraft.getMinecraft(), x+10, y, I18n.format("gui.theoneprobe.gui_note_config.body.2"));
        y += 20;

        hitboxes = new ArrayList<>();
        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, TextFormatting.GOLD + I18n.format("gui.theoneprobe.gui_note_config.title.presets"));
        y += 12;
        for (Preset preset : PresetBuilder.getPresets()) {
            y = addPreset(x, y, preset);
        }

        y += 5;

        RenderHelper.renderText(Minecraft.getMinecraft(), x, y, TextFormatting.GOLD + I18n.format("gui.theoneprobe.gui_note_config.title.scale"));
        y += 12;
        RenderHelper.renderText(Minecraft.getMinecraft(), x+10, y, I18n.format("gui.theoneprobe.gui_note_config.body.3"));
        y += 12;
        addButton(x+10, y, "--", () -> ConfigSetup.setScale(ConfigSetup.getScale() + 0.2F)); x += 36;
        addButton(x+10, y, "-", () -> ConfigSetup.setScale(ConfigSetup.getScale() + 0.1F)); x += 36;
        addButton(x+10, y, "0", () -> ConfigSetup.setScale(1f)); x += 36;
        addButton(x+10, y, "+", () -> ConfigSetup.setScale(ConfigSetup.getScale() - 0.1F)); x += 36;
        addButton(x+10, y, "++", () -> ConfigSetup.setScale(ConfigSetup.getScale() - 0.2F));

        int margin = 90;
        hitboxes.add(new HitBox(0, 0, margin, margin, () -> ConfigSetup.setPos(2, 2, -1, -1)));
        hitboxes.add(new HitBox(margin, 0, WIDTH - margin, margin, () -> ConfigSetup.setPos(-1, 2, -1, -1)));
        hitboxes.add(new HitBox(WIDTH - margin, 0, WIDTH, margin, () -> ConfigSetup.setPos(-1, 2, 2, -1)));
        hitboxes.add(new HitBox(0, margin, margin, HEIGHT - margin, () -> ConfigSetup.setPos(2, -1, -1, -1)));
        hitboxes.add(new HitBox(margin, margin, WIDTH - margin, HEIGHT - margin, () -> ConfigSetup.setPos(-1, -1, -1, -1)));
        hitboxes.add(new HitBox(WIDTH - margin, margin, WIDTH, HEIGHT - margin, () -> ConfigSetup.setPos(-1, -1, 5, -1)));
        hitboxes.add(new HitBox(0, HEIGHT - margin, margin, HEIGHT, () -> ConfigSetup.setPos(2, -1, -1, 5)));
        hitboxes.add(new HitBox(margin, HEIGHT - margin, WIDTH - margin, HEIGHT, () -> ConfigSetup.setPos(-1, -1, -1, 20)));
        hitboxes.add(new HitBox(WIDTH - margin, HEIGHT - margin, WIDTH, HEIGHT, () -> ConfigSetup.setPos(-1, -1, 5, 2)));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            for (HitBox box : hitboxes) {
                if (box.isHit(mouseX-guiLeft, mouseY-guiTop)) {
                    box.call();
                }
            }
        }
    }

    /**
     * Applies the given preset configuration.
     *
     * @param preset The {@link Preset} object containing the configuration to apply.
     */
    private void applyPreset(Preset preset) {
        ConfigSetup.setBoxStyle(preset.getBoxThickness(), preset.getBoxBorderColor(), preset.getBoxFillColor());

        for (Map.Entry<TextStyleClass, String> entry : ConfigSetup.defaultTextStyleClasses.entrySet()) {
            ConfigSetup.setTextStyle(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<TextStyleClass, String> entry : preset.getTextStyleClasses().entrySet()) {
            ConfigSetup.setTextStyle(entry.getKey(), entry.getValue());
        }
    }

    private int addPreset(int x, int y, Preset preset) {
        drawRect(x + 10, y - 1, x + 10 + WIDTH - 50, y + 10, 0xff000000);
        RenderHelper.renderText(Minecraft.getMinecraft(), x + 20, y, preset.getName());
        hitboxes.add(new HitBox(x + 10 - guiLeft, y - 1 - guiTop, x + 10 + WIDTH - 50 - guiLeft, y + 10 - guiTop, () -> applyPreset(preset)));
        y += 14;
        return y;
    }

    private void addButton(int x, int y, String text, Runnable runnable) {
        drawRect(x, y, x + 30 -1, y + 14 -1, 0xff000000);
        RenderHelper.renderText(Minecraft.getMinecraft(), x + 3, y + 3, text);
        hitboxes.add(new HitBox(x - guiLeft, y - guiTop, x + 30 -1 - guiLeft, y + 14 -1 - guiTop, runnable));
    }

    /**
     * Renders the fake TOP overlay in the GUI
     *
     * @param blockName The {@link Block} (as a string) to use for the example
     *
     * @author McJty
     */
    private void renderProbe(String blockName) {
        // Try to resolve the block from the string blockName
        Block block = Block.getBlockFromName(blockName);

        // If the block isn't found, default to air or handle error
        if (block == Blocks.AIR || block == null) {
            TheOneProbe.setup.getLogger().error("Block not found: {}! Defaulting to Log", blockName);
            block = Blocks.LOG;
        }

        String modid = Tools.getModName(block);
        ProbeInfo probeInfo = TheOneProbe.theOneProbeImp.create();
        ItemStack pickBlock = new ItemStack(block);
        probeInfo.horizontal()
                .item(pickBlock)
                .vertical()
                .text(NAME + pickBlock.getDisplayName())
                .text(MODNAME + modid);
        probeInfo.text(LABEL + "{*theoneprobe.probe.fuel_indicator*} " + INFO + "5 volts");
        probeInfo.text(LABEL + "{*theoneprobe.probe.error_indicator*} " + ERROR + "Oups!");

        renderElements(probeInfo, ConfigSetup.getDefaultOverlayStyle());
    }

    /**
     * Renders elements of the probe information overlay with the given style.
     *
     * @param probeInfo The {@link ProbeInfo} object containing the information to be rendered.
     * @param style     The {@link IOverlayStyle} object defining the style of the overlay.
     */
    private void renderElements(ProbeInfo probeInfo, IOverlayStyle style) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(1 / ConfigSetup.tooltipScale, 1 / ConfigSetup.tooltipScale, 1 / ConfigSetup.tooltipScale);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();

        int w = probeInfo.getWidth();
        int h = probeInfo.getHeight();

        int offset = style.getBorderOffset();
        int thick = style.getBorderThickness();
        int margin = (thick > 0) ? offset + thick + 3 : 0;

        if (thick > 0) {
            w += (margin * 2);
            h += (margin * 2);
        }

        int x = calculateXPosition(style, w);
        int y = calculateYPosition(style, h);

        x += guiLeft;
        y += guiTop;

        double factor = (ConfigSetup.tooltipScale - 1) * 1.4 + 1;
        x *= factor;
        y *= factor;

        if (thick > 0) {
            int x2 = x + w - 1;
            int y2 = y + h - 1;
            if (offset > 0) {
                RenderHelper.drawThickBeveledBox(x, y, x2, y2, thick, style.getBoxColor(), style.getBoxColor(), style.getBoxColor());
            }
            RenderHelper.drawThickBeveledBox(x + offset, y + offset, x2 - offset, y2 - offset, thick, style.getBorderColor(), style.getBorderColor(), style.getBoxColor());
        }

        if (!Minecraft.getMinecraft().isGamePaused()) {
            RenderHelper.rot += .5f;
        }

        probeInfo.render(x + margin, y + margin);

        GlStateManager.popMatrix();
    }

    /**
     * Calculates the x position for the overlay based on the given style and width.
     *
     * @param style The {@link IOverlayStyle} object defining the style of the overlay.
     * @param width The width of the overlay.
     * @return The calculated x position.
     */
    private int calculateXPosition(IOverlayStyle style, int width) {
        if (style.getLeftX() != -1) {
            return style.getLeftX();
        } else if (style.getRightX() != -1) {
            return WIDTH - width - style.getRightX();
        } else {
            return (WIDTH - width) / 2;
        }
    }

    /**
     * Calculates the y position for the overlay based on the given style and height.
     *
     * @param style  The {@link IOverlayStyle} object defining the style of the overlay.
     * @param height The height of the overlay.
     * @return The calculated y position.
     */
    private int calculateYPosition(IOverlayStyle style, int height) {
        if (style.getTopY() != -1) {
            return style.getTopY();
        } else if (style.getBottomY() != -1) {
            return HEIGHT - height - style.getBottomY();
        } else {
            return (HEIGHT - height) / 2;
        }
    }
}
