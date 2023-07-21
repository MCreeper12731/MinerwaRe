package com.github.mcreeper12731.minerware.managers;

import com.github.mcreeper12731.minerware.Minerware;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private final Minerware plugin;

    @Getter
    private FileConfiguration config;

    public ConfigManager(Minerware plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder().getAbsoluteFile() + "/config.yml"));

    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder().getAbsoluteFile() + "/config.yml"));
    }

}
