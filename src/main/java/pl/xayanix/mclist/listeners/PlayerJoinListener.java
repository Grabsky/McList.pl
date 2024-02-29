package pl.xayanix.mclist.listeners;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.xayanix.mclist.McList;

import java.io.IOException;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener(JavaPlugin instance) {
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(McList.getInstance(), () -> {
            try {
                McList.getInstance().getConfiguration().loadUserData(player);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        });
    }
}
