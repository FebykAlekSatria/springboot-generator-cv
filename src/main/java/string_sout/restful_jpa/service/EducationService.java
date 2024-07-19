 package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Education;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.education.CreateEducationRequest;
import string_sout.restful_jpa.model.education.EducationResponse;
import string_sout.restful_jpa.model.education.UpdateEducationRequest;
import string_sout.restful_jpa.repository.EducationRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ValidationService validationService;

    private EducationResponse toResponse(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .school(education.getSchool())
                .major(education.getMajor())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .gpa(education.getGpa())
                .desc(education.getDesc())
                .build();
    }

    @Transactional
    public List<EducationResponse> create(User user, List<CreateEducationRequest> request) {
        log.info("START looping save education");
        for (CreateEducationRequest requestItem : request) {
            log.info("START Validation: {}", requestItem);
            validationService.validate(requestItem);
            log.info("END Validation: {}", requestItem);

            log.info("START Save: {}", requestItem);

            Education education = new Education();
            education.setId(UUID.randomUUID().toString());
            education.setSchool(requestItem.getSchool());
            education.setMajor(requestItem.getMajor());
            education.setStartDate(requestItem.getStartDate());
            education.setEndDate(requestItem.getEndDate());
            education.setGpa(requestItem.getGpa());
            education.setDesc(requestItem.getDesc());
            education.setUser(user);
            educationRepository.save(education);
            log.info("END Save: {}", education);
        }

        List<Education> allByUser = educationRepository.findAllByUser(user);
        return allByUser.stream().map(this::toResponse).toList();
    }

//    public EducationResponse get(User user, String id) {
//        Skill skill = skillRepository.findFirstByUserAndId(user, id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
//        return toResponse(skill);
//    }

@Transactional
    public List<EducationResponse> update(User user, List<UpdateEducationRequest> request) {
        for (UpdateEducationRequest update : request) {
            validationService.validate(update);
            Education education = educationRepository.findFirstByUserAndId(user, update.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found"));

            education.setSchool(update.getSchool());
            education.setMajor(update.getMajor());
            education.setStartDate(update.getStartDate());
            education.setEndDate(update.getEndDate());
            education.setGpa(update.getGpa());
            education.setDesc(update.getDesc());
            educationRepository.save(education);
        }
        List<Education> allByUser = educationRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }

    @Transactional
    public void delete(User user, String id) {
        Education education = educationRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found"));

        educationRepository.delete(education);
    }

    public List<EducationResponse> getAllEducation(User user) {

        List<Education> allByUser = educationRepository.findAllByUser(user);

        return allByUser.stream().map(this::toResponse).toList();
    }
}
