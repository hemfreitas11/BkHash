package me.bkrmt.bkhash.game;

import org.bukkit.entity.Player;

public class TTTPlayer {
    private final Player player;
    private final PlayerSymbol symbol;
    private boolean isTurnToPlay;

    public TTTPlayer(Player player, PlayerSymbol symbol) {
        this.player = player;
        this.symbol = symbol;
        isTurnToPlay = false;
    }

    public boolean isTurnToPlay() {
        return isTurnToPlay;
    }

    public void setTurnToPlay(boolean turnToPlay) {
        isTurnToPlay = turnToPlay;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerSymbol getSymbol() {
        return symbol;
    }
}
