package com.glassboxdigital.models;

import java.util.ArrayList;

public class Command {
    String command;
    StringBuffer results;

    public Command(String command) {
        this.command = command;
        results = new StringBuffer();

    }

    public Command() {
        this.results = new StringBuffer();
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public String[] getResultsLines() {
        return results.toString().split("\\n");
    }

    public StringBuffer getResults() {
        return results;
    }
}
