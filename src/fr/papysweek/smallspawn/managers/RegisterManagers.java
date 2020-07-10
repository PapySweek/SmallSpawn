package fr.papysweek.smallspawn.managers;

import fr.papysweek.smallspawn.Main;
import fr.papysweek.smallspawn.commands.SetSpawn;
import fr.papysweek.smallspawn.commands.Spawn;
import fr.papysweek.smallspawn.listeners.PlayerJoin;
import fr.papysweek.smallspawn.listeners.PlayerRespawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class RegisterManagers {

    private Main instance = Main.getInstance();

    public void register(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerRespawn(), instance);
        pm.registerEvents(new PlayerJoin(), instance);

        instance.getCommand("spawn").setExecutor(new Spawn());
        instance.getCommand("setspawn").setExecutor(new SetSpawn());
    }
}
