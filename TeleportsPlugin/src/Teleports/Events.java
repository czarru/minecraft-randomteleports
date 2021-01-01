package Teleports;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.material.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Events implements Listener
{
	
	//grupowy teleport 1vs1
	
	@EventHandler
    public void onUse(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.STONE_BUTTON) {
            final Button b = (Button)e.getClickedBlock().getState().getData();
            final Block gold = e.getClickedBlock().getRelative(b.getAttachedFace());
            if (gold.getType() == Material.GOLD_BLOCK) {
                final ArrayList<Player> list = Main.getNearbyPlayers(e.getClickedBlock().getLocation(), e.getPlayer());
                final Location l = Main.getRandomLocation(e.getPlayer().getWorld());
                if (list.size() == 0) {
                    TitleP.title(e.getPlayer(), "", ChatColor.RED + "Nie mo¿esz siê sam teleportowaæ!", 0, 10, 0);
                    return;
                }
                final Random r = new Random();
                final Player second = list.get(r.nextInt(list.size()));
                e.getPlayer().teleport(l);
                second.teleport(l);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Pomyœlnie przeteleportowano!");
                second.sendMessage(ChatColor.GREEN + "Pomyœlnie przeteleportowano!");
            }
        }
    }
	
	//teleport
	
	@EventHandler
    public void onUse2(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.STONE_BUTTON) {
            final Button b = (Button)e.getClickedBlock().getState().getData();
            final Block sponge = e.getClickedBlock().getRelative(b.getAttachedFace());
            if (sponge.getType() == Material.SPONGE) {
                final Location l = Main.getRandomLocation(e.getPlayer().getWorld());
                e.getPlayer().teleport(l);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Pomyœlnie przeteleportowano!");
            }
        }
    }
}
