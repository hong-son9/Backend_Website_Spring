package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("fullname")
    private String fullName;
    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Password cannot be blank")
    private String password;
    @JsonProperty("retype_password")
    private String retypePassword;
    @JsonProperty("date_od_birth")
    private Data dateOfBirth;
    @JsonProperty("facebook_account_id")
    private int facebookAccountId;
    @JsonProperty("google_account-id")
    private int googleAccountId;

    @NotNull(message = "Role Id is required")
    @JsonProperty("role_id")
    private long roleId;

}
