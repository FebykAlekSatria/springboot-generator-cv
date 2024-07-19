package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.user.RegisterUserRequest;
import string_sout.restful_jpa.model.user.UpdateUserRequest;
import string_sout.restful_jpa.model.user.UserResponse;
import string_sout.restful_jpa.repository.UserRepository;
import string_sout.restful_jpa.security.BCrypt;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request){
        log.info("Start validation request: {}", request.getUsername());
        validationService.validate(request);
        log.info("finish validation request: {}", request.getUsername());

        log.info("Start cek exist request: {}", request.getUsername());

        if(userRepository.existsUserByUsername(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        log.info("Finish cek exist request: {}", request.getUsername());

        log.info("Start Save request: {}", request.getUsername());

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setEmail(request.getEmail());
        userRepository.save(user);

        log.info("Finish save request: {}", request.getUsername());
    }

    public UserResponse get(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request){
        log.info("start validation user {}", user.getUsername());
        log.info("request request {}", request);
        validationService.validate(request);

        if(Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }

        if(Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        log.info("Validation Success");

        userRepository.save(user);
        log.info("Finish save user {}", user.getUsername());

        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
