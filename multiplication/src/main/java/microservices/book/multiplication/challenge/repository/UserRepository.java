package microservices.book.multiplication.challenge.repository;

import microservices.book.multiplication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAlias(final String alias);;
}
