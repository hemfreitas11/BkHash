package me.bkrmt.bkhash.commands;

import me.bkrmt.bkcore.BkPlugin;
import me.bkrmt.bkcore.Utils;
import me.bkrmt.bkcore.command.Executor;
import me.bkrmt.bkhash.BkHash;
import me.bkrmt.bkhash.game.PlayerSymbol;
import me.bkrmt.bkhash.game.TTTGame;
import me.bkrmt.bkhash.game.TTTPlayer;
import me.bkrmt.bkhash.game.TTTPlayerGroup;
import me.bkrmt.bkhash.game.events.TTTGameStartEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptCmd extends Executor {

    public AcceptCmd(BkPlugin plugin, String langKey, String permission) {
        super(plugin, langKey, permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player1 = Utils.getPlayer(args[0]);
        Player player2 = (Player) sender;
        TTTPlayerGroup players = new TTTPlayerGroup(new TTTPlayer(player1, PlayerSymbol.O), new TTTPlayer(player2, PlayerSymbol.X));
        TTTGameStartEvent event = new TTTGameStartEvent(players);
        getPlugin().callEvent(event);
        if (!event.isCanceled()) {
            TTTGame game = new TTTGame(players);
            BkHash.getGameManager().getActiveGames().put(players.getPlayer1().getPlayer().getUniqueId(), game);
            BkHash.getGameManager().getActiveGames().put(players.getPlayer2().getPlayer().getUniqueId(), game);
        } else {
            // error
        }
        return true;
    }
}
