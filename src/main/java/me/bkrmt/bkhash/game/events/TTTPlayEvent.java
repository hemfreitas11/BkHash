package me.bkrmt.bkhash.game.events;

import me.bkrmt.bkhash.game.TTTGame;
import me.bkrmt.bkhash.game.TTTPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TTTPlayEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final TTTGame game;
    private final TTTPlayer whoPlayed;
    private final int move;

    public TTTPlayEvent(TTTGame game, TTTPlayer whoPlayed, int move) {
        this.game = game;
        this.whoPlayed = whoPlayed;
        this.move = move;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public TTTGame getGame() {
        return game;
    }

    public TTTPlayer getWhoPlayed() {
        return whoPlayed;
    }

    public int getMove() {
        return move;
    }
}
