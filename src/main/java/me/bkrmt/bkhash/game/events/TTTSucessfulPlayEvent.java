package me.bkrmt.bkhash.game.events;

import me.bkrmt.bkhash.game.TTTGame;
import me.bkrmt.bkhash.game.TTTPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TTTSucessfulPlayEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final TTTGame game;
    private final TTTPlayer whoPlayed;

    public TTTSucessfulPlayEvent(TTTGame game, TTTPlayer whoPlayed) {
        this.game = game;
        this.whoPlayed = whoPlayed;
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
}
