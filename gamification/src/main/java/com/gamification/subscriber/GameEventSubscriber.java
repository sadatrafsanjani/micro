package com.gamification.subscriber;

import com.challenge.ChallengeSolvedEvent;
import com.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameEventSubscriber {

    private final GameService gameService;

    @RabbitListener(queues = "${amqp.queue.gamification}")
    void handleMultiplicationSolved(final ChallengeSolvedEvent event) {

        log.info("Challenge Solved Event received: {}", event.getAttemptId());

        try {
            gameService.newAttemptForUser(event);
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
