package us.lynuxcraft.deadsilenceiv.dutilities;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public interface ConfigAttribute {

    String getConfigPath();

    Object getDefaultConfigValue();

    Class<?> getConfigValueClass();
    
    default Object getConfigValue(ConfigurationSection section){
        boolean hasDefaultValue = getDefaultConfigValue() != null;
        if(getConfigValueClass() == String.class){
            return (!hasDefaultValue) ? section.getString(getConfigPath()) : section.getString(getConfigPath(),(String)getDefaultConfigValue());
        }
        if(getConfigValueClass() == Boolean.class){
            return (!hasDefaultValue) ? section.getBoolean(getConfigPath()) : section.getBoolean(getConfigPath(),(boolean)getDefaultConfigValue());
        }
        if(getConfigValueClass() == Integer.class){
            return (!hasDefaultValue) ? section.getInt(getConfigPath()) : section.getInt(getConfigPath(),(int)getDefaultConfigValue());
        }
        if(getConfigValueClass() == Double.class){
            return (!hasDefaultValue) ? section.getDouble(getConfigPath()) : section.getDouble(getConfigPath(),(double)getDefaultConfigValue());
        }
        if(getConfigValueClass() == Long.class){
            return (!hasDefaultValue) ? section.getLong(getConfigPath()) : section.getLong(getConfigPath(),(long)getDefaultConfigValue());
        }
        if(getConfigValueClass() == List.class){
            return section.getStringList(getConfigPath());
        }
        return null;
    }
}
