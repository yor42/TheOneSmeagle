package mcjty.theoneprobe.items;

import mcjty.theoneprobe.TheOneProbe;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ProbeArmor extends ItemArmor {

    private int armorType;

    public ProbeArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot, int armorType) {
        super(material, renderIndex, equipmentSlot);
        this.armorType = armorType;
    }

    @Override
    public boolean hasOverlay(ItemStack stack) {
        return true; // Enable the overlay for this armor
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if ("overlay".equals(type)) {
            // Return the custom overlay texture (layer1)
            return TheOneProbe.MODID + ":textures/models/armor/probe_armor_overlay.png";
        }

        if (slot == EntityEquipmentSlot.HEAD) {
            return getArmorTexture(this.armorType);
        }

        // Call the parent method for layer0
        return super.getArmorTexture(stack, entity, slot, type);
    }

    public String getArmorTexture(int type){
        switch (type){
            case 1:
                return "minecraft:textures/models/armor/diamond_layer_1.png";
            case 2:
                return "minecraft:textures/models/armor/gold_layer_1.png";
            case 3:
                return "minecraft:textures/models/armor/iron_layer_1.png";
            default:
                return "minecraft:textures/models/armor/diamond_layer_1.png";
        }
    }
}
