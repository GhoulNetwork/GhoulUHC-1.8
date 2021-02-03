package net.raauhh.uhc.command;

import net.raauhh.uhc.UHC;
import net.raauhh.uhc.manager.UHCPlayer;
import net.raauhh.uhc.manager.gamemode.Gamemode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackPackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        if (args.length == 1) {
            if (!Gamemode.getByName("BackPack").isEnabled()) {
                player.sendMessage("§cBackpacks are disabled on this match.");
                return true;
            }

            UHCPlayer uhcPlayer = UHCPlayer.getByUuid(player.getUniqueId());
            if (UHC.getInstance().getGame().getHost().contains(uhcPlayer) || UHC.getInstance().getGame().getModerators().contains(uhcPlayer)) {
                UHCPlayer targetUhcPlayer = UHCPlayer.getByName(args[0]);
                if (targetUhcPlayer == null || !targetUhcPlayer.isOnline()) {
                    player.sendMessage("§cCouldn't find a player with the name " + args[0]);
                    return true;
                }

                player.openInventory(targetUhcPlayer.getUhcTeam().getBackPack());
            }
        } else {
            player.performCommand("team inventory");
        }
        return true;
    }
}
