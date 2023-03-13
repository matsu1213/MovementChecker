package net.azisaba.movementchecker;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class CheckManager implements Listener {

    static HashMap<Player, ClassToInstanceMap<Check>> playerMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(playerMap.containsKey(e.getPlayer())){
            playerMap.get(e.getPlayer()).getInstance(SpeedA.class).check(e);
        }
    }

    public static void addPlayer(Player player){
        ClassToInstanceMap<Check> checks = new ImmutableClassToInstanceMap.Builder<Check>().put(SpeedA.class, new SpeedA(player)).build();
        playerMap.put(player, checks);
    }

    public static void removePlayer(Player player){
        playerMap.remove(player);
    }

}
