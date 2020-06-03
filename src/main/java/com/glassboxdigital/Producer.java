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

public class Producer implements Runnable {

    private final BlockingQueue<SshClient> queue;

    public Producer(BlockingQueue<SshClient> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Configuration conf = new Configuration("ssh.properties");
        String clingine = conf.get("clingine");
        String cloff = conf.get("cloff");
        String clifka = conf.get("clifka");
        String user = conf.get("user");
        String tg1 = conf.get("tg1");
        String tg2 = conf.get("tg2");
        String privateKeyLocation = conf.get("privateKeyLocation");
        SshCommands sshCommands = new SshCommands();
        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CPU_STATUS, sshCommands.MEM_STATUS, sshCommands.SERVER_ROOT_MSG_CONSUMER_STAT}).run();
        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.SESSION_PIPELINE_METRICS_CSV_FILE}).run();
        new Clingine(clingine, user, privateKeyLocation, new String[]{sshCommands.LSOF_ALL, sshCommands.LSOF_FTS, sshCommands.LSOF_RECENT, sshCommands.LSOF_LOG, sshCommands.LSOF_JAR, sshCommands.LSOF_PIPE, sshCommands.LSOF_EVENT_POLL, sshCommands.LSOF_EVENT_JOURNEY}).run();

//            new SshClient(cloff, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS, sshCommands.CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR});
//            new SshClient(clifka, user, privateKeyLocation, new String[]{sshCommands.CLI_STATUS, sshCommands.KAFKA_CONSUMER_GROUP});
//            new SshClient(tg1, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});
//            new SshClient(tg2, user, privateKeyLocation, new String[]{sshCommands.TG_STATUS});

        RestClient.disableSslVerification();
        RestClient apiRestClient = new RestClient(conf.get("base_url"));
        HttpHeaders headers = apiRestClient.postEntity(conf.get("login_endpoint")).getHeaders();
        String set_cookie = (headers.getFirst(headers.SET_COOKIE)).split(";")[0];
        apiRestClient.addHeader("Cookie", set_cookie);
        Gson gson = new Gson();
        Sessions sessions = gson.fromJson("{\"timeFrame\": {\"from\":0 ,\"till\": 0},\"limit\": 100000,\"uniqueCount\": {\"field\": \"SESSIONGUID\"},\"steps\": [{\"name\": \"\",\"operator\": \"AND\",\"query\": [{\"field\": \"APPID\",\"value\": [\"3\"],\"operator\": \"AND\"}]}],\"filters\": {\"query\": []}}", Sessions.class);
        sessions.setTimeFrame(new TimeFrame(System.currentTimeMillis() - (86400000), System.currentTimeMillis()));
        String re = apiRestClient.post(conf.get("session_endpoint"), gson.toJson(sessions));
        Results res = gson.fromJson(re, Results.class);
    }
}