package me.bkrmt.bkhash;


import com.comphenix.protocol.Packets;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.bkrmt.bkcore.BkPlugin;
import me.bkrmt.bkcore.command.CommandModule;
import me.bkrmt.bkhash.commands.TTTCommand;
import me.bkrmt.bkhash.game.GameManager;

import java.util.UUID;

public class BkHash extends BkPlugin {
    public static BkHash plugin;
    private static GameManager gameManager;

    public void onEnable() {
        plugin = this;
        start()
                .addCommand(new CommandModule(new TTTCommand(this, "teste", "bkteste1.perm"), null))
                .registerAll();
        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(new TTTListener(), this);

        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(this, ConnectionSide.SERVER_SIDE, Packets.Server.CHAT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        UUID playerID = event.getPlayer().getUniqueId();

                        if (BkHash.getGameManager().getActiveGames().containsKey(playerID)) {
                            event.setCancelled(true);
                        }
                    }
                });
    }

    public static GameManager getGameManager() {
        return gameManager;
    }
}
