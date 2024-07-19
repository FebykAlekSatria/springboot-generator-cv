package string_sout.restful_jpa.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.user.RegisterUserRequest;
import string_sout.restful_jpa.model.user.UpdateUserRequest;
import string_sout.restful_jpa.model.user.UserResponse;
import string_sout.restful_jpa.model.WebResponse;
import string_sout.restful_jpa.service.UserService;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        log.info("Start Registering user: {}", request);
        userService.register(request);
        log.info("Finish Registering user: {}", request);
        return WebResponse.<String>builder().data("Success").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(User user){
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();

    }

    @PatchMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(User user , @RequestBody UpdateUserRequest request) {
        log.info("start service Updating request: {}", request);
        log.info("start service Updating user: {}", user.getUsername());

        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
