package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Skill;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.skill.CreateSkillRequest;
import string_sout.restful_jpa.model.skill.UpdateSkillRequest;
import string_sout.restful_jpa.model.skill.SkillResponse;
import string_sout.restful_jpa.repository.SkillRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ValidationService validationService;

    private SkillResponse toResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .skill(skill.getSkill())
                .level(skill.getLevel())
                .build();
    }

    @Transactional
    public List<SkillResponse> create(User user, List<CreateSkillRequest> request) {
        log.info("START looping Save Skill");
        for (CreateSkillRequest requestItem : request) {
            log.info("START Validation: {}", requestItem);
            validationService.validate(requestItem);
            log.info("END Validation: {}", requestItem);

            log.info("START Save: {}", requestItem);

            Skill skill = new Skill();
            skill.setId(UUID.randomUUID().toString());
            skill.setSkill(requestItem.getSkill());
            skill.setLevel(requestItem.getLevel());
            skill.setUser(user);
            skillRepository.save(skill);
            log.info("END Save: {}", skill);
        }

        List<Skill> allByUser = skillRepository.findAllByUser(user);
        return allByUser.stream().map(this::toResponse).toList();
    }

//    public SkillResponse get(User user, String id) {
//        Skill skill = skillRepository.findFirstByUserAndId(user, id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
//        return toResponse(skill);
//    }

@Transactional
    public List<SkillResponse> update(User user, List<UpdateSkillRequest> request) {
        for (UpdateSkillRequest update : request) {
            validationService.validate(update);
            Skill skill = skillRepository.findFirstByUserAndId(user, update.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));

            skill.setSkill(update.getSkill());
            skill.setLevel(update.getLevel());
            skillRepository.save(skill);
        }
        List<Skill> allByUser = skillRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }

    @Transactional
    public void delete(User user, String id) {
        Skill skill = skillRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));

        skillRepository.delete(skill);
    }

    public List<SkillResponse> getAllSkill(User user) {

        List<Skill> allByUser = skillRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }


}
