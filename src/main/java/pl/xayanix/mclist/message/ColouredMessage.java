package pl.xayanix.mclist.message;

import lombok.Getter;
import org.bukkit.command.CommandSender;

public class ColouredMessage {

    @Getter
    private final String message;

    public ColouredMessage(String message){
        this.message = message.replace("&", "§");
    }

    public void sendMessage(CommandSender commandSender){
        if ("".equals(this.message) == true)
            return;
        commandSender.sendMessage(this.message);
    }

}
