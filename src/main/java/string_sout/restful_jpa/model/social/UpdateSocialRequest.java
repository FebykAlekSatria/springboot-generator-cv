package string_sout.restful_jpa.model.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UpdateSocialRequest {

//    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    private String Id;

    @NotBlank
    @Size(max = 100)
    private String platform;

    @Size(max = 100)
    private String url;

    @NotBlank
    @Size(max = 100)
    private String username;

}
