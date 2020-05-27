package com.glassboxdigital.http;

import com.glassboxdigital.http.ApiRestClient;

public class RestWorker implements Runnable {
    private ApiRestClient apiRestClient = null;
    private String uri;
    private String json;

    public RestWorker(String uri, String json) {
        apiRestClient = new ApiRestClient("https://127.0.0.1/webinterface");
        this.uri = uri;
        this.json = json;
    }

    public void run() {
        apiRestClient.post(this.uri, this.json);
    }
}
