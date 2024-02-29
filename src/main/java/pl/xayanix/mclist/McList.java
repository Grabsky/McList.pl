package pl.xayanix.mclist;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.xayanix.mclist.commands.McListCommand;
import pl.xayanix.mclist.config.Configuration;
import pl.xayanix.mclist.listeners.PlayerJoinListener;
import pl.xayanix.mclist.user.McListUserManager;

import java.io.IOException;

@Getter
public class McList extends JavaPlugin {

    @Getter
    private static McList instance;

    private Configuration configuration;

    public void onEnable(){
        instance = this;

        try {
            this.configuration = new Configuration();
        } catch (IOException e) { }

        new McListUserManager();
        new PlayerJoinListener(this);

        this.getCommand("mclist").setExecutor(new McListCommand());
        this.getLogger().info("Poprawnie zaladowano plugin.");
    }

}
