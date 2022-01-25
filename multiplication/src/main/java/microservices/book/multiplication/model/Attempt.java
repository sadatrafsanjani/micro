package microservices.book.multiplication.model;

import lombok.*;

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

    @Column(name = "FACTOR_A")
    private int factorA;

    @Column(name = "FACTOR_B")
    private int factorB;

    @Column(name = "RESULT_ATTEMPT")
    private int resultAttempt;

    @Column(name = "CORRECT")
    private boolean correct;
}
