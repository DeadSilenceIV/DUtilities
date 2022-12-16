package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import java.io.File;

public abstract class YamlDataFile implements YamlDataStorage {
    @Getter protected PluginBase plugin;
    protected String fileName;
    protected File file;
    protected YamlConfiguration config;
    public YamlDataFile(PluginBase plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        load();
    }

    /**
     * Loads the file and gets the config instance.
     */
    protected abstract void load();

}
