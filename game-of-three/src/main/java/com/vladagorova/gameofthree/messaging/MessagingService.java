package com.vladagorova.gameofthree.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private RabbitTemplate rabbitTemplate;

    public MessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sentMessageToPlayer(String queueName, Object value) {
        rabbitTemplate.convertAndSend(queueName, value);
    }

}
