package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.*;
import string_sout.restful_jpa.model.social.CreateSocialRequest;
import string_sout.restful_jpa.model.social.SocialResponse;
import string_sout.restful_jpa.model.social.UpdateSocialRequest;
import string_sout.restful_jpa.service.SocialService;

import java.util.List;

@Slf4j
@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;

    @PostMapping(
            path = "/api/socials",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SocialResponse>> create(User user,
                                                    @RequestBody List<CreateSocialRequest> request) {

        log.info("START create social request : {}", request);
        List<SocialResponse> socialResponses = socialService.create(user, request);
        log.info("END create social request : {}", request);

        return WebResponse.<List<SocialResponse>>builder().data(socialResponses).build();

    }

    @GetMapping(
            path = "/api/socials",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SocialResponse>> get(User user) {
        List<SocialResponse> allSocial = socialService.getAllSocial(user);
        return WebResponse.<List<SocialResponse>>builder().data(allSocial).build();
    }

    @PutMapping(
            path = "/api/socials",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<SocialResponse>> update(
            User user,
            @RequestBody List<UpdateSocialRequest> request
    ) {
        List<SocialResponse> allSocial = socialService.update(user, request);
        return WebResponse.<List<SocialResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/socials/{socialId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("socialId") String socialId
    ) {
         socialService.delete(user, socialId);
        return WebResponse.<String>builder().data("Success delete social").build();
    }

}
