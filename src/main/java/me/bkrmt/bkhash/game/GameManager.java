package me.bkrmt.bkhash.game;

import java.util.Hashtable;
import java.util.UUID;

public class GameManager {
    private final Hashtable<UUID, TTTGame> activeGames;

    public GameManager() {
        activeGames = new Hashtable<>();
    }

    public Hashtable<UUID, TTTGame> getActiveGames() {
        return activeGames;
    }
}
