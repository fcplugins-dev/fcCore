package fc.plugins.fccore.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fc.plugins.fccore.FcCore;
import org.bukkit.Material;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TranslationManager {
    
    private static TranslationManager instance;
    private final FcCore plugin;
    private final Map<Material, String> translations;
    private final Gson gson;
    
    public TranslationManager(FcCore plugin) {
        this.plugin = plugin;
        this.translations = new HashMap<>();
        this.gson = new Gson();
        instance = this;
    }
    
    public static TranslationManager getInstance() {
        return instance;
    }
    
    public void load() {
        translations.clear();
        
        File langFile = new File(plugin.getDataFolder(), "ru_ru.json");
        
        if (!langFile.exists()) {
            plugin.saveResource("ru_ru.json", false);
        }
        
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(langFile), StandardCharsets.UTF_8)) {
            
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            
            for (Material material : Material.values()) {
                String key = getTranslationKey(material);
                if (json.has(key)) {
                    translations.put(material, json.get(key).getAsString());
                }
            }
            
            plugin.getLogger().info("Загружено " + translations.size() + " переводов предметов");
            
        } catch (IOException e) {
        }
    }
    
    public String getTranslation(Material material) {
        return translations.getOrDefault(material, material.name());
    }
    
    public boolean hasTranslation(Material material) {
        return translations.containsKey(material);
    }
    
    public void reload() {
        load();
    }
    
    private String getTranslationKey(Material material) {
        return "minecraft:" + material.name().toLowerCase();
    }
}
