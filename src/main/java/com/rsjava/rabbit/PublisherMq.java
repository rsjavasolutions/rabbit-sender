package com.rsjava.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherMq {

    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRateString = "PT2S")
    public void sendNumbers() {
        int number = new Random().nextInt() + 1;

        if (isOdd(number)) {
            rabbitTemplate.convertAndSend("odd-numbers", number);
        } else {
            rabbitTemplate.convertAndSend("even-numbers", number);
        }
        log.info(String.valueOf(number));
    }

    private static boolean isOdd(int i) {
        return (i & 1) == 1;
    }
}
