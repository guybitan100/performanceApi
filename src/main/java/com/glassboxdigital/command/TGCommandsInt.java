package com.glassboxdigital.command;

public interface TGCommandsInt {
    String PS_TG_STATUS = "ps aux | grep -v grep |grep \"raffi\"";
        String TOP_TG = "top -bn 1 | grep \"raffi\"";
}
