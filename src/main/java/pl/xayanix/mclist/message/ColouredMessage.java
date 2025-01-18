package pl.xayanix.mclist.message;

import lombok.Getter;
import org.bukkit.command.CommandSender;

public class ColouredMessage {

    @Getter
    private final String message;

    public ColouredMessage(String message){
        this.message = message.replace("&", "").replace("§", "");
    }

    public void sendMessage(CommandSender commandSender){
        if ("".equals(this.message) == false)
            commandSender.sendRichMessage(this.message);
    }

}
