package me.bkrmt.bkhash.game.playarea;

import me.bkrmt.bkhash.game.TTTPlayer;

public class PlayAreaSlot {
    private boolean isPlayed;
    private TTTPlayer player;
    private final int slotNumber;

    public PlayAreaSlot(int slotNumber) {
        isPlayed = false;
        this.slotNumber = slotNumber;
        player = null;
    }

    public void setPlayed(TTTPlayer player) {
        isPlayed = true;
        this.player = player;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public TTTPlayer getPlayer() {
        return player;
    }
}
