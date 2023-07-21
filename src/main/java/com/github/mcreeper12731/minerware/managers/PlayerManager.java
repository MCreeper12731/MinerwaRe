package com.github.mcreeper12731.minerware.managers;

import com.github.mcreeper12731.minerware.models.Contender;
import com.google.common.collect.ImmutableSet;

import java.util.*;

public class PlayerManager {

    private final Map<UUID, Contender> players = new HashMap<>();

    public void addPlayer(UUID playerId, String username) {
        players.put(playerId, new Contender(playerId, username));
    }

    public void removePlayer(UUID playerId) {
        players.remove(playerId);
    }

    public Contender getPlayer(UUID playerId) {
        return players.get(playerId);
    }

    public Set<Contender> getPlayers() {
        return ImmutableSet.copyOf(players.values());
    }

    public int getPlayerAmount() {
        return players.size();
    }

}
