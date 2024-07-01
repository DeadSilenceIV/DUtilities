package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;
import us.lynuxcraft.deadsilenceiv.dutilities.Pair;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class handles a folder of YAML files.
 */
public abstract class YamlDataFolder<I> implements YamlDataStorage{
    @Getter protected PluginBase plugin;
    protected File folder;
    protected String folderName;
    protected Map<I,Pair<File,YamlConfiguration>> data;
    @Getter protected boolean preloaded;
    @SuppressWarnings("all")
    public YamlDataFolder(PluginBase plugin, String folderName, Set<YamlDataFile> initialFiles, boolean preloadFiles) {
        this.plugin = plugin;
        data = new HashMap<>();
        folder = new File(plugin.getDataFolder().getPath() + File.separator + folderName);
        this.folderName = folderName;
        if (!folder.exists()) {
            folder.mkdirs();
            if (initialFiles != null) {
                for (YamlDataFile file : initialFiles) {
                    file.setFolderName(folderName);
                    file.load();
                }
            }
        }
        if(preloadFiles) {
            load();
            preloaded = preloadFiles;
        }
    }

    public YamlDataFolder(PluginBase plugin, String folderName, Set<YamlDataFile> initialFiles) {
        this(plugin,folderName,initialFiles,true);
    }

    public YamlDataFolder(PluginBase plugin,String folderName){
        this(plugin,folderName,null,true);
    }

    public abstract I getIdentifier(File file);

    /**
     * Loads each YAML file inside the data folder.
     */
    private void load(){
        File[] files = folder.listFiles();
        if(files != null){
            for(File file : files){
                if(FileUtils.getFileExtension(file.getName()).equals(".yml")) {
                    I identifier = getIdentifier(file);
                    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                    data.put(identifier,new Pair<>(file,config));
                }
            }
        }
    }

    /**
     * Gets the config data of an uuid.
     *
     * @param identifier the file's identifier
     * @param store if you want to store the information in cache
     * @return the {@link Pair} of the config data, null if is not found.
     */
    public Pair<File,YamlConfiguration> getConfigData(I identifier,boolean store){
        Pair<File,YamlConfiguration> pair = data.get(identifier);
        if(pair == null && !preloaded) {
            File file = new File(folder.getPath() + File.separator + identifier.toString() + ".yml");
            if (file.exists()) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                pair = new Pair<>(file, config);
                if(store) {
                    data.put(identifier, pair);
                }
            }
        }
        return pair;
    }

    public Pair<File,YamlConfiguration> getConfigData(I identifier){
        return getConfigData(identifier,true);
    }

    /**
     * Creates the config data for a specified uuid.
     *
     * @param identifier the file's identifier
     * @return the {@link Pair} of the config data created.
     */
    protected Pair<File,YamlConfiguration> create(I identifier){
        File file = new File(folder.getPath()+File.separator+identifier.toString()+".yml");
        if(!file.exists()) FileUtils.create(file);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return new Pair<>(file,config);
    }

    /**
     * Creates the config data for a specified uuid and save it in cache.
     *
     * @param identifier the file's identifier
     * @return the {@link Pair} of the config data created and saved.
     */
    protected Pair<File,YamlConfiguration> createAndSaveConfigDataInCache(I identifier){
        Pair<File,YamlConfiguration> pair = create(identifier);
        data.put(identifier,pair);
        return pair;
    }

    /**
     * Deletes the specified UUID of the storage.
     *
     * @param identifier the UUID instance.
     * @return true if was correctly removed, false if the uuid didn't exist in the storage.
     */
    @SuppressWarnings("all")
    public boolean delete(I identifier){
        if(data.containsKey(identifier)){
            File file = data.get(identifier).getKey();
            file.delete();
            data.remove(identifier);
            return true;
        }else{
            File file = new File(folder.getPath()+File.separator+identifier.toString()+".yml");
            if(file.exists()) {
                file.delete();
                return true;
            }
        }
        return false;
    }

    public boolean exists(I identifier){
        File file = new File(folder.getPath()+File.separator+identifier.toString()+".yml");
        return file.exists();
    }

}
