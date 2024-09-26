package org.example.question2;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void addMessage(String message) {
        try {
            queue.put(message);
            synchronized (this) {
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }
    }

    public String getMessage() throws InterruptedException {
        return queue.take();
    }
}
