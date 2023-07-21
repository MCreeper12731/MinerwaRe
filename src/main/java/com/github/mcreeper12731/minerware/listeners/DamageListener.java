package com.github.mcreeper12731.minerware.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onHurtPlayer(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        event.setCancelled(true);
    }

}
