package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.model.experience.CreateExperinceRequest;
import string_sout.restful_jpa.model.experience.ExperienceResponse;
import string_sout.restful_jpa.model.experience.UpdateExperienceRequest;
import string_sout.restful_jpa.service.ExperienceService;

import java.util.List;

@Slf4j
@RestController
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @PostMapping(
            path = "/api/experiences",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ExperienceResponse>> create(User user,
                                                        @RequestBody List<CreateExperinceRequest> request) {

        log.info("START create experience request : {}", request);
        List<ExperienceResponse> experienceResponse = experienceService.create(user, request);
        log.info("END create experience request : {}", request);

        return WebResponse.<List<ExperienceResponse>>builder().data(experienceResponse).build();

    }

    @GetMapping(
            path = "/api/experiences",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ExperienceResponse>> get(User user) {
        List<ExperienceResponse> allSocial = experienceService.getAllExperience(user);
        return WebResponse.<List<ExperienceResponse>>builder().data(allSocial).build();
    }

    @PutMapping(
            path = "/api/experiences",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ExperienceResponse>> update(
            User user,
            @RequestBody List<UpdateExperienceRequest> request
    ) {
        List<ExperienceResponse> allSocial = experienceService.update(user, request);
        return WebResponse.<List<ExperienceResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/experiences/{experienceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("experienceId") String experienceId
    ) {
         experienceService.delete(user, experienceId);
        return WebResponse.<String>builder().data("Success delete").build();
    }

}
