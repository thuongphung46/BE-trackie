package vn.kma.hrmactvn.controller.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "require_update_profile")
    private Boolean requireUpdateProfile;


}
