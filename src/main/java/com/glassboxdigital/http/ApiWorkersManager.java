package com.glassboxdigital.http;
import com.glassboxdigital.http.ApiRestClient;
import com.glassboxdigital.http.RestWorker;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiWorkersManager {
    ExecutorService executorService;
    private static final String THREAD_NAME = "ApiWorkersManager";

    public void executeAsyncService(int ConcurrentSession) {
        ApiRestClient.disableSslVerification();
        executorService = Executors.newFixedThreadPool(ConcurrentSession, new ThreadFactoryBuilder().setNameFormat(THREAD_NAME).build());
        for (int i = 0; i < ConcurrentSession; i++) {
            executorService.execute(createRestWorker());
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }


    private Runnable createRestWorker() {
        String uri = "/j_spring_security_check?j_username=clarisite&j_password=ysyghb&redirect_response=true&submit=Login&application=x-www-form-urlencoded";
        Runnable worker = new RestWorker(uri, "");
        return worker;
    }
}
