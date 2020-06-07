package com.glassboxdigital.clients.ssh;

import org.apache.poi.ss.usermodel.Sheet;

public class Clifka extends SshClient {
    public Clifka(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }


    public void runAndCreateKafkaConsumerGroup(Sheet sheet) {
        runAndCreateKafkaConsumerGroup(sheet,new String[]{KAFKA_CONSUMER_GROUP});
    }
}
