package com.glassboxdigital.xls;

import java.util.ArrayList;

public class Command {
    String command;
    ArrayList<String> results;

    public Command(String command) {
        this.command = command;
        results = new ArrayList<String>();

    }

    public Command() {
        this.results = new ArrayList<String>();
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void add(String res) {
        this.results.add(res);
    }

    @Override
    public String toString() {
        return String.join("/n", results);
    }
}
