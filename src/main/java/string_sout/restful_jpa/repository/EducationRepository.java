package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Education;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long>, JpaSpecificationExecutor<Education> {

    List<Education> findAllByUser(User user);

    Optional<Education> findFirstByUserAndId(User user, String id);

}
