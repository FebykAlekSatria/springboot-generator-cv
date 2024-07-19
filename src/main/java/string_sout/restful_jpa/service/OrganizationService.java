 package string_sout.restful_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import string_sout.restful_jpa.entity.Experience;
import string_sout.restful_jpa.entity.Organization;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.experience.CreateExperinceRequest;
import string_sout.restful_jpa.model.experience.ExperienceResponse;
import string_sout.restful_jpa.model.experience.UpdateExperienceRequest;
import string_sout.restful_jpa.model.organization.CreateOrganizationRequest;
import string_sout.restful_jpa.model.organization.OrganizationResponse;
import string_sout.restful_jpa.model.organization.UpdateOrganizationRequest;
import string_sout.restful_jpa.repository.OrganizationRepository;

import java.util.List;
import java.util.UUID;

 @Slf4j
 @Service
 public class OrganizationService {

     @Autowired
     private OrganizationRepository organizationRepository;

     @Autowired
     private ValidationService validationService;

     private OrganizationResponse toResponse(Organization experience) {
         return OrganizationResponse.builder()
                 .id(experience.getId())
                 .organization(experience.getOrganization())
                 .startDate(experience.getStartDate())
                 .endDate(experience.getEndDate())
                 .title(experience.getTitle())
                 .desc(experience.getDesc())
                 .build();
     }

     @Transactional
     public List<OrganizationResponse> create(User user, List<CreateOrganizationRequest> request) {
         log.info("START looping Save experiences");
         for (CreateOrganizationRequest requestItem : request) {
             log.info("START Validation: {}", requestItem);
             validationService.validate(requestItem);
             log.info("END Validation: {}", requestItem);

             log.info("START Save: {}", requestItem);

             Organization organization = new Organization();
             organization.setId(UUID.randomUUID().toString());
             organization.setOrganization(requestItem.getOrganization());
             organization.setStartDate(requestItem.getStartDate());
             organization.setEndDate(requestItem.getEndDate());
             organization.setTitle(requestItem.getTitle());
             organization.setDesc(requestItem.getDesc());
             organization.setUser(user);
             organizationRepository.save(organization);
             log.info("END Save: {}", organization);
         }

         List<Organization> allByUser = organizationRepository.findAllByUser(user);
         return allByUser.stream().map(this::toResponse).toList();
     }

 //    public UpdateOrganizationResponse get(User user, String id) {
 //        Skill skill = skillRepository.findFirstByUserAndId(user, id)
 //                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found"));
 //        return toResponse(skill);
 //    }

     @Transactional
     public List<OrganizationResponse> update(User user, List<UpdateOrganizationRequest> request) {
         for (UpdateOrganizationRequest requestItem : request) {
             validationService.validate(requestItem);
             Organization organization = organizationRepository.findFirstByUserAndId(user, requestItem.getId())
                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));

             organization.setOrganization(requestItem.getOrganization());
             organization.setStartDate(requestItem.getStartDate());
             organization.setEndDate(requestItem.getEndDate());
             organization.setTitle(requestItem.getTitle());
             organization.setDesc(requestItem.getDesc());
             organizationRepository.save(organization);
         }
         List<Organization> allByUser = organizationRepository.findAllByUser(user);

         return allByUser.stream().map(this::toResponse).toList();
     }

     @Transactional
     public void delete(User user, String id) {
         Organization organization = organizationRepository.findFirstByUserAndId(user, id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found"));

         organizationRepository.delete(organization);
     }

     public List<OrganizationResponse> getAllExperience(User user) {

         List<Organization> allByUser = organizationRepository.findAllByUser(user);

         return allByUser.stream().map(this::toResponse).toList();
     }
 }
