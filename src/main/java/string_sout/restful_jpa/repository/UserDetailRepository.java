package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.entity.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

//    Optional<UserDetail> findFirstByUserAndId(User user, String id);

    Optional<UserDetail> findFirstByUser(User user);
}
