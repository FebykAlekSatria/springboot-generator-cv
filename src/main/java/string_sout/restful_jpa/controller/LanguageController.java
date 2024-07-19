package string_sout.restful_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import string_sout.restful_jpa.entity.User;
import string_sout.restful_jpa.model.WebResponse;

import string_sout.restful_jpa.model.language.CreateLanguageRequest;
import string_sout.restful_jpa.model.language.LanguageResponse;
import string_sout.restful_jpa.model.language.UpdateLanguageRequest;
import string_sout.restful_jpa.service.LanguageService;

import java.util.List;

@Slf4j
@RestController
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping(
            path = "/api/languages",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<LanguageResponse>> create(User user,
                                                      @RequestBody List<CreateLanguageRequest> request) {

        log.info("START create language request : {}", request);
        List<LanguageResponse> languageResponses = languageService.create(user, request);
        log.info("END create language request : {}", request);

        return WebResponse.<List<LanguageResponse>>builder().data(languageResponses).build();

    }

    @GetMapping(
            path = "/api/languages",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<LanguageResponse>> get(User user) {
        List<LanguageResponse> allSocial = languageService.getAllLanguage(user);
        return WebResponse.<List<LanguageResponse>>builder().data(allSocial).build();
    }

    @PutMapping(
            path = "/api/languages",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<LanguageResponse>> update(
            User user,
            @RequestBody List<UpdateLanguageRequest> request
    ) {
        List<LanguageResponse> allSocial = languageService.update(user, request);
        return WebResponse.<List<LanguageResponse>>builder().data(allSocial).build();
    }

    @DeleteMapping(
            path = "/api/languages/{languageId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            User user,
            @PathVariable("languageId") String languageId
    ) {
         languageService.delete(user, languageId);
        return WebResponse.<String>builder().data("Success delete language").build();
    }

//    @GetMapping(
//            path = "/api/language",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<List<LanguageResponse>> search(User user,
//                                                    @RequestParam(name = "name", required = false) String name,
//                                                    @RequestParam(name = "phone", required = false) String phone,
//                                                    @RequestParam(name = "email", required = false) String email,
//                                                    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                                                    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
//        SearchContactRequest request = SearchContactRequest.builder()
//                .page(page)
//                .size(size)
//                .email(email)
//                .name(name)
//                .phone(phone).build();
//
//        Page<LanguageResponse> contactResponses = languageService.search(user, request);
//        return WebResponse.<List<LanguageResponse>>builder()
//                .data(contactResponses.getContent())
//                .paging(PagingResponse.builder()
//                        .currentPage(contactResponses.getNumber())
//                        .totalPages(contactResponses.getTotalPages())
//                        .size(contactResponses.getSize())
//                        .build())
//                .build();
//
//
//    }
}
