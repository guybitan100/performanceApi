package com.glassboxdigital;

import com.glassboxdigital.clients.ssh.Clingine;
import com.glassboxdigital.clients.ssh.TrafficGenerator;
import com.glassboxdigital.conf.Configuration;
import com.glassboxdigital.clients.ssh.SshClient;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import com.glassboxdigital.clients.RestClient;
import com.glassboxdigital.models.*;

import java.util.concurrent.BlockingQueue;

public class Main {


    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration("ssh.properties");
        String clingine = conf.get("clingine");
        String cloff = conf.get("cloff");
        String clifka = conf.get("clifka");
        String user = conf.get("user");
        String tg1 = conf.get("tg1");
        String tg2 = conf.get("tg2");
        String privateKeyLocation = conf.get("privateKeyLocation");
//        TrafficGenerator trafficGen1 = new TrafficGenerator(tg1, user, privateKeyLocation);
//        TrafficGenerator trafficGen2 = new TrafficGenerator(tg2, user, privateKeyLocation);
//        trafficGen1.isUp();
//        trafficGen2.isUp();
//        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CPU_STATUS, sshCommands.MEM_STATUS, sshCommands.SERVER_ROOT_MSG_CONSUMER_STAT}).run();
//        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.SESSION_PIPELINE_METRICS_CSV_FILE}).run();
        new Clingine(clingine, user, privateKeyLocation).getOpenFiles();

//            new SshClient(cloff, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, sshCommands.CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
//            new SshClient(clifka, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.KAFKA_CONSUMER_GROUP});
//            new SshClient(tg1, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});
//            new SshClient(tg2, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});


    }
}