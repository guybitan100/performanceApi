package com.glassboxdigital.models;

import java.util.ArrayList;
import java.util.Date;

public class Asset {
    String ip;
    Date date;
    ArrayList<Command> commands;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
}
