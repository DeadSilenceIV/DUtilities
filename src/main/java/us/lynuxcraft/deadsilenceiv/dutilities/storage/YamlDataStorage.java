package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public interface YamlDataStorage {

    PluginBase getPlugin();

    /**
     * Gets the default resource of the specified file name.
     *
     * @return the YamlConfiguration of the default resource.
     */
    default YamlConfiguration getDefaultConfig(String fileName){
        try {
            Reader defConfigStream = new InputStreamReader(getPlugin().getResource(fileName), StandardCharsets.UTF_8);
            return YamlConfiguration.loadConfiguration(defConfigStream);
        }catch (Exception e){
            return null;
        }
    }

    default void save(File file, YamlConfiguration config){
        try {
            config.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
