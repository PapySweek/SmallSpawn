package fr.papysweek.smallspawn.commands;

import fr.papysweek.smallspawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    private Main instance = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration spawnFile = YamlConfiguration.loadConfiguration(instance.getFile("spawn"));
            Location spawn = new Location(Bukkit.getWorld(spawnFile.getString("spawn.world")),
                    spawnFile.getDouble("spawn.x"),
                    spawnFile.getDouble("spawn.y"),
                    spawnFile.getDouble("spawn.z"),
                    (float) spawnFile.getDouble("spawn.yaw"),
                    (float) spawnFile.getDouble("spawn.pitch"));
            player.teleport(spawn);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Lol tu tp"));
        } else {
            sender.sendMessage("§8[§6SmallSpawn§8] §cSeul un joueur peut effectuer cette commande.");
        }
        return true;
    }
}
