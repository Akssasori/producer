package com.lucas.producer.producers;

import com.lucas.producer.dtos.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.producer}")
    private String exchangeProducerName;

    @Value("${rabbitmq.route.test}")
    private String routeTestName;


    public void sendToTestQueue(
            final UserRequestDTO userRequestDTO) {

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.convertAndSend(
                    exchangeProducerName,
                    routeTestName,
                    userRequestDTO);

    }


}
