package fr.communaywen.core.tpa;

import fr.communaywen.core.AywenCraftPlugin;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class TpacceptCommand {
    TPAQueue tpQueue = TPAQueue.INSTANCE;

    @Command("tpaccept")
    @CommandPermission("ayw.command.tpa")
    public void onCommand(Player player) {
        Player tpaplayer = tpQueue.TPA_REQUESTS.get(player);
        if (tpaplayer == null) {
            player.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de téléporation");
            return;
        }
        tpQueue.TPA_REQUESTS.remove(player);
        player.sendMessage(tpaplayer.getName()+" va être téléporté à vous dans 3 secondes");
        tpaplayer.sendTitle("Téléportation à "+player.getName()," dans 3 secondes...",0,20,40);
        new BukkitRunnable() {
            @Override
            public void run() {
                tpaplayer.teleport(player);
            }
        }.runTaskLater(AywenCraftPlugin.getInstance(),60);
    }
}