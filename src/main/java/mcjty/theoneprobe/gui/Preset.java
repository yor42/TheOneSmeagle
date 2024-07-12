package mcjty.theoneprobe.gui;

import mcjty.theoneprobe.api.TextStyleClass;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
class Preset {
    private final String name;
    private final int boxBorderColor;
    private final int boxFillColor;
    private final int boxThickness;
    private final int boxOffset;
    private final Map<TextStyleClass, String> textStyleClasses;

    /**
     * Constructs a Preset with specified parameters.
     *
     * @param name           The name of the preset.
     * @param boxBorderColor The color of the border of the box.
     * @param boxFillColor   The color to fill the box.
     * @param boxThickness   The thickness of the box border.
     * @param boxOffset      The offset of the box.
     * @param styles         Pairs of {@link TextStyleClass} and their associated style names.
     */
    @SafeVarargs
    public Preset(String name, int boxBorderColor, int boxFillColor, int boxThickness, int boxOffset, Pair<TextStyleClass, String>... styles) {
        this.name = name;
        this.boxBorderColor = boxBorderColor;
        this.boxFillColor = boxFillColor;
        this.boxThickness = boxThickness;
        this.boxOffset = boxOffset;
        textStyleClasses = new HashMap<>();
        for (Pair<TextStyleClass, String> style : styles) {
            textStyleClasses.put(style.getLeft(), style.getRight());
        }
    }

    public String getName() {
        return name;
    }

    public int getBoxBorderColor() {
        return boxBorderColor;
    }

    public int getBoxFillColor() {
        return boxFillColor;
    }

    public int getBoxThickness() {
        return boxThickness;
    }

    public int getBoxOffset() {
        return boxOffset;
    }

    /**
     * Gets the map of TextStyleClass to their associated style names.
     *
     * @return The map of TextStyleClass to their associated style names.
     */
    public Map<TextStyleClass, String> getTextStyleClasses() {
        return textStyleClasses;
    }
}
