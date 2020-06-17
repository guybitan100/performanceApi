package com.glassboxdigital.xls;

import java.util.ArrayList;

public class Commands {
    ArrayList<Command> commands = new ArrayList<Command>();

    public void add(Command cmd) {
        this.commands.add(cmd);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Command cmd : commands) {
            stringBuffer.append(cmd.toString());
        }
        return stringBuffer.toString();
    }


}
