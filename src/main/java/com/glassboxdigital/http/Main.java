package com.glassboxdigital.http;

import com.glassboxdigital.http.utils.ApiWorkersManager;

public class Main {

    public static void main(String[] args) {
        //ApiGenParameters parameters = new ApiGenParameters();
        //ArgsParser.parseArguments(parameters, args);
        ApiWorkersManager apiManager = new ApiWorkersManager();
        apiManager.executeAsyncService(1000000);
    }
}
