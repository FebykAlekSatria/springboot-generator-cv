package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Language;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.language.CreateLanguageRequest;
import string_sout.restful_jpa.model.language.LanguageResponse;
import string_sout.restful_jpa.model.language.UpdateLanguageRequest;
import string_sout.restful_jpa.repository.LanguageRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ValidationService validationService;

    private LanguageResponse toResponse(Language language) {
        return LanguageResponse.builder()
                .id(language.getId())
                .language(language.getLanguage())
                .level(language.getLevel())
                .build();
    }

    @Transactional
    public List<LanguageResponse> create(User user, List<CreateLanguageRequest> request) {
        log.info("START looping Save language");
        for (CreateLanguageRequest requestItem : request) {
            log.info("START Validation: {}", requestItem);
            validationService.validate(requestItem);
            log.info("END Validation: {}", requestItem);

            log.info("START Save: {}", requestItem);

            Language language = new Language();
            language.setId(UUID.randomUUID().toString());
            language.setLanguage(requestItem.getLanguage());
            language.setLevel(requestItem.getLevel());
            language.setUser(user);
            languageRepository.save(language);
            log.info("END Save: {}", language);
        }

        List<Language> allByUser = languageRepository.findAllByUser(user);
        return allByUser.stream().map(this::toResponse).toList();
    }

//    public LanguageResponse get(User user, String id) {
//        Language language = languageRepository.findFirstByUserAndId(user, id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));
//        return toResponse(language);
//    }

@Transactional
    public List<LanguageResponse> update(User user, List<UpdateLanguageRequest> request) {
        for (UpdateLanguageRequest update : request) {
            validationService.validate(update);
            Language language = languageRepository.findFirstByUserAndId(user, update.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

            language.setLanguage(update.getLanguage());
            language.setLevel(update.getLevel());
            languageRepository.save(language);
        }
        List<Language> allByUser = languageRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }

    @Transactional
    public void delete(User user, String id) {
        Language language = languageRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        languageRepository.delete(language);
    }

    public List<LanguageResponse> getAllLanguage(User user) {

        List<Language> allByUser = languageRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }


}
