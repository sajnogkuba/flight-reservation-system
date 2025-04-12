package dom.lot.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PassengerRequestDto (

    int passengerId,

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 1 and 50 characters")
    String firstName,

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 1 and 50 characters")
    String lastName,

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    String email,

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(
            regexp = "^\\+?[0-9]{6,15}$",
            message = "Phone number must contain only digits and may start with '+'"
    )
    String phoneNumber

){}
