package fr.papysweek.smallspawn.listeners;

import fr.papysweek.smallspawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    private Main instance = Main.getInstance();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        FileConfiguration spawnFile = YamlConfiguration.loadConfiguration(instance.getFile("spawn"));
        Location spawn = new Location(Bukkit.getWorld(spawnFile.getString("spawn.world")),
                spawnFile.getDouble("spawn.x"),
                spawnFile.getDouble("spawn.y"),
                spawnFile.getDouble("spawn.z"),
                (float) spawnFile.getDouble("spawn.yaw"),
                (float) spawnFile.getDouble("spawn.pitch"));
        e.setRespawnLocation(spawn);
    }
}
