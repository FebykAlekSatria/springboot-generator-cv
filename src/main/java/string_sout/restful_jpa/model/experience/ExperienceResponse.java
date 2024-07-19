package string_sout.restful_jpa.model.experience;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceResponse {

    @Id
    @NotBlank
    @Size(max = 100)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String company;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM")
    private Date endDate;

    @NotBlank
    @Size(max = 500)
    private String desc;
}
