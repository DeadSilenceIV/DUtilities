package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import java.io.File;

public abstract class YamlDataFile implements YamlDataStorage {
    @Getter protected PluginBase plugin;
    protected String fileName;
    @Setter protected String folderName;
    protected File file;
    protected YamlConfiguration config;
    public YamlDataFile(PluginBase plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.folderName = "";
    }

    protected String getFolderPath(){
        String folderPath = folderName;
        if(!folderName.isEmpty()){
            folderPath += File.separator;
        }
        return folderPath;
    }

    protected void loadFile(){
        file = new File(plugin.getDataFolder().getPath() + File.separator + getFolderPath() + fileName);
    }

    /**
     * Loads the file and gets the config instance.
     */
    protected abstract void load();

    protected void save() {
        save(file, config);
    }

}
