package com.electrogrid.payment.utils;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.*;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); //set rabbitmq host address

        try (
        	Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) 
        {
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null); //Declare rpc_queue 
            channel.queuePurge(RPC_QUEUE_NAME); //Delete all the contents of that queue

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Object monitor = new Object();
            
            //Callback which executes when messages get delivered
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";

                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    int n = Integer.parseInt(message);

                    System.out.println(" [.] fib(" + message + ")");
                    response += fib(n);
                } catch (RuntimeException e) {
                    System.out.println(" [.] " + e.toString());
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
//                    synchronized (monitor) {
//                        monitor.notify();
//                    }
                }
            };
            //end of callback

            channel.basicConsume(RPC_QUEUE_NAME, true, deliverCallback, (consumerTag -> { })); //consume messages from the rpc_queue
            
            
            // Wait and be prepared to consume the message from RPC client.
//            while (true) {
//                synchronized (monitor) {
//                    try {
//                        monitor.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            while(true) {
            	TimeUnit.SECONDS.sleep(3);
            	System.out.println("Tick");
            }
            
            //System.out.println("After sync loop");
        }
    }
}