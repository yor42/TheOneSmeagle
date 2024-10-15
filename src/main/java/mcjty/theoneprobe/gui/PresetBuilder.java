package mcjty.theoneprobe.gui;

import mcjty.theoneprobe.api.TextStyleClass;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * The Builder to make a new prest, basically for GroovyScript compact
 *
 * @since 10/15/2024
 * @author strubium
 */
public class PresetBuilder {

    private static final List<Preset> presets = new ArrayList<>();

    private String name;
    private int boxBorderColor = 0xFFFFFF; // default value
    private int boxFillColor = 0x000000;   // default value
    private int boxThickness = 1;          // default value
    private int boxOffset = 0;             // default value
    private final Map<TextStyleClass, String> textStyleClasses = new HashMap<>();

    public PresetBuilder() {}

    /**
     * Set the Name of the preset, can be a translation key
     */
    public PresetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PresetBuilder setBoxBorderColor(int color) {
        this.boxBorderColor = color;
        return this;
    }

    public PresetBuilder setBoxFillColor(int color) {
        this.boxFillColor = color;
        return this;
    }

    public PresetBuilder setBoxThickness(int thickness) {
        this.boxThickness = thickness;
        return this;
    }

    public PresetBuilder setBoxOffset(int offset) {
        this.boxOffset = offset;
        return this;
    }

    public PresetBuilder addTextStyleClass(TextStyleClass styleClass, String styleName) {
        this.textStyleClasses.put(styleClass, styleName);
        return this;
    }

    // Build method to construct the Preset object and add it to the static presets list
    public Preset build() {
        // Convert the Map entries to an array of Pair<TextStyleClass, String>
        List<Pair<TextStyleClass, String>> stylePairs = textStyleClasses.entrySet()
                .stream()
                .map(entry -> Pair.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        // Create the new Preset object
        Preset preset = new Preset(
                name,
                boxBorderColor,
                boxFillColor,
                boxThickness,
                boxOffset,
                stylePairs.toArray(new Pair[0])
        );

        // Add the newly created preset to the static list
        presets.add(preset);

        return preset;
    }

    /**
     * Get the ArrayList containing all the presets
     */
    public static List<Preset> getPresets() {
        return presets;
    }
}
