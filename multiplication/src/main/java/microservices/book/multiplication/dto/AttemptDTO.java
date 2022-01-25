package microservices.book.multiplication.dto;

import lombok.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AttemptDTO {

    @Min(11)
    @Max(99)
    private int factorA, factorB;
    private String userAlias;
    @Positive(message = "How could you possibly get a negative result here? Try again.")
    private int guess;
}