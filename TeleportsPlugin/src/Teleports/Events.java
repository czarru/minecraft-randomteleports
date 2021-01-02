package Teleports;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;

public class Events implements Listener
{
	
	//grupowy teleport 1vs1
	
	@EventHandler
    public void onUse(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.STONE_BUTTON) {
            final Button b = (Button)e.getClickedBlock().getState().getData();
            final Block bb = e.getClickedBlock().getRelative(b.getAttachedFace());
            if (bb.getType() == Material.GOLD_BLOCK) {
                final ArrayList<Player> list = Main.getNearbyPlayers(e.getClickedBlock().getLocation(), e.getPlayer());
                final Location l = Main.getRandomLocation(e.getPlayer().getWorld());
                if (list.size() == 0) {
                    TextUtilities.title(e.getPlayer(), "", String.valueOf(TextUtilities.rp(Main.getInst().getConfig().getString("nosolo"))), 0, 10, 0);
                    return;
                }
                final Player second = list.get(Main.r.nextInt(list.size()));
                e.getPlayer().teleport(l);
                second.teleport(l);
                e.getPlayer().sendMessage(String.valueOf(TextUtilities.rp(Main.getInst().getConfig().getString("success"))));
                second.sendMessage(String.valueOf(TextUtilities.rp(Main.getInst().getConfig().getString("success"))));
            }
            //teleport solo
            final Location l = Main.getRandomLocation(e.getPlayer().getWorld());
            if (bb.getType() == Material.SPONGE) {
                e.getPlayer().teleport(l);
                e.getPlayer().sendMessage(String.valueOf(TextUtilities.rp(Main.getInst().getConfig().getString("success"))));
            }
        }
    }
}
