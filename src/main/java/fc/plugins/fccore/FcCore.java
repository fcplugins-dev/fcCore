package fc.plugins.fccore;

import fc.plugins.fccore.manager.TranslationManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FcCore extends JavaPlugin {

    private static FcCore instance;
    private TranslationManager translationManager;

    @Override
    public void onEnable() {
        instance = this;
        
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        
        translationManager = new TranslationManager(this);
        translationManager.load();
        
        getLogger().info("fcCore успешно загружен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("fcCore выключен!");
    }
    
    public static FcCore getInstance() {
        return instance;
    }
    
    public TranslationManager getTranslationManager() {
        return translationManager;
    }
}
