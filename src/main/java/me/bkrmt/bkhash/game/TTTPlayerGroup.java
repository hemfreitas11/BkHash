package me.bkrmt.bkhash.game;

public class TTTPlayerGroup {
    private final TTTPlayer player1;
    private final TTTPlayer player2;

    public TTTPlayerGroup(TTTPlayer player1, TTTPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public TTTPlayer getPlayer1() {
        return player1;
    }

    public TTTPlayer getPlayer2() {
        return player2;
    }
}
