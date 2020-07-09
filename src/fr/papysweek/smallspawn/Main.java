package fr.papysweek.smallspawn;

import fr.papysweek.smallspawn.managers.RegisterManagers;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {

        instance = this;

        Bukkit.getConsoleSender().sendMessage("§8[§6SmallSpawn§8] §2Version 1.0.0 created by PapySweek");
        Bukkit.getConsoleSender().sendMessage("§8[§6SmallSpawn§8] §2SourceCode: https://github.com/PapySweek/SmallSpawn");

        createSpawnFile();

        new RegisterManagers().register();
    }

    @Override
    public void onDisable() {
        FileConfiguration spawn = YamlConfiguration.loadConfiguration(getFile("spawn"));
        try {
            spawn.save(getFile("spawn"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    private void createSpawnFile() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "spawn.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileConfiguration spawn = YamlConfiguration.loadConfiguration(getFile("spawn"));
                spawn.set("spawn.world", Bukkit.getWorlds().get(0).getName());
                spawn.set("spawn.x", 0.5);
                spawn.set("spawn.y", (double) Bukkit.getWorlds().get(0).getHighestBlockYAt(0,0));
                spawn.set("spawn.z", 0.5);
                spawn.set("spawn.yaw", (float) -180.0);
                spawn.set("spawn.pitch", (float) 0.0);
                try {
                    spawn.save(getFile("spawn"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile(String fileName){
        return new File(getDataFolder(), fileName + ".yml");
    }
}