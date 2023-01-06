package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;
import us.lynuxcraft.deadsilenceiv.dutilities.Pair;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This class handles a folder of YAML files.
 */
public class YamlDataFolder implements YamlDataStorage{
    @Getter protected PluginBase plugin;
    protected File folder;
    @Getter protected Map<UUID,Pair<File,YamlConfiguration>> data;
    public YamlDataFolder(PluginBase plugin,String folderName) {
        this.plugin = plugin;
        data = new HashMap<>();
        folder = new File(plugin.getDataFolder().getPath()+File.separator+folderName);
        if(!folder.exists())folder.mkdirs();
        load();
    }

    /**
     * Loads each YAML file inside the data folder.
     */
    private void load(){
        File[] files = folder.listFiles();
        if(files != null){
            for(File file : files){
                if(FileUtils.getFileExtension(file.getName()).equals(".yml")) {
                    UUID uuid = UUID.fromString(FileUtils.removeExtension(file.getName()));
                    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                    data.put(uuid,new Pair<>(file,config));
                }
            }
        }
    }

    /**
     * Gets the config data of an uuid.
     *
     * @param uuid the uuid instance
     * @return the {@link Pair} of the config data, null if is not found.
     */
    protected Pair<File,YamlConfiguration> getConfigData(UUID uuid){
        for (UUID id : data.keySet()) {
            if(id.equals(uuid)){
                return data.get(uuid);
            }
        }
        return null;
    }

    /**
     * Creates the config data for a specified uuid and save it in cache.
     *
     * @param uuid the uuid instance
     * @return the {@link Pair} of the config data created and saved.
     */
    protected Pair<File,YamlConfiguration> createAndSaveConfigDataInCache(UUID uuid){
        File file = new File(folder.getPath()+File.separator+uuid.toString()+".yml");
        if(!file.exists()) FileUtils.create(file);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Pair<File,YamlConfiguration> configData = new Pair<>(file,config);
        data.put(uuid,configData);
        return configData;
    }

    /**
     * Deletes the specified UUID of the storage.
     *
     * @param uuid the UUID instance.
     * @return true if was correctly removed, false if the uuid didn't exist in the storage.
     */
    protected boolean delete(UUID uuid){
        if(data.containsKey(uuid)){
            File file = data.get(uuid).getKey();
            file.delete();
            data.remove(uuid);
            return true;
        }
        return false;
    }

}
