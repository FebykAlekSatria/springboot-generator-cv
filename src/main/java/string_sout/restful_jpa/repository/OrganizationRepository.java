package string_sout.restful_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import string_sout.restful_jpa.entity.Experience;
import string_sout.restful_jpa.entity.Organization;
import string_sout.restful_jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    List<Organization> findAllByUser(User user);

    Optional<Organization> findFirstByUserAndId(User user, String id);

}
