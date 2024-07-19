package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.model.achievement.AchievementResponse;
import string_sout.restful_jpa.model.achievement.CreateAchievementRequest;
import string_sout.restful_jpa.model.achievement.UpdateAchievementRequest;
import string_sout.restful_jpa.service.AchievementService;

import java.util.List;

@Slf4j
@RestController
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @PostMapping(
            path = "/api/achieves",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AchievementResponse>> create(User user,
                                                         @RequestBody List<CreateAchievementRequest> request) {

        log.info("START create skill request : {}", request);
        List<AchievementResponse> achieves = achievementService.create(user, request);
        log.info("END create skill request : {}", request);

        return WebResponse.<List<AchievementResponse>>builder().data(achieves).build();

    }

    @GetMapping(
            path = "/api/achieves",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AchievementResponse>> get(User user) {
        List<AchievementResponse> achieves = achievementService.getAllAchievement(user);
        return WebResponse.<List<AchievementResponse>>builder().data(achieves).build();
    }

    @PutMapping(
            path = "/api/achieves",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AchievementResponse>> update(
            User user,
            @RequestBody List<UpdateAchievementRequest> request
    ) {
        List<AchievementResponse> achieves = achievementService.update(user, request);
        return WebResponse.<List<AchievementResponse>>builder().data(achieves).build();
    }

    @DeleteMapping(
            path = "/api/achieves/{achieveId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("achieveId") String achieveId
    ) {
         achievementService.delete(user, achieveId);
        return WebResponse.<String>builder().data("Success delete Achieve").build();
    }

}
