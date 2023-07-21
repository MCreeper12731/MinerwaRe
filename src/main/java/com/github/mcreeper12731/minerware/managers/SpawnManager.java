package com.github.mcreeper12731.minerware.managers;

import com.github.mcreeper12731.minerware.Minerware;
import com.github.mcreeper12731.minerware.models.Position;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.bukkit.scoreboard.Team;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpawnManager {

    private final Minerware plugin;

    @Getter
    private Position lobbyLocation;
    @Getter
    private Position wonLocation;
    @Getter
    private Position lostLocation;

    public SpawnManager(Minerware plugin) {
        this.plugin = plugin;
    }

    public void saveSpawns() {
        try {
            File file = new File(
                    plugin.getDataFolder().getAbsoluteFile() +
                            "/spawns.json"
            );
            file.getParentFile().mkdir();
            file.createNewFile();
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Writer writer = new FileWriter(file, false);
            Set<Position> positions = new HashSet<>();
            positions.add(lobbyLocation);
            positions.add(wonLocation);
            positions.add(lostLocation);

            gson.toJson(positions, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSpawns() {
        try {
            File file = new File(
                    plugin.getDataFolder().getAbsoluteFile() +
                            "/spawns.json"
            );
            if (!file.exists()) return;
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Reader reader = new FileReader(file);
            Type listType = new TypeToken<List<Position>>(){}.getType();

            List<Position> spawns = gson.fromJson(reader, listType);

            lobbyLocation = spawns.get(0);
            wonLocation = spawns.get(1);
            lostLocation = spawns.get(2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
