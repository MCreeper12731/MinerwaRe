package com.github.mcreeper12731.minerware.phases;

import com.github.mcreeper12731.minerware.managers.PhaseManager;
import org.bukkit.event.Listener;

public abstract class Phase implements Listener {

    protected final PhaseManager phaseManager;

    public Phase(PhaseManager phaseManager) {
        this.phaseManager = phaseManager;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract Phase getNextPhase(int option);

}
