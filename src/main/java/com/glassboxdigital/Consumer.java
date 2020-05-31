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
        synchronized (queue) {
            try {
                Client client = queue.take();
                client.run();
                if (queue.isEmpty())
                    queue.notify(); // notify the producer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}