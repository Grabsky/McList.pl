package pl.xayanix.mclist.commands;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.xayanix.mclist.McList;
import pl.xayanix.mclist.config.Configuration;
import pl.xayanix.mclist.constant.Constants;
import pl.xayanix.mclist.time.TimeUtil;
import pl.xayanix.mclist.user.McListUser;
import pl.xayanix.mclist.user.McListUserManager;

import java.io.IOException;
import java.net.URI;

public class McListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This command is for players only.");
            commandSender.sendMessage("Ta komenda jest tylko dla graczy.");
            return false;
        }

        if(!commandSender.hasPermission("mclist.odbierz")){
            Constants.NO_PERMISSION_MESSAGE.sendMessage(commandSender);
            return false;
        }

        McListUser mcListUser = McListUserManager.getInstance().getUser(commandSender);
        if(mcListUser.getLastMcListCommandUse() > System.currentTimeMillis()){
            Constants.COOLDOWN.sendMessage(commandSender);
            return false;
        }

        if(mcListUser.getLastRewardReceiveTime() > System.currentTimeMillis()){
            Constants.ALREADY_RECEIVED_REWARD.sendMessage(commandSender);
            return false;
        }

        Constants.CONNECTING.sendMessage(commandSender);

        Bukkit.getScheduler().runTaskAsynchronously(McList.getInstance(), () -> {
            try {
                mcListUser.setLastMcListCommandUse(System.currentTimeMillis() + Constants.COMMAND_COOLDOWN);
                String out = IOUtils.toString(URI.create("https://www.mclist.pl/api/1.1/like/" + ((Player) commandSender).getAddress().getAddress().getHostAddress() + "/" + Constants.SERVER_IP));
                if(out.equalsIgnoreCase("1")){
                    mcListUser.setLastRewardReceiveTime(TimeUtil.getEndOfDay());
                    Bukkit.getScheduler().runTask(McList.getInstance(), () ->
                            Constants.COMMANDS_TO_EXECUTE.forEach(c ->
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c.replaceFirst("/", "").replace("%player%", commandSender.getName()))));

                    Constants.SUCCESS.sendMessage(commandSender);
                    McList.getInstance().getConfiguration().saveUserData(commandSender);
                    return;
                }

                Constants.FAILED.sendMessage(commandSender);

            } catch (IOException e) {
                Constants.API_EXCEPTION.sendMessage(commandSender);
                e.printStackTrace();
            }
        });


        return true;
    }

}
