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
    private final String resourceFolder;
    public YamlResource(PluginBase plugin, String fileName,String resourceFolder) {
        super(plugin, fileName);
        this.resourceFolder = resourceFolder;
    }

    public YamlResource(PluginBase plugin, String fileName) {
        this(plugin,fileName,"");
    }

    @Override
    protected void load(){
        String folderPath = "";
        if(!resourceFolder.isEmpty()){
            folderPath = resourceFolder+"/";
        }
        InputStream input = plugin.getResource(folderPath+fileName);
        if(!plugin.getDataFolder().exists())plugin.getDataFolder().mkdirs();
        loadFile();
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
                if(!folderName.isEmpty()){
                    File folder = new File(plugin.getDataFolder()+File.separator+folderName);
                    if(!folder.exists()){
                        folder.mkdirs();
                    }
                }
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                FileUtils.copyFile(stream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
