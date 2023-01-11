package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public class UUIDYamlDataFolder extends YamlDataFolder<UUID> {

    public UUIDYamlDataFolder(PluginBase plugin, String folderName, Set<YamlDataFile> initialFiles) {
        super(plugin, folderName, initialFiles);
    }

    @Override
    public UUID getIdentifier(File file) {
        return UUID.fromString(FileUtils.removeExtension(file.getName()));
    }

}
