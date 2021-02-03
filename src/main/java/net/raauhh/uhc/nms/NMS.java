package net.raauhh.uhc.nms;

import net.raauhh.uhc.manager.UHCPlayer;
import net.raauhh.uhc.util.Skin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface NMS {

    void sendTittle(Player player, String title);

    void removeArrows(Player player);

    void addVehicle(Player player);

    void removeVehicle(Player player);

    void removeMovement(Entity entity);

    void removePlayer(Player player, Player target);

    Skin getSkin(Player player);

    void changeSkin(UHCPlayer uhcPlayer, boolean steve);

    boolean isItem(Material mat);

    void firework(Location location);

    double getHealth(Player player);

    int getRandomFortuneDrops(Block block, int level);
}
