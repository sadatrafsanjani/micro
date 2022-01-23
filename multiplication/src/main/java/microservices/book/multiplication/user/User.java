package microservices.book.multiplication.user;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;

    public User(final String userAlias) {
        this(null, userAlias);
    }
}
