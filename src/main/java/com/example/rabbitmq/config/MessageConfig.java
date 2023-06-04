package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    public static final String QUEUE1 = "consumer1queue";
    public static final String ROUTING_KEY1 = "consumer1routingkey";
    public static final String QUEUE2 = "consumer2queue";
    public static final String ROUTING_KEY2 = "consumer2routingkey";

    public static final String DIRECT_EXCHANGE = "exchange";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue queue1(){
        return new Queue(QUEUE1);
    }
    @Bean
    public Queue queue2(){
        return new Queue(QUEUE2);
    }


    @Bean
    public Binding binding1(Queue queue1,DirectExchange exchange){
        return BindingBuilder.bind(queue1).to(exchange).with(ROUTING_KEY1);
    }

    @Bean
    public Binding binding2(Queue queue2,DirectExchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
