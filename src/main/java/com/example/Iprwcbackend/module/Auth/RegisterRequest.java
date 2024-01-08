package com.example.Iprwcbackend.module.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Voornaam is verplicht")
    private String firstName;
    @NotBlank(message = "Achternaam is verplicht")
    private String lastName;
    @NotBlank(message = "Wachtwoord is verplicht")
    @Size(min = 8, message = "Wachtwoord moet minstens 8 tekens bevatten")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Wachtwoord moet minstens een hoofdletter, een kleine letter, een getal en een speciaal teken bevatten\n"
    )
    private String password;
    @Email(message = "Email is verplicht")
    private String email;
}
