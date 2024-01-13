package org.example.question1;

import java.util.logging.Logger;

public class MessageSender implements Runnable{
    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
    private final MessageQueue messageQueue;
    private final String senderName;

    public MessageSender(MessageQueue messageQueue, String senderName)
    {
        this.messageQueue = messageQueue;
        this.senderName = senderName;
    }

    @Override
    public void run(){
        for(int i=1;i<=5;i++)
        {
            String message = senderName + " Message " + i;
            messageQueue.addMessage(message);
            logger.info(String.format("Sent: %s", message));
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

}
