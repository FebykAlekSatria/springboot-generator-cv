 package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Experience;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.experience.CreateExperinceRequest;
import string_sout.restful_jpa.model.experience.ExperienceResponse;
import string_sout.restful_jpa.model.experience.UpdateExperienceRequest;
import string_sout.restful_jpa.repository.ExperienceRepository;

import java.util.List;
import java.util.UUID;

 @Slf4j
 @Service
 public class ExperienceService {

     @Autowired
     private ExperienceRepository experienceRepository;

     @Autowired
     private ValidationService validationService;

     private ExperienceResponse toResponse(Experience experience) {
         return ExperienceResponse.builder()
                 .id(experience.getId())
                 .company(experience.getCompany())
                 .startDate(experience.getStartDate())
                 .endDate(experience.getEndDate())
                 .title(experience.getTitle())
                 .desc(experience.getDesc())
                 .build();
     }

     @Transactional
     public List<ExperienceResponse> create(User user, List<CreateExperinceRequest> request) {
         log.info("START looping Save experiences");
         for (CreateExperinceRequest requestItem : request) {
             log.info("START Validation: {}", requestItem);
             validationService.validate(requestItem);
             log.info("END Validation: {}", requestItem);

             log.info("START Save: {}", requestItem);

             Experience experience = new Experience();
             experience.setId(UUID.randomUUID().toString());
             experience.setCompany(requestItem.getCompany());
             experience.setStartDate(requestItem.getStartDate());
             experience.setEndDate(requestItem.getEndDate());
             experience.setTitle(requestItem.getTitle());
             experience.setDesc(requestItem.getDesc());
             experience.setUser(user);
             experienceRepository.save(experience);
             log.info("END Save: {}", experience);
         }

         List<Experience> allByUser = experienceRepository.findAllByUser(user);
         return allByUser.stream().map(this::toResponse).toList();
     }

 //    public UpdateExperienceResponse get(User user, String id) {
 //        Skill skill = skillRepository.findFirstByUserAndId(user, id)
 //                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
 //        return toResponse(skill);
 //    }

     @Transactional
     public List<ExperienceResponse> update(User user, List<UpdateExperienceRequest> request) {
         for (UpdateExperienceRequest requestItem : request) {
             validationService.validate(requestItem);
             Experience experience = experienceRepository.findFirstByUserAndId(user, requestItem.getId())
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));

             experience.setCompany(requestItem.getCompany());
             experience.setStartDate(requestItem.getStartDate());
             experience.setEndDate(requestItem.getEndDate());
             experience.setTitle(requestItem.getTitle());
             experience.setDesc(requestItem.getDesc());
             experienceRepository.save(experience);
         }
         List<Experience> allByUser = experienceRepository.findAllByUser(user);

         return allByUser.stream().map(this::toResponse).toList();
     }

     @Transactional
     public void delete(User user, String id) {
         Experience experiences = experienceRepository.findFirstByUserAndId(user, id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));

         experienceRepository.delete(experiences);
     }

     public List<ExperienceResponse> getAllExperience(User user) {

         List<Experience> allByUser = experienceRepository.findAllByUser(user);

         return allByUser.stream().map(this::toResponse).toList();
     }
 }
