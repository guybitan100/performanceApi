package com.glassboxdigital;

import com.glassboxdigital.clients.Client;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<Client> queue;

    public Consumer(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Client client = queue.take();
                client.run();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}