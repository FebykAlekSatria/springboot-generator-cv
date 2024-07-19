package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.model.skill.CreateSkillRequest;
import string_sout.restful_jpa.model.skill.SkillResponse;
import string_sout.restful_jpa.model.skill.UpdateSkillRequest;
import string_sout.restful_jpa.service.SkillService;

import java.util.List;

@Slf4j
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping(
            path = "/api/skills",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SkillResponse>> create(User user,
                                                   @RequestBody List<CreateSkillRequest> request) {

        log.info("START create skill request : {}", request);
        List<SkillResponse> skillRespons = skillService.create(user, request);
        log.info("END create skill request : {}", request);

        return WebResponse.<List<SkillResponse>>builder().data(skillRespons).build();

    }

    @GetMapping(
            path = "/api/skills",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SkillResponse>> get(User user) {
        List<SkillResponse> allSocial = skillService.getAllSkill(user);
        return WebResponse.<List<SkillResponse>>builder().data(allSocial).build();
    }

    @PutMapping(
            path = "/api/skills",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SkillResponse>> update(
            User user,
            @RequestBody List<UpdateSkillRequest> request
    ) {
        List<SkillResponse> allSocial = skillService.update(user, request);
        return WebResponse.<List<SkillResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/skills/{skillId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("skillId") String skillId
    ) {
         skillService.delete(user, skillId);
        return WebResponse.<String>builder().data("Success delete skill").build();
    }

}
