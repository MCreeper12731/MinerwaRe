package com.github.mcreeper12731.minerware.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Contender {

    private final UUID id;
    private final String username;
    private int score = 0;

    public void addPoint() {
        score++;
    }

}
