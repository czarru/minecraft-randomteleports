package Teleports;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;

public class TitleP {
	
	//title packet
	
    public static void title(final Player p, String tt, String st, final int f1, final int s, final int f2) {
        final CraftPlayer craftPlayer = (CraftPlayer)p;
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + tt + "\"}");
        final PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)packetTitle);
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + st + "\"}");
        final PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)packetSubtitle);
    }
}
