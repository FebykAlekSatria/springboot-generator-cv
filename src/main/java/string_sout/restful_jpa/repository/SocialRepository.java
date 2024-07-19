package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Social;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Long>, JpaSpecificationExecutor<Social> {

    Optional<Social> findFirstByUserAndId(User user, String id);

    List<Social> findAllByUser(User user);

}
