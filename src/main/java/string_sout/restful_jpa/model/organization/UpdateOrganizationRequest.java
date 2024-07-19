package string_sout.restful_jpa.model.organization;

import jakarta.persistence.Id;
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
public class UpdateOrganizationRequest {

    @Id
    @NotBlank
    @Size(max = 100)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String organization;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private Date startDate;

    private Date endDate;

    @NotBlank
    @Size(max = 500)
    private String desc;
}
