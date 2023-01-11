package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;

import java.io.File;
import java.util.Set;

public class StringYamlDataFolder extends YamlDataFolder<String>{

    public StringYamlDataFolder(PluginBase plugin, String folderName, Set<YamlDataFile> initialFiles) {
        super(plugin, folderName, initialFiles);
    }

    @Override
    public String getIdentifier(File file) {
        return FileUtils.removeExtension(file.getName());
    }

}
