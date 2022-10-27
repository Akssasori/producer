package com.lucas.producer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    @Value("${rabbitmq.exchange.producer}")
    private String exchangeProducerName;

    @Value("${rabbitmq.route.test}")
    private String routeTestName;

    @Value("${rabbitmq.queue.test}")
    private String queueTestName;

    public String getExchangeProducerName() {
        return exchangeProducerName;
    }

    public String getRouteTestName() {
        return routeTestName;
    }


    @Bean
    public TopicExchange exchangeProducer() {
        return new TopicExchange(getExchangeProducerName());
    }


    @Bean
    public Queue queueTest() {
        return new Queue(queueTestName, true);
    }


    @Bean
    public Binding bindingTest(
            final Queue queueTest,
            final TopicExchange exchange) {
        return BindingBuilder.bind(queueTest)
                .to(exchange)
                .with(getRouteTestName());
    }

}
