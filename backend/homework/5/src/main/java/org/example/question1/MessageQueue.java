package org.example.question1;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private final Queue<String> queue = new LinkedList<>();

    public synchronized void addMessage(String message)
    {
        queue.add(message);
        notifyAll();
    }

    public synchronized String getMessage() throws InterruptedException
    {
        while(queue.isEmpty())
        {
            wait();
        }
        return queue.poll();
    }


}
