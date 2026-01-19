package fc.plugins.fccore.manager.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemTranslationAPI {
    
    public static String getTranslation(Material material) {
        return fc.plugins.fccore.manager.TranslationManager.getInstance().getTranslation(material);
    }
    
    public static String getTranslation(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return "Воздух";
        }
        return getTranslation(item.getType());
    }
    
    public static String getTranslation(String materialName) {
        try {
            Material material = Material.valueOf(materialName.toUpperCase());
            return getTranslation(material);
        } catch (IllegalArgumentException e) {
            return materialName;
        }
    }
    
    public static boolean hasTranslation(Material material) {
        return fc.plugins.fccore.manager.TranslationManager.getInstance().hasTranslation(material);
    }
    
    public static void reload() {
        fc.plugins.fccore.manager.TranslationManager.getInstance().reload();
    }
}
