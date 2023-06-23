package us.lynuxcraft.deadsilenceiv.dutilities.storage;

import org.bukkit.configuration.ConfigurationSection;

public interface YamlSerializer<T>{

    T deserialize(ConfigurationSection section,boolean log,Object... values);

}
