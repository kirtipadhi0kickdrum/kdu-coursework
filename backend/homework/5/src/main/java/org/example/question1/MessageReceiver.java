package org.example.question1;

import java.util.logging.Logger;

public class MessageReceiver implements Runnable{
    private static final Logger logger = Logger.getLogger(MessageReceiver.class.getName());
    private final String receiverName;
    private final MessageQueue messageQueue;
    private volatile boolean isRunning = true;

    public MessageReceiver(MessageQueue messageQueue, String receiverName)
    {
        this.receiverName = receiverName;
        this.messageQueue = messageQueue;
    }

    public void stop(){
        isRunning = false;
    }

    public void run()
    {
        while (isRunning)
        {
            try{
                String message = messageQueue.getMessage();
                logger.info(receiverName + " Received: " + message);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }


}
