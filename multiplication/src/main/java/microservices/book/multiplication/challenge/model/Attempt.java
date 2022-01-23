package microservices.book.multiplication.challenge.model;

import lombok.*;
import microservices.book.multiplication.user.User;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Attempt {

    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
