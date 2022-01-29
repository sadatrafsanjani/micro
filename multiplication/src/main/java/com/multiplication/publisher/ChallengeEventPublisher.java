package com.multiplication.publisher;

import com.challenge.ChallengeSolvedEvent;
import com.multiplication.model.Attempt;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChallengeEventPublisher {

    private final AmqpTemplate amqpTemplate;
    private final String challengesTopicExchange;

    public ChallengeEventPublisher(final AmqpTemplate amqpTemplate, @Value("${amqp.exchange.attempts}") final String challengesTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    public void publishChallenge(final Attempt attempt) {

        ChallengeSolvedEvent event = ChallengeSolvedEvent.builder()
                .attemptId(attempt.getId())
                .correct(attempt.isCorrect())
                .factorA(attempt.getFactorA())
                .factorB(attempt.getFactorB())
                .userAlias(attempt.getUser().getAlias())
                .userId(attempt.getUser().getId())
                .build();
        // Routing Key is 'attempt.correct' or 'attempt.wrong'
        String routingKey = "attempt." + (event.isCorrect() ? "correct" : "wrong");
        amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
    }
}
