package net.azisaba.movementchecker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpeedA extends Check{

    private final Player player;

    private int buffer = 0;

    private boolean lastOnGround = true;
    private double lastDeltaXY = 0;

    private Location lastGroundLoc = null;

    SpeedA(Player player){
        this.player = player;
    }

    public void check(PlayerMoveEvent e){
        if(!e.getPlayer().isOnGround() && !e.getPlayer().isFlying()) {
            double deltaX = e.getTo().getX() - e.getFrom().getX();
            double deltaZ = e.getTo().getZ() - e.getFrom().getZ();
            double deltaXY = Math.hypot(deltaX, deltaZ);

            double prediction = lastDeltaXY * 0.91 + (e.getPlayer().isSprinting() ? 0.026 : 0.02);
            double acceleration = deltaXY - prediction;

            if (acceleration > 1e-12 && !lastOnGround) {
                buffer++;
            } else {
                buffer = 0;
            }

            if (buffer > 2) {
                if(lastGroundLoc != null && e.getPlayer().getWorld() == lastGroundLoc.getWorld()){
                    e.getPlayer().teleport(lastGroundLoc);
                }
            }

            lastDeltaXY = deltaXY;
        }
        if(e.getPlayer().isOnGround()){
            lastGroundLoc = e.getPlayer().getLocation();
        }
        lastOnGround = e.getPlayer().isOnGround();
    }
}
