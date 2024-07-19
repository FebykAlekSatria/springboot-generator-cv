package string_sout.restful_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.UserDetail;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.userDetail.UserDetailResponse;
import string_sout.restful_jpa.model.userDetail.CreateUserDetailRequest;
import string_sout.restful_jpa.model.userDetail.UpdateUserDetailRequest;
import string_sout.restful_jpa.repository.UserDetailRepository;
import string_sout.restful_jpa.repository.SocialRepository;

import java.util.UUID;

@Service
public class DetailUserService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private SocialRepository socialRepository;

    @Autowired
    private ValidationService validationService;

    public UserDetailResponse create(User user, CreateUserDetailRequest request) {
        validationService.validate(request);

        UserDetail userDetail = new UserDetail();
        userDetail.setId(UUID.randomUUID().toString());
        userDetail.setCity(request.getCity());
        userDetail.setCountry(request.getCountry());
        userDetail.setFirstName(request.getFirstName());
        userDetail.setLastName(request.getLastName());
        userDetail.setAbout(request.getAbout());
        userDetail.setPhone(request.getPhone());
        userDetail.setUser(user);

        userDetailRepository.save(userDetail);

        return toUserDetail(userDetail);
    }

    public UserDetailResponse get(User user) {

        UserDetail userDetail = userDetailRepository.findFirstByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User detail not found"));

        return toUserDetail(userDetail);
    }

    public UserDetailResponse update(User user, UpdateUserDetailRequest request) {
        validationService.validate(request);

        UserDetail userDetail = userDetailRepository.findFirstByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User detail not found"));

        userDetail.setCity(request.getCity());
        userDetail.setCountry(request.getCountry());
        userDetail.setFirstName(request.getFirstName());
        userDetail.setLastName(request.getLastName());
        userDetail.setPhone(request.getPhone());
        userDetail.setAbout(request.getAbout());
        userDetailRepository.save(userDetail);

        return toUserDetail(userDetail);
    }

    public void delete(User user) {
        UserDetail userDetail = userDetailRepository.findFirstByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User detail not found"));

        userDetailRepository.delete(userDetail);
    }

    private UserDetailResponse toUserDetail(UserDetail userDetail) {
        return UserDetailResponse.builder()
                .city(userDetail.getCity())
                .country(userDetail.getCountry())
                .firstName(userDetail.getFirstName())
                .lastName(userDetail.getLastName())
                .about(userDetail.getAbout())
                .phone(userDetail.getPhone())
                .build();
    }
}
