package com.example.rabbitmq.consumer;

import com.example.rabbitmq.config.MessageConfig;
import com.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE1)
    public void consumeMessageFromQueue1(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue1 : " + orderStatus);
    }

    @RabbitListener(queues = MessageConfig.QUEUE2)
    public void consumeMessageFromQueue2(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue2 : " + orderStatus);
    }

}
