package com.vladagorova.playerclient.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class RabbitConfiguration {

    private static final String MOVE_EVENT_QUEUE_NAME = ".event.in";
    private static final String GAME_OVER_QUEUE_NAME = ".game.over";
    private static final String DEAD_QUEUE_NAME = ".event.dead";
    public static final String EXCHANGE_NAME = "game.of.three";
    private static final int MESSAGE_TTL_MINUTES = 20;

    @Bean
    public Queue moveEventQueue(@Value("${spring.application.name}") String applicationName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", TimeUnit.MINUTES.toMillis(MESSAGE_TTL_MINUTES));
        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", applicationName + DEAD_QUEUE_NAME);
        return new Queue(applicationName + MOVE_EVENT_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Queue gameOverQueue(@Value("${spring.application.name}") String applicationName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", TimeUnit.MINUTES.toMillis(MESSAGE_TTL_MINUTES));
        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", applicationName + DEAD_QUEUE_NAME);
        return new Queue(applicationName + GAME_OVER_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Queue deadQueue(@Value("${spring.application.name}") String applicationName) {
        return new Queue(applicationName + DEAD_QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding moveEventQueueBinding(@Value("${spring.application.name}") String applicationName) {
        return BindingBuilder.bind(moveEventQueue(applicationName)).to(exchange()).withQueueName();
    }

    @Bean
    public Binding gameOverQueueBinding(@Value("${spring.application.name}") String applicationName) {
        return BindingBuilder.bind(gameOverQueue(applicationName)).to(exchange()).withQueueName();
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }
}