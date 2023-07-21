package com.github.mcreeper12731.minerware.phases;

import com.github.mcreeper12731.minerware.managers.PhaseManager;
import com.github.mcreeper12731.minerware.models.Position;
import com.github.mcreeper12731.minerware.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class WaitingPhase extends Phase {

    private Position lobbyPosition;

    public WaitingPhase(PhaseManager phaseManager) {
        super(phaseManager);
        lobbyPosition = phaseManager.getSpawnManager().getLobbyLocation();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) return;
        phaseManager.getPlayerManager().addPlayer(player.getUniqueId(), player.getName());
        addPlayer(player);
    }

    private void addPlayer(Player player) {

        player.teleport(lobbyPosition.getLocation(player.getWorld()));
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        player.getActivePotionEffects().clear();

        Bukkit.broadcastMessage(Color.color("&6" + player.getName() +
                " &ejoined the game (" +
                phaseManager.getPlayerManager().getPlayerAmount() +
                "/" +
                phaseManager.getConfigManager().getConfig().getInt("max-players")));


    }

    @Override
    public Phase getNextPhase(int option) {
        return null;
        //if (option == 0) //return new CountdownPhase(phaseManager);
    }
}
