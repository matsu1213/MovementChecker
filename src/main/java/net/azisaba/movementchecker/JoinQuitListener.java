package net.azisaba.movementchecker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        CheckManager.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        CheckManager.removePlayer(e.getPlayer());
    }

}
