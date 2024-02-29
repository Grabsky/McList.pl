package pl.xayanix.mclist.config;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.xayanix.mclist.McList;
import pl.xayanix.mclist.constant.Constants;
import pl.xayanix.mclist.message.ColouredMessage;
import pl.xayanix.mclist.user.McListUser;
import pl.xayanix.mclist.user.McListUserManager;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private File userFolder;

    public Configuration() throws IOException {
        McList.getInstance().saveDefaultConfig();
        Constants.NO_PERMISSION_MESSAGE = new ColouredMessage(McList.getInstance().getConfig().getString("messages.no_permission"));
        Constants.SUCCESS = new ColouredMessage(McList.getInstance().getConfig().getString("messages.success"));
        Constants.CONNECTING = new ColouredMessage(McList.getInstance().getConfig().getString("messages.connecting"));
        Constants.FAILED = new ColouredMessage(McList.getInstance().getConfig().getString("messages.failed"));
        Constants.COOLDOWN = new ColouredMessage(McList.getInstance().getConfig().getString("messages.cooldown"));
        Constants.ALREADY_RECEIVED_REWARD = new ColouredMessage(McList.getInstance().getConfig().getString("messages.already_received_reward"));
        Constants.API_EXCEPTION = new ColouredMessage(McList.getInstance().getConfig().getString("messages.api_exception"));
        Constants.SERVER_IP = McList.getInstance().getConfig().getString("server-ip");
        Constants.COMMAND_COOLDOWN = McList.getInstance().getConfig().getLong("command-cooldown");
        Constants.COMMANDS_TO_EXECUTE = McList.getInstance().getConfig().getStringList("commands");
        this.userFolder = new File(McList.getInstance().getDataFolder().getPath() + "/users/");

        if(!this.userFolder.exists())
            this.userFolder.mkdirs();
    }

    public void loadUserData(CommandSender player) throws IOException, InvalidConfigurationException {
        File data = new File(userFolder.getPath() + "/" + player.getName().toLowerCase() + ".yml");
        if(!data.exists())
            return;

        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.load(data);

        McListUser mcListUser = new McListUser(yamlConfiguration.getString("data.name"));
        mcListUser.setLastRewardReceiveTime(yamlConfiguration.getLong("data.lastRewarded"));

        McListUserManager.getInstance().getMcListUserConcurrentHashMap().put(mcListUser.getName().toLowerCase(), mcListUser);

    }

    public void saveUserData(CommandSender player) throws IOException {
        McListUser mcListUser = McListUserManager.getInstance().getUser(player);
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        yamlConfiguration.set("data.name", mcListUser.getName());
        yamlConfiguration.set("data.lastRewarded", mcListUser.getLastRewardReceiveTime());

        yamlConfiguration.save(userFolder.getPath() + "/" + player.getName().toLowerCase() + ".yml");
    }

}
