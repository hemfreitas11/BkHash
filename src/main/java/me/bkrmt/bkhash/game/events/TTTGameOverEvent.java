package me.bkrmt.bkhash.game.events;

import me.bkrmt.bkhash.game.TTTGame;
import me.bkrmt.bkhash.game.TTTPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TTTGameOverEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final TTTGame game;
    private final TTTPlayer winner;

    public TTTGameOverEvent(TTTGame game, TTTPlayer winner) {
        this.game = game;
        this.winner = winner;
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

    public TTTPlayer getWinner() {
        return winner;
    }

}
