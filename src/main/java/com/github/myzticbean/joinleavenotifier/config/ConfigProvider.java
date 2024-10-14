package com.github.myzticbean.joinleavenotifier.config;

import lombok.Getter;

import java.util.List;

@Getter
public class ConfigProvider {

    private final int configVersion;
    private final List<String> playerJoinMessages;
    private final List<String> playerLeaveMessages;

    public ConfigProvider(ConfigLoader configLoader) {
        configVersion = configLoader.getConfig().getInt("config-version");
        playerJoinMessages = configLoader.getConfig().getStringList("player-join-messages");
        playerLeaveMessages = configLoader.getConfig().getStringList("player-leave-messages");
    }

}
