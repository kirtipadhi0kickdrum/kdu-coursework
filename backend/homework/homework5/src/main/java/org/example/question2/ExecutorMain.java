package org.example.question2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorMain {
    public static void main(String[] a)
    {
        MessageQueue messageQueue = new MessageQueue();


        ExecutorService senderExecutor = Executors.newFixedThreadPool(3);
        senderExecutor.execute(new MessageSender(messageQueue, "Sender1"));
        senderExecutor.execute(new MessageSender(messageQueue, "Sender2"));
        senderExecutor.execute(new MessageSender(messageQueue, "Sender3"));


        ExecutorService receiverExecutor = Executors.newFixedThreadPool(3);
        receiverExecutor.execute(new MessageReceiver(messageQueue, "Receiver1"));
        receiverExecutor.execute(new MessageReceiver(messageQueue, "Receiver2"));
        receiverExecutor.execute(new MessageReceiver(messageQueue, "Receiver3"));
    }

}
