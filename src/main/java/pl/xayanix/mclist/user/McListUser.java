package pl.xayanix.mclist.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class McListUser {

    private final String name;
    private long lastMcListCommandUse;
    private long lastRewardReceiveTime;

}
