package org.example.question1;

public class MessageMain {
    public static void main(String[] a)
    {
        MessageQueue messageQueue = new MessageQueue();

        Thread sender1 = new Thread(new MessageSender(messageQueue, "Sender1"));
        Thread sender2 = new Thread(new MessageSender(messageQueue, "Sender2"));
        Thread sender3 = new Thread(new MessageSender(messageQueue, "Sender3"));
        sender1.start();
        sender2.start();
        sender3.start();

        Thread receiver1 = new Thread(new MessageReceiver(messageQueue, "Receiver1"));
        Thread receiver2 = new Thread(new MessageReceiver(messageQueue, "Receiver2"));
        Thread receiver3 = new Thread(new MessageReceiver(messageQueue, "Receiver3"));
        receiver1.start();
        receiver2.start();
        receiver3.start();



    }
}
