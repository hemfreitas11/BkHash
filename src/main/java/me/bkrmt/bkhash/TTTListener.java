package me.bkrmt.bkhash;

import me.bkrmt.bkhash.game.TTTGame;
import me.bkrmt.bkhash.game.TTTPlayer;
import me.bkrmt.bkhash.game.events.TTTGameOverEvent;
import me.bkrmt.bkhash.game.events.TTTGameStartEvent;
import me.bkrmt.bkhash.game.events.TTTPlayEvent;
import me.bkrmt.bkhash.game.events.TTTSucessfulPlayEvent;
import me.bkrmt.bkhash.game.playarea.PlayAreaSlot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static me.bkrmt.bkhash.BkHash.plugin;

public class TTTListener implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlay(TTTPlayEvent event) {
        if (event.getWhoPlayed().isTurnToPlay()) {
            int temp = 1;
            for (int c = 0; c < 3; c++) {
                for (int i = 0; i < 3; i++) {
                    if (temp == event.getMove()) {
                        PlayAreaSlot slot = event.getGame().getPlayArea().getSlot(c, i);
                        if (!slot.isPlayed()) {
                            slot.setPlayed(event.getWhoPlayed());
                            TTTGame game = event.getGame();
                            game.setHasTurn(event.getWhoPlayed().equals(game.getPlayers().getPlayer1()) ? game.getPlayers().getPlayer2() : game.getPlayers().getPlayer1());
                            game.drawPlayArea();
                            plugin.callEvent(new TTTSucessfulPlayEvent(event.getGame(), event.getWhoPlayed()));
                        } else {
                            event.getWhoPlayed().getPlayer().sendMessage("play again");
                        }
                        return;
                    } else temp++;
                }
            }
        } else {
            event.getWhoPlayed().getPlayer().sendMessage("not your turn");
        }
    }

    @EventHandler
    public void onLeaveServer(PlayerQuitEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();
        if (BkHash.getGameManager().getActiveGames().containsKey(playerID)) {
            TTTGame game = BkHash.getGameManager().getActiveGames().get(playerID);
            if (playerID.equals(game.getPlayers().getPlayer2().getPlayer().getUniqueId())) {
                game.getPlayers().getPlayer1().getPlayer().sendMessage("player 2 quit");
            } else if (playerID.equals(game.getPlayers().getPlayer1().getPlayer().getUniqueId())) {
                game.getPlayers().getPlayer2().getPlayer().sendMessage("player 1 quit");
            }
            TTTGameOverEvent gameOverEvent = new TTTGameOverEvent(game, null);
            plugin.callEvent(gameOverEvent);
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onGameStart(TTTGameStartEvent event) {
        if (BkHash.getGameManager().getActiveGames().containsKey(event.getPlayers().getPlayer1().getPlayer().getUniqueId()) ||
                BkHash.getGameManager().getActiveGames().containsKey(event.getPlayers().getPlayer2().getPlayer().getUniqueId())){
            event.setCanceled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onGameOver(TTTGameOverEvent event) {
        TTTPlayer winner = event.getWinner();
        if (winner == null) {
            event.getGame().getPlayers().getPlayer1().getPlayer().sendMessage("no winner");
            event.getGame().getPlayers().getPlayer2().getPlayer().sendMessage("no winner");
        } else if (winner.equals(event.getGame().getPlayers().getPlayer1())) {
            event.getGame().getPlayers().getPlayer1().getPlayer().sendMessage("you win");
            event.getGame().getPlayers().getPlayer2().getPlayer().sendMessage("opponent win");
        } else if (winner.equals(event.getGame().getPlayers().getPlayer2())) {
            event.getGame().getPlayers().getPlayer2().getPlayer().sendMessage("you win");
            event.getGame().getPlayers().getPlayer1().getPlayer().sendMessage("opponent win");
        }
        BkHash.getGameManager().getActiveGames().remove(event.getGame().getPlayers().getPlayer1().getPlayer().getUniqueId());
        BkHash.getGameManager().getActiveGames().remove(event.getGame().getPlayers().getPlayer2().getPlayer().getUniqueId());
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void checkWinner(TTTSucessfulPlayEvent event) {
        if (!playerWon(event)) {
            if (event.getGame().getMovesPlayed() == 9) {
                plugin.callEvent(new TTTGameOverEvent(event.getGame(), null));
            }
        }
    }

    private boolean playerWon(TTTSucessfulPlayEvent event) {
        PlayAreaSlot[][] slots = event.getGame().getPlayArea().getSlots();
        boolean returnValue = false;

        for (int c = 0; c < 3; c++) {
            if (isEqual(slots[c][0], slots[c][1]) && isEqual(slots[c][0], slots[c][2])) {
                returnValue = true;
                endGame(event.getGame(), slots[c][0].getPlayer());
            }
        }
        for (int c = 0; c < 3; c++) {
            if (isEqual(slots[0][c], slots[1][c]) && isEqual(slots[0][c], slots[2][c])) {
                returnValue = true;
                endGame(event.getGame(), slots[0][c].getPlayer());
            }
        }

        if (isEqual(slots[0][0], slots[1][1]) && isEqual(slots[0][0], slots[2][2])) {
                returnValue = true;
            endGame(event.getGame(), slots[0][0].getPlayer());
        } else if ((isEqual(slots[0][2], slots[1][1]) && isEqual(slots[0][2], slots[2][0]))) {
                returnValue = true;
            endGame(event.getGame(), slots[0][2].getPlayer());
        }
        return returnValue;
    }

    private void endGame(TTTGame game, TTTPlayer player) {
        TTTGameOverEvent gameOverEventevent = new TTTGameOverEvent(game, player);
        plugin.callEvent(gameOverEventevent);
    }

    private boolean isEqual(PlayAreaSlot slot1, PlayAreaSlot slot2) {
        boolean returnValue = false;
        if (slot1.isPlayed() && slot2.isPlayed()) {
            if (slot1.getPlayer().getSymbol().equals(slot2.getPlayer().getSymbol())) {
                returnValue = true;
            }
        }
        return returnValue;
    }
}
