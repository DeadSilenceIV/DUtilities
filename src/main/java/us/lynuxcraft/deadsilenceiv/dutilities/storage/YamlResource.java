package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;
import us.lynuxcraft.deadsilenceiv.dutilities.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * This class handles a previously created YAML file.
 */
public class YamlResource extends YamlDataFile {

    public YamlResource(PluginBase plugin, String fileName) {
        super(plugin, fileName);
    }

    @Override
    protected void load(){
        InputStream input = plugin.getResource(fileName);
        if(!plugin.getDataFolder().exists())plugin.getDataFolder().mkdirs();
        file = new File(plugin.getDataFolder().getPath() + File.separator + fileName);
        copyIfNotExists(input);
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Copy the default file if isn't already created.
     *
     * @param stream the resource input stream.
     */
    private void copyIfNotExists(InputStream stream){
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                FileUtils.copyFile(stream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
