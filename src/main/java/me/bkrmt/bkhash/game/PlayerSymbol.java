package me.bkrmt.bkhash.game;

public enum PlayerSymbol {
    O("O"),
    X("X");

    private final String symbol;

    PlayerSymbol(String message) {
        this.symbol = message;
    }

    public String getString() {
        return symbol;
    }
}
