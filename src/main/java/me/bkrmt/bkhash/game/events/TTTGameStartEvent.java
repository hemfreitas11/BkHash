package me.bkrmt.bkhash.game.events;

import me.bkrmt.bkhash.game.TTTPlayerGroup;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TTTGameStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final TTTPlayerGroup players;
    private boolean isCanceled;

    public TTTGameStartEvent(TTTPlayerGroup players) {
        this.players = players;
        this.isCanceled = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public TTTPlayerGroup getPlayers() {
        return players;
    }
}
