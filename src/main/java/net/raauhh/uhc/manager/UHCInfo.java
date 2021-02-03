package net.raauhh.uhc.manager;

import net.raauhh.uhc.UHC;
import net.raauhh.uhc.manager.event.GameStartEvent;
import net.raauhh.uhc.util.TaskUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by Ryzeon
 * Project: GhoulUHC
 * Date: 15/01/2021 @ 05:32 p. m.
 */

public class UHCInfo implements Listener {

    private BukkitTask bukkitTask;

    public UHCInfo() {
        Bukkit.getPluginManager().registerEvents(this, UHC.getInstance());
        startTask();
    }

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        startTaskGame();
    }

    public void startTaskGame() {
        if (bukkitTask != null) {
            UHC.getInstance().getLogger().info("[UHC-Info] Canceling an old task to start a new one");
            bukkitTask.cancel();
            bukkitTask = null;
        }
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                updateMotd();
            }
        }.runTaskTimer(UHC.getInstance(), 0, 20 * 5);
    }

    public void startTask() {
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                updateMotd();
            }
        }.runTaskTimer(UHC.getInstance(), 0, 20);
    }

    private void updateMotd() {
        TaskUtil.runAsync(() -> {
            UHC.getInstance().getCoreAPI().setServerMotD(UHC.getInstance().getCoreAPI().getUHCUtils().serializeUHC());
        });
    }
}
