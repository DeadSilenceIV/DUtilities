package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;

import java.io.File;

/**
 * This class handles a new empty YAML file.
 */
public class BaseYamlDataFile extends YamlDataFile{

    public BaseYamlDataFile(PluginBase plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    protected void load() {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        file = new File(plugin.getDataFolder().getPath() + File.separator + fileName);
        if(!file.exists())FileUtils.create(file);
        config = YamlConfiguration.loadConfiguration(file);
    }

}
