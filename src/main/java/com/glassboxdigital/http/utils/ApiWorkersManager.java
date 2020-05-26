package com.glassboxdigital.http.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiWorkersManager {
    ExecutorService executorService;
    private static final String THREAD_NAME = "ApiWorkersManager";

    public void executeAsyncService(int ConcurrentSession) {
        disableSslVerification();
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
    private void disableSslVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
