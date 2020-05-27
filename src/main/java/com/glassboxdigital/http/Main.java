package com.glassboxdigital.http;

public class Main {

    public static void main(String[] args) {
        //ApiGenParameters parameters = new ApiGenParameters();
        //ArgsParser.parseArguments(parameters, args);
        ApiWorkersManager apiManager = new ApiWorkersManager();
        apiManager.executeAsyncService(1000000);
    }
}
