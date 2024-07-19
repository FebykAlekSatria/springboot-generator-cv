package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.model.education.CreateEducationRequest;
import string_sout.restful_jpa.model.education.EducationResponse;
import string_sout.restful_jpa.model.education.UpdateEducationRequest;
import string_sout.restful_jpa.service.EducationService;

import java.util.List;

@Slf4j
@RestController
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping(
            path = "/api/educations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<EducationResponse>> create(User user,
                                                       @RequestBody List<CreateEducationRequest> request) {

        log.info("START create education request : {}", request);
        List<EducationResponse> educationResponse = educationService.create(user, request);
        log.info("END create education request : {}", request);

        return WebResponse.<List<EducationResponse>>builder().data(educationResponse).build();

    }

    @GetMapping(
            path = "/api/educations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<EducationResponse>> get(User user) {
        List<EducationResponse> allSocial = educationService.getAllEducation(user);
        return WebResponse.<List<EducationResponse>>builder().data(allSocial).build();
    }

    @PutMapping(
            path = "/api/educations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<EducationResponse>> update(
            User user,
            @RequestBody List<UpdateEducationRequest> request
    ) {
        List<EducationResponse> allSocial = educationService.update(user, request);
        return WebResponse.<List<EducationResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/educations/{educationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("educationId") String educationId
    ) {
         educationService.delete(user, educationId);
        return WebResponse.<String>builder().data("Success delete skill").build();
    }

}
