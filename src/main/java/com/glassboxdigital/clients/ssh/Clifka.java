package com.glassboxdigital.clients.ssh;

import org.apache.poi.ss.usermodel.Sheet;

public class Clifka extends SshClient {
    public Clifka(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }

    private int privateRowNumber = 0;

    public void runAndCreateKafkaConsumerGroup(Sheet sheet, int publicRowNumber) {
        runAndCreateKafkaConsumerGroup(sheet, privateRowNumber + publicRowNumber, new String[]{KAFKA_CONSUMER_GROUP});
        privateRowNumber += 7;
    }
}
