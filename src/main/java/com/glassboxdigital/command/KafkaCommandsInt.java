package com.glassboxdigital.command;

public interface KafkaCommandsInt {
    String KAFKA_CONSUMER_GROUP = "export JAVA_HOME=/opt/glassbox/_jvm8_linux; /opt/glassbox/clifka/bin/kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group";
}
