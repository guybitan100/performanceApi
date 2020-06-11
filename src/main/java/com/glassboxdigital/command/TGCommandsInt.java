package com.glassboxdigital.command;

public interface TGCommandsInt {
    String PS_TG_STATUS = "ps aux | grep -v grep |grep \"raffi\"";
    String TOP_TG = "top -bn 1 | grep \"raffi\"";
    String READ_3_LINES_FROM_TG_LOG1 = "tail -4 /opt/glassbox/tools/bin/runTrafficGenerator.log";
    String READ_3_LINES_FROM_TG_LOG2 = "tail -4 /opt/glassbox/tools/bin/runTrafficGenerator2.log";
    String READ_15_LINES_FROM_TG_LOG1 = "tail -15 /opt/glassbox/tools/bin/runTrafficGenerator.log";
    String READ_15_LINES_FROM_TG_LOG2 = "tail -15 /opt/glassbox/tools/bin/runTrafficGenerator2.log";

}
