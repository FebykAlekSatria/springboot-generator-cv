package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Achievement;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long>, JpaSpecificationExecutor<Achievement> {

    List<Achievement> findAllByUser(User user);

    Optional<Achievement> findFirstByUserAndId(User user, String id);

}
