package com.glassboxdigital;

import com.glassboxdigital.clients.SshClient;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<SshClient> queue;

    public Consumer(BlockingQueue<SshClient> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            try {
                SshClient client = queue.take();
                client.run();
                if (queue.isEmpty())
                    queue.notify(); // notify the producer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}