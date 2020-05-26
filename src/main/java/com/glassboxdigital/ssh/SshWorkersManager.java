package com.glassboxdigital.ssh;


public class SshWorkersManager {

    public static void main(String[] args) {
       //WorkerSsh worker1 = new WorkerSsh("10.10.1.40",new String[]{"grep -r \"MessageConsumerStats\" /opt/glassbox/clingine/log/servers.root.log","cat /opt/glassbox/clingine/log/session_pipeline_metrics.csv"});
        WorkerSsh worker2 = new WorkerSsh("10.10.1.238",new String[]{"clickhouse-client;\n"});
      //  WorkerSsh worker3 = new WorkerSsh("10.10.1.176",new String[]{"cd /opt/glassbox/clifka/bin","export JAVA_HOME=/opt/glassbox/_jvm8_linux/./kafka-consumer-groups.sh -bootstrap-server localhost:8093 -describe -group beacon_offline_group"});
       //worker1.run();
        worker2.run();
       // worker3.run();
    }
}