package com.example.rabbitmq.producer;


import com.example.rabbitmq.config.MessageConfig;
import com.example.rabbitmq.dto.Order;
import com.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{hotel}")
    public String addDetails(@RequestBody Order order, @PathVariable String hotel){
        OrderStatus orderStatus = new OrderStatus(order,"progress","order placed at " + hotel);
        if(hotel.equals("sasi")){
            template.convertAndSend(MessageConfig.DIRECT_EXCHANGE,MessageConfig.ROUTING_KEY1,orderStatus);
        }
        else{
            template.convertAndSend(MessageConfig.DIRECT_EXCHANGE,MessageConfig.ROUTING_KEY2,orderStatus);
        }
        return "success";
    }

}
