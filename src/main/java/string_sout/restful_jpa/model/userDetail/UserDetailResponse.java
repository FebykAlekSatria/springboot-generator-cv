package string_sout.restful_jpa.model.userDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailResponse {

    private String firstName;

    private String lastName;

    private String city;

    private String country;

    private String about;

    private String phone;
}
