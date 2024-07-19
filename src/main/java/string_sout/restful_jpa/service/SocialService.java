package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Social;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.social.CreateSocialRequest;
import string_sout.restful_jpa.model.social.SocialResponse;
import string_sout.restful_jpa.model.social.UpdateSocialRequest;
import string_sout.restful_jpa.repository.SocialRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SocialService {

    @Autowired
    private SocialRepository socialRepository;

    @Autowired
    private ValidationService validationService;

    private SocialResponse toResponse(Social social) {
        return SocialResponse.builder()
                .id(social.getId())
                .platform(social.getPlatform())
                .username(social.getUsername())
                .url(social.getUrl())
                .build();
    }

    @Transactional
    public List<SocialResponse> create(User user, List<CreateSocialRequest> request) {
        log.info("START looping Save Social");
        for (CreateSocialRequest requestItem : request) {
            log.info("START Validation: {}", requestItem);
            validationService.validate(requestItem);
            log.info("END Validation: {}", requestItem);

            log.info("START Save: {}", requestItem);

            Social social = new Social();
            social.setId(UUID.randomUUID().toString());
            social.setPlatform(requestItem.getPlatform());
            social.setUsername(requestItem.getUsername());
            social.setUrl(requestItem.getUrl());
            social.setUser(user);
            socialRepository.save(social);
            log.info("END Save: {}", social);
        }

        List<Social> allByUser = socialRepository.findAllByUser(user);
        return allByUser.stream().map(this::toResponse).toList();
    }

//    public SocialResponse get(User user, String id) {
//        Social social = socialRepository.findFirstByUserAndId(user, id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Social not found"));
//        return toResponse(social);
//    }

    public List<SocialResponse> update(User user, List<UpdateSocialRequest> request) {
        for (UpdateSocialRequest update : request) {
            validationService.validate(update);
            Social social = socialRepository.findFirstByUserAndId(user, update.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Social not found"));

            social.setPlatform(update.getPlatform());
            social.setUrl(update.getUrl());
            social.setUsername(update.getUsername());
            socialRepository.save(social);
        }
        List<Social> allByUser = socialRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }

    @Transactional
    public void delete(User user, String id) {
        Social social = socialRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Social not found"));

        socialRepository.delete(social);
    }

    public List<SocialResponse> getAllSocial(User user) {

        List<Social> allByUser = socialRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }


}
