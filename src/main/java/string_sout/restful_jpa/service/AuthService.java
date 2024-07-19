package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.user.LoginUserRequest;
import string_sout.restful_jpa.model.TokenResponse;
import string_sout.restful_jpa.repository.UserRepository;
import string_sout.restful_jpa.security.BCrypt;

import java.util.UUID;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    public TokenResponse login(LoginUserRequest request){
        log.info("START validation request: {}", request);
        validationService.validate(request);
        log.info("FINISH validation request: {}", request);

        log.info("START find username request: {}", request);
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong"));
        log.info("START find username request: {}", request);

        log.info("START cek password request: {}", request);
        if(BCrypt.checkpw((request.getPassword()), user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);
            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt((user.getTokenExpiredAt()))
                    .build();
        } else {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
//        return "Success logout";
    }

    private Long next30Days(){
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }
}
