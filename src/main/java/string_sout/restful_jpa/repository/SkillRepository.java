package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Skill;
import string_sout.restful_jpa.entity.Social;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill> {

    List<Skill> findAllByUser(User user);

    Optional<Skill> findFirstByUserAndId(User user, String id);

}
