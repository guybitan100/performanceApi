package com.glassboxdigital.clients.ssh;

import com.glassboxdigital.command.KafkaCommandsInt;
import org.apache.poi.ss.usermodel.Sheet;

public class Clifka extends SshClient implements KafkaCommandsInt {
    public Clifka(String host, String user, String privateKeyLocation) {
        super(host, user, privateKeyLocation);
    }


    public void publishKafkaConsumerGroup(Sheet sheet) {
        publishKafkaConsumerGroup(sheet, new String[]{KAFKA_CONSUMER_GROUP});
    }
}
