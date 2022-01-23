package microservices.book.multiplication.challenge.model;

import lombok.*;
import microservices.book.multiplication.user.User;
import javax.persistence.*;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;
}
