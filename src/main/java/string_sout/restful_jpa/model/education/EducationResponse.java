package string_sout.restful_jpa.model.education;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {

    @NotBlank
    @Size(max = 100)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String school;

    @NotBlank
    private String major;

    @NotNull
    private Date startDate;

    private Date endDate;

    private double gpa;

    @NotBlank
    @Size(max = 500)
    private String desc;
}
