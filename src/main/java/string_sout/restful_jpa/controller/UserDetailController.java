package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.userDetail.UserDetailResponse;
import string_sout.restful_jpa.model.userDetail.CreateUserDetailRequest;
import string_sout.restful_jpa.model.userDetail.UpdateUserDetailRequest;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.service.DetailUserService;
import string_sout.restful_jpa.service.ValidationService;

@RestController
@Slf4j
public class UserDetailController {

    @Autowired
    private DetailUserService detailUserService;

    private ValidationService validationService;

    @PostMapping(
            path = "/api/user/detail",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserDetailResponse> create (User user,
                                                   @RequestBody CreateUserDetailRequest request
                                                  ) {
        log.info("Create address: {}", request);
        UserDetailResponse userDetailResponse = detailUserService.create(user, request);

        return WebResponse.<UserDetailResponse>builder().data(userDetailResponse).build();
    }

    @GetMapping(
            path = "/api/user/detail",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserDetailResponse> get (User user
    ) {
        UserDetailResponse userDetailResponse = detailUserService.get(user);
        return WebResponse.<UserDetailResponse>builder().data(userDetailResponse).build();
    }

    @PutMapping(
            path = "/api/user/detail"
    )
    public WebResponse<UserDetailResponse> update (User user,
                                                   @RequestBody UpdateUserDetailRequest request){
        request.setUserId(user.getId());
        UserDetailResponse update = detailUserService.update(user, request);
        return WebResponse.<UserDetailResponse>builder().data(update).build();
    }

}
