package com.optica.luminova.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.optica.luminova.validator.UniqueEmail;
import com.optica.luminova.validator.ValidPassword;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
   @NotBlank
   private String nombre;

   @NotBlank
   @Email
   @UniqueEmail
   private String email;

   @NotBlank
   @ValidPassword
   private String password;
}
