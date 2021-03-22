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
import me.bkrmt.bkhash.game.events.TTTPlayEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TTTCommand extends Executor {

    public TTTCommand(BkPlugin plugin, String langKey, String permission) {
        super(plugin, langKey, permission);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            Player player1 = (Player) commandSender;
            Player player2 = Utils.getPlayer("tester");
            TTTPlayerGroup players = new TTTPlayerGroup(new TTTPlayer(player1, PlayerSymbol.O), new TTTPlayer(player2, PlayerSymbol.X));
            TTTGameStartEvent event = new TTTGameStartEvent(players);
            getPlugin().callEvent(event);
            if (!event.isCanceled()) {
                TTTGame game = new TTTGame(players);
                BkHash.getGameManager().getActiveGames().put(players.getPlayer1().getPlayer().getUniqueId(), game);
                BkHash.getGameManager().getActiveGames().put(players.getPlayer2().getPlayer().getUniqueId(), game);
            } else {
                commandSender.sendMessage("already playing");
            }
        } else if (args.length == 1) {
            if (BkHash.getGameManager().getActiveGames().containsKey(((Player)commandSender).getUniqueId())) {
                TTTGame game = BkHash.getGameManager().getActiveGames().get(((Player)commandSender).getUniqueId());
                UUID id = ((Player)commandSender).getUniqueId();
                TTTPlayEvent event = new TTTPlayEvent(BkHash.getGameManager().getActiveGames().get(id), game.getPlayer(id), Integer.parseInt(args[0]));
                getPlugin().callEvent(event);
            }
        }
        return true;
    }
}
