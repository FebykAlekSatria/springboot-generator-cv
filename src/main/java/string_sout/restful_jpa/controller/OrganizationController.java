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
import string_sout.restful_jpa.model.organization.CreateOrganizationRequest;
import string_sout.restful_jpa.model.organization.OrganizationResponse;
import string_sout.restful_jpa.model.organization.UpdateOrganizationRequest;
import string_sout.restful_jpa.service.OrganizationService;

import java.util.List;

@Slf4j
@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping(
            path = "/api/organizations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrganizationResponse>> create(User user,
                                                          @RequestBody List<CreateOrganizationRequest> request) {

        log.info("START create organizations request : {}", request);
        List<OrganizationResponse> organizationsResponse = organizationService.create(user, request);
        log.info("END create organizations request : {}", request);

        return WebResponse.<List<OrganizationResponse>>builder().data(organizationsResponse).build();

    }

    @GetMapping(
            path = "/api/organizations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrganizationResponse>> get(User user) {
        List<OrganizationResponse> allOrganization = organizationService.getAllExperience(user);
        return WebResponse.<List<OrganizationResponse>>builder().data(allOrganization).build();
    }

    @PutMapping(
            path = "/api/organizations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrganizationResponse>> update(
            User user,
            @RequestBody List<UpdateOrganizationRequest> request
    ) {
        List<OrganizationResponse> allSocial = organizationService.update(user, request);
        return WebResponse.<List<OrganizationResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/organizations/{experienceId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("experienceId") String experienceId
    ) {
         organizationService.delete(user, experienceId);
        return WebResponse.<String>builder().data("Success delete").build();
    }

}
