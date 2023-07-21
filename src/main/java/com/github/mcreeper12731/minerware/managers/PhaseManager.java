package com.github.mcreeper12731.minerware.managers;

import com.github.mcreeper12731.minerware.Minerware;
import com.github.mcreeper12731.minerware.phases.Phase;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class PhaseManager {

    @Getter
    private final Minerware plugin;
    @Getter
    private final ConfigManager configManager;
    @Getter
    private final PlayerManager playerManager;
    @Getter
    private final SpawnManager spawnManager;

    private Phase currentPhase;

    public PhaseManager(Minerware plugin,
                        ConfigManager configManager,
                        PlayerManager playerManager,
                        SpawnManager spawnManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.playerManager = playerManager;
        this.spawnManager = spawnManager;
        //setPhase(new WaitingPhase(this));
    }

    public Class<? extends Phase> getPhase() {
        return currentPhase.getClass();
    }

    public boolean isInPhase(Class<? extends Phase> phaseClass) {
        return getPhase().equals(phaseClass);
    }

    public void nextPhase(int option) {
        setPhase(currentPhase.getNextPhase(option));
    }

    private void setPhase(Phase phase) {
        if (currentPhase != null) {
            currentPhase.onDisable();
            HandlerList.unregisterAll(currentPhase);
        }

        currentPhase = phase;
        Bukkit.getPluginManager().registerEvents(currentPhase, plugin);
        currentPhase.onEnable();
    }

    /*TODO public void forceStart(CommandSender sender) {
        if (!isInPhase(WaitingPhase.class)) {
            sender.sendMessage("Game cannot be force started at the moment!");
            return;
        }
        nextPhase(0);
    }

    public void forceStop(CommandSender sender) {
        if (!isInPhase(ActivePhase.class)) {
            sender.sendMessage("Game cannot be force stopped at the moment!");
            return;
        }
        nextPhase(0);
    }*/

}
