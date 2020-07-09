package fr.papysweek.smallspawn.commands;

import fr.papysweek.smallspawn.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetSpawn implements CommandExecutor {

    private Main instance = Main.getInstance();

    FileConfiguration spawnFile = YamlConfiguration.loadConfiguration(instance.getFile("spawn"));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (player.hasPermission("smallspawn.setspawn")) {
                    Location location = player.getLocation();
                    spawnFile.set("spawn.world", location.getWorld().getName());
                    spawnFile.set("spawn.x", location.getX());
                    spawnFile.set("spawn.y", location.getY());
                    spawnFile.set("spawn.z", location.getZ());
                    spawnFile.set("spawn.yaw", location.getYaw());
                    spawnFile.set("spawn.pitch", location.getPitch());

                    try {
                        spawnFile.save(instance.getFile("spawn"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2SPAWN SET"));
                    return true;
                } else {
                    sender.sendMessage("§8[§6SmallSpawn§8] §cVous n'avez pas la permission d'executer cette commande.");
                    return true;
                }
            } else {
                sender.sendMessage("§8[§6SmallSpawn§8] §cSeul un joueur peut effectuer cette commande.");
                return true;
            }
        }
        return true;
    }
}
