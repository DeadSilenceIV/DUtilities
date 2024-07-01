package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UUIDYamlDataFolder extends YamlDataFolder<UUID> {

    public UUIDYamlDataFolder(PluginBase plugin, String folderName,Set<YamlDataFile> initialFiles, boolean preload) {
        super(plugin, folderName,initialFiles,preload);
    }

    public UUIDYamlDataFolder(PluginBase plugin, String folderName, boolean preload) {
        super(plugin, folderName,null,preload);
    }


    public UUIDYamlDataFolder(PluginBase plugin, String folderName, Set<YamlDataFile> initialFiles) {
        super(plugin, folderName, initialFiles);
    }

    public UUIDYamlDataFolder(PluginBase plugin, String folderName) {
        super(plugin, folderName);
    }

    @Override
    public UUID getIdentifier(File file) {
        return UUID.fromString(FileUtils.removeExtension(file.getName()));
    }

    public Set<UUID> getIdentifiers(){
        if(preloaded){
            return data.keySet();
        }else{
            Set<UUID> identifiers = new HashSet<>();
            File[] files = folder.listFiles();
            if(files != null) {
                for (File file : files) {
                    if (FileUtils.getFileExtension(file.getName()).equals(".yml")) {
                        identifiers.add(getIdentifier(file));
                    }
                }
            }
            return identifiers;
        }
    }

}
