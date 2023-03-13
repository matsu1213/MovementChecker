package net.azisaba.movementchecker;

import org.bukkit.plugin.java.JavaPlugin;

public final class MovementChecker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new CheckManager(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
