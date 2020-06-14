package com.glassboxdigital.models;

import java.util.ArrayList;

public class Command {
    String command;
    ArrayList<String> results;

    public Command(String command, ArrayList<String> results) {
        this.command = command;
        this.results = results;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }
}
