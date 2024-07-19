package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Language;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {

    List<Language> findAllByUser(User user);

    Optional<Language> findFirstByUserAndId(User user, String id);

}
