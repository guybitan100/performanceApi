package com.glassboxdigital.clients;

public class Clingine extends SshClient {

    public Clingine(String host, String user, String privateKeyLocation,String[] commands) {
        super(host, user, privateKeyLocation,commands);
    }

}