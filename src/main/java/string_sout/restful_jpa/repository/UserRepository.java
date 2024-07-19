package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import string_sout.restful_jpa.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);

    Optional<User> findByUsername(String username);



    boolean existsUserByUsername(String username);
}
