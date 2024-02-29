package pl.xayanix.mclist.user;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import pl.xayanix.mclist.McList;

import java.util.concurrent.ConcurrentHashMap;

public class McListUserManager {

    @Getter
    private static McListUserManager instance;

    @Getter
    private ConcurrentHashMap<String, McListUser> mcListUserConcurrentHashMap;

    public McListUserManager() {
        instance = this;
        this.mcListUserConcurrentHashMap = new ConcurrentHashMap<>();
    }

    public McListUser getUser(CommandSender player){
        if(!this.mcListUserConcurrentHashMap.containsKey(player.getName().toLowerCase())){
            McListUser mcListUser = new McListUser(player.getName());
            mcListUserConcurrentHashMap.put(player.getName().toLowerCase(), mcListUser);
            return mcListUser;
        }

        return this.mcListUserConcurrentHashMap.get(player.getName().toLowerCase());
    }

}
