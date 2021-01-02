package Teleports;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin
{
    public static int Xmin;
    public static int Xmax;
    public static int Zmin;
    public static int Zmax;
    public static Random r = new Random();
    private static Main instance;
    
    @SuppressWarnings("static-access")
	public void onEnable() {
    	instance = this;
    	saveDefaultConfig();
        super.onEnable();
        this.Xmin = getConfig().getInt("minX");
        this.Xmax = getConfig().getInt("maxX");
        this.Zmin = getConfig().getInt("minZ");
        this.Zmax = getConfig().getInt("maxZ");
        getServer().getPluginManager().registerEvents((Listener)new Events(), (Plugin)this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + " Plugin zostal uruchomiony w wersji " + ChatColor.WHITE + this.getDescription().getVersion());

    }
    
    public static ArrayList<Player> getNearbyPlayers(final Location l, final Player p) {
        final ArrayList<Player> nearby = new ArrayList<Player>();
        final double range = 3.0;
        for (final Entity e : l.getWorld().getNearbyEntities(l, range, range, range)) {
            if (e instanceof Player && !((Player)e).equals(p)) {
                nearby.add((Player)e);
            }
        }
        return nearby;
    }
    
    public static Main getInst() {
        return Main.instance;
    }
    
    public static int getRandomX() {
        return r.nextInt(Main.Xmax - Main.Xmin) + Main.Xmin;
    }
    
    public static int getRandomZ() {
        return r.nextInt(Main.Zmax - Main.Zmin) + Main.Zmin;
    }
    
    public static Location getRandomLocation(final World w) {
        boolean safe = false;
        Location l;
        do {
            l = w.getHighestBlockAt(getRandomX(), getRandomZ()).getLocation();
            for (int i = 1; i <= 3; ++i) {
                if (l.subtract(0.0, 1.0, 1.0).getBlock().getType() != Material.AIR) {
                    safe = true;
                    l.add(0.0, (double)i, 0.0);
                }
            }
        } while (!safe);
        return l;
    }
}
