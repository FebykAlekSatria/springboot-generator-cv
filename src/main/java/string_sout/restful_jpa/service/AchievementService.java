package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Achievement;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.achievement.AchievementResponse;
import string_sout.restful_jpa.model.achievement.CreateAchievementRequest;
import string_sout.restful_jpa.model.achievement.UpdateAchievementRequest;
import string_sout.restful_jpa.repository.AchievementRepository;


import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private ValidationService validationService;

    private AchievementResponse toResponse(Achievement skill) {
        return AchievementResponse.builder()
                .id(skill.getId())
                .achieve(skill.getAchieve())
                .year(skill.getYear())
                .build();
    }

    @Transactional
    public List<AchievementResponse> create(User user, List<CreateAchievementRequest> request) {
        log.info("START looping Save Achievement");
        for (CreateAchievementRequest requestItem : request) {
            log.info("START Validation: {}", requestItem);
            validationService.validate(requestItem);
            log.info("END Validation: {}", requestItem);

            log.info("START Save: {}", requestItem);

            Achievement achievement = new Achievement();
            achievement.setId(UUID.randomUUID().toString());
            achievement.setAchieve(requestItem.getAchieve());
            achievement.setYear(requestItem.getYear());
            achievement.setUser(user);
            achievementRepository.save(achievement);
            log.info("END Save: {}", achievement);
        }

        List<Achievement> allByUser = achievementRepository.findAllByUser(user);
        return allByUser.stream().map(this::toResponse).toList();
    }

//    public AchievementResponse get(User user, String id) {
//        Achievement skill = skillRepository.findFirstByUserAndId(user, id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found"));
//        return toResponse(skill);
//    }

@Transactional
    public List<AchievementResponse> update(User user, List<UpdateAchievementRequest> request) {
        for (UpdateAchievementRequest update : request) {
            validationService.validate(update);
            Achievement skill = achievementRepository.findFirstByUserAndId(user, update.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found"));

            skill.setAchieve(update.getAchieve());
            skill.setYear(update.getYear());
            achievementRepository.save(skill);
        }
        List<Achievement> allByUser = achievementRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }

    @Transactional
    public void delete(User user, String id) {
        Achievement skill = achievementRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found"));

        achievementRepository.delete(skill);
    }

    public List<AchievementResponse> getAllAchievement(User user) {

        List<Achievement> allByUser = achievementRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }


}
