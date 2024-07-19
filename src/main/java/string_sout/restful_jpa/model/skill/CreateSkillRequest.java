package string_sout.restful_jpa.model.skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSkillRequest {

    @NotBlank
    @Size(max = 100)
    private String skill;

    @NotBlank
    @Size(max = 100)
    private String level;
}
