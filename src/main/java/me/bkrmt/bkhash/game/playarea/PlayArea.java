package me.bkrmt.bkhash.game.playarea;

import me.bkrmt.bkhash.game.TTTPlayer;

public class PlayArea {
    PlayAreaSlot[][] slots;

    public PlayArea() {
        slots = new PlayAreaSlot[3][3];
        int temp = 1;
        for (int c = 0; c < 3; c++) {
            for (int i = 0; i < 3; i++) {
                slots[c][i] = new PlayAreaSlot(temp);
                temp++;
            }
        }
    }

    public void play(TTTPlayer player, int colum, int line) {
        getSlots()[colum][line].setPlayed(player);
    }

    public PlayAreaSlot[][] getSlots() {
        return slots;
    }

    public PlayAreaSlot getSlot(int colum, int line) {
        return getSlots()[colum][line];
    }
}
