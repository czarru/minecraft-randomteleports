package Teleports;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;

public class Main extends JavaPlugin
{
    public static int Xmin;
    public static int Xmax;
    public static int Zmin;
    public static int Zmax;
    
    public void onEnable() {
        super.onEnable();
        this.getServer().getPluginManager().registerEvents((Listener)new Events(), (Plugin)this);
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
    
    public static int getRandomX() {
        final Random r = new Random();
        return r.nextInt(Main.Xmax - Main.Xmin) + Main.Xmin;
    }
    
    public static int getRandomZ() {
        final Random r = new Random();
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
    
    static {
        Main.Xmin = -100;
        Main.Xmax = 100;
        Main.Zmin = -100;
        Main.Zmax = 100;
    }
}
