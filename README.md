# fcCore

Библиотека для получения русских названий предметов Minecraft 1.16.5

## Описание

fcCore - это вспомогательная библиотека, которая предоставляет простой API для получения русских переводов названий предметов Minecraft. Поддерживает все 1271 предмет версии 1.16.5.

## Установка

### Maven

Добавьте JitPack репозиторий в ваш `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Добавьте зависимость:

```xml
<dependencies>
    <dependency>
        <groupId>com.github.fcplugins-dev</groupId>
        <artifactId>fcCore</artifactId>
        <version>v1.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### plugin.yml

Добавьте fcCore как зависимость:

```yaml
depend: [fcCore]
```

## Использование

### Основной API

```java
import fc.plugins.fccore.manager.api.ItemTranslationAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

// Получить перевод по Material
String name = ItemTranslationAPI.getTranslation(Material.DIAMOND);
// Результат: "Алмаз"

// Получить перевод по ItemStack
ItemStack item = new ItemStack(Material.STONE);
String itemName = ItemTranslationAPI.getTranslation(item);
// Результат: "Камень"

// Получить перевод по строке
String goldName = ItemTranslationAPI.getTranslation("GOLD_INGOT");
// Результат: "Золотой слиток"

// Проверить наличие перевода
boolean hasTranslation = ItemTranslationAPI.hasTranslation(Material.STONE);

// Перезагрузить переводы
ItemTranslationAPI.reload();
```

### Пример использования

```java
public class MyPlugin extends JavaPlugin {
    
    public void giveTranslatedItem(Player player, Material material, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        
        if (meta != null) {
            // Устанавливаем русское название
            meta.setDisplayName(ItemTranslationAPI.getTranslation(material));
            item.setItemMeta(meta);
        }
        
        player.getInventory().addItem(item);
        player.sendMessage("§aПолучен предмет: §e" + ItemTranslationAPI.getTranslation(material));
    }
}
```

## API Методы

### ItemTranslationAPI

| Метод | Описание | Возвращает |
|-------|----------|------------|
| `getTranslation(Material)` | Получить русский перевод материала | String |
| `getTranslation(ItemStack)` | Получить русский перевод предмета | String |
| `getTranslation(String)` | Получить русский перевод по названию материала | String |
| `hasTranslation(Material)` | Проверить наличие перевода | boolean |
| `reload()` | Перезагрузить переводы из файла | void |

## Структура проекта

```
fcCore/
├── src/main/java/fc/plugins/fccore/
│   ├── FcCore.java                    # Главный класс плагина
│   ├── api/
│   │   └── ItemTranslationAPI.java    # Публичный API
│   └── manager/
│       └── TranslationManager.java    # Менеджер переводов
└── src/main/resources/
    ├── plugin.yml                     # Конфигурация плагина
    └── ru_ru.json                     # Файл с переводами
```

## Формат ru_ru.json

Переводы хранятся в формате:

```json
{
  "minecraft:stone": "Камень",
  "minecraft:diamond": "Алмаз",
  "minecraft:iron_ingot": "Железный слиток"
}
```

## Требования

- Minecraft 1.16.5
- Spigot/Paper API 1.16.5
- Java 8+

## Лицензия

Этот проект распространяется свободно для использования в других плагинах.

## Контакты

Telegram: [@fcplugins_minecraft](https://t.me/fcplugins_minecraft)

---

**fcPlugins** - качественные плагины для Minecraft
