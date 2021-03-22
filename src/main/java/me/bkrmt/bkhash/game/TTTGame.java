package me.bkrmt.bkhash.game;

import me.bkrmt.bkhash.game.playarea.PlayArea;
import me.bkrmt.bkhash.game.playarea.PlayAreaSlot;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.UUID;

import static me.bkrmt.bkhash.BkHash.plugin;

public class TTTGame {

    private final TTTPlayerGroup players;
    private final PlayArea playArea;
    private int movesPlayed;

    public TTTGame(TTTPlayerGroup players) {
        this.players = players;
        playArea = new PlayArea();
        movesPlayed = -1;

        int firstMove = plugin.getConfig().getInt("who-starts");
        while (firstMove == 0) {
            firstMove = (int)(2.0 * Math.random());
        }

        if (firstMove == 1) {
            setHasTurn(getPlayers().getPlayer1());
        } else if (firstMove == 2) {
            setHasTurn(getPlayers().getPlayer2());
        }

        drawPlayArea();
    }

    public TTTPlayer getPlayer(UUID id) {
        if (id.equals(getPlayers().getPlayer1().getPlayer().getUniqueId())) return getPlayers().getPlayer1();
        else if (id.equals(getPlayers().getPlayer2().getPlayer().getUniqueId())) return getPlayers().getPlayer2();
        else return null;
    }

    public TTTPlayerGroup getPlayers() {
        return players;
    }

    public PlayArea getPlayArea() {
        return playArea;
    }

    public void setHasTurn(TTTPlayer player) {
        setMovesPlayed(getMovesPlayed() + 1);
        if (player == getPlayers().getPlayer1()) {
            getPlayers().getPlayer1().setTurnToPlay(true);
            getPlayers().getPlayer2().setTurnToPlay(false);
        } else if (player == getPlayers().getPlayer2()) {
            getPlayers().getPlayer2().setTurnToPlay(true);
            getPlayers().getPlayer1().setTurnToPlay(false);
        }
    }

    public void drawPlayArea() {
        draw(getPlayers().getPlayer1());
        draw(getPlayers().getPlayer2());
    }

    private void draw(TTTPlayer player) {
        if (plugin.getConfig().getBoolean("wipe-chat")) {
            for (int d = 0; d < 100; d++) {
                player.getPlayer().sendMessage(" ");
            }
        }
        if (player.isTurnToPlay()) player.getPlayer().sendMessage("Your turn");
        else player.getPlayer().sendMessage("Opponent's turn");
        if (player.getPlayer().isOnline()) {
            for (int c = 0; c < 3; c++) {
                TextComponent line = new TextComponent(" | ");
                for (int i = 0; i < 3; i++) {
                    PlayAreaSlot slot =getPlayArea().getSlots()[c][i];
                    String button = slot.isPlayed() ? slot.getPlayer().getSymbol().getString() : String.valueOf(slot.getSlotNumber());
                    line.addExtra(getTextComponent(button, slot.getSlotNumber()));
                    line.addExtra(" | ");
                }
                if (player.getPlayer().isOnline()) {
                    player.getPlayer().spigot().sendMessage(line);
                } else {
                    //game over
                }
            }
        } else {
            //game over
        }
    }

    private TextComponent getTextComponent(String button, int key/*, String s*/) {
        TextComponent buttonAccept;
        String hover;
        buttonAccept = new TextComponent(/*plugin.getLangFile().get(key)*/String.valueOf(button));
        hover = /*plugin.getLangFile().get(sectionName + s)*/ " asd casj da s";
        buttonAccept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teste " + key));
        buttonAccept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
        return buttonAccept;
    }

    public int getMovesPlayed() {
        return movesPlayed;
    }

    public void setMovesPlayed(int movesPlayed) {
        this.movesPlayed = movesPlayed;
    }
}
