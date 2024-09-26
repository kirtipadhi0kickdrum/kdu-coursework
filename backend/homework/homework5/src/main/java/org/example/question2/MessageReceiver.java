package org.example.question2;

import java.util.logging.Logger;

public class MessageReceiver implements Runnable{
    private static final Logger logger = Logger.getLogger(MessageReceiver.class.getName());
    private final MessageQueue messageQueue;
    private final String receiverName;
    private volatile boolean isRunning = true;

    public MessageReceiver(MessageQueue messageQueue, String receiverName) {
        this.messageQueue = messageQueue;
        this.receiverName = receiverName;
    }

    public void stop(){
        isRunning = false;
    }
    @Override
    public void run() {
        while (isRunning) {
            try {
                String message = messageQueue.getMessage();
                logger.info(receiverName + " Received: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
