package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Experience;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long>, JpaSpecificationExecutor<Experience> {

    List<Experience> findAllByUser(User user);

    Optional<Experience> findFirstByUserAndId(User user, String id);

}
