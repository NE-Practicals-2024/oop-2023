package com.mugishap.rca.springboot.v1.payload.request;


import com.mugishap.rca.springboot.v1.validators.ValidPassword;
import com.mugishap.rca.springboot.v1.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank
    private String names;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,12}", message = "Your phone is not a valid tel we expect 2507***, or 07*** or 7***")
    private String telephone;

    @Email
    private String email;

    private ERole role;

    @ValidPassword
    private String password;
}
