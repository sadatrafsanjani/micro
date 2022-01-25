package microservices.book.multiplication.client;

import microservices.book.multiplication.dto.ChallengeSolvedDTO;
import microservices.book.multiplication.model.Attempt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GamificationClient {

    private final RestTemplate restTemplate;
    private final String gamificationHostUrl;

    public GamificationClient(final RestTemplateBuilder builder,
                                     @Value("${service.gamification.host}") final String gamificationHostUrl) {
        restTemplate = builder.build();
        this.gamificationHostUrl = gamificationHostUrl;
    }

    public HttpStatus sendAttempt(final Attempt attempt) {
        try {
            ChallengeSolvedDTO dto = new ChallengeSolvedDTO(
                    attempt.getId(),
                    attempt.isCorrect(), attempt.getFactorA(),
                    attempt.getFactorB(), attempt.getUser().getId(),
                    attempt.getUser().getAlias());

            ResponseEntity<String> r = restTemplate.postForEntity(gamificationHostUrl + "/games", dto, String.class);
            log.info("Gamification service response: {}", r.getStatusCode());

            return r.getStatusCode();
        } catch (Exception e) {
            log.error("There was a problem sending the attempt.", e);
        }
        return null;
    }
}