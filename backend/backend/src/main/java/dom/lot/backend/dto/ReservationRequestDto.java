package dom.lot.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ReservationRequestDto(
        @Min(value = 1, message = "Reservation number must be greater than 0")
        int reservationNumber,

        boolean alreadyDeparted,

        @NotBlank(message = "Seat number is required")
        @Pattern(
                regexp = "^[1-9][0-9]?[A-F]$",
                message = "Seat number must be in format like '12A' or '4F'"
        )
        String seatNumber,

        @NotBlank(message = "Flight number is required")
        @Pattern(
                regexp = "^LO\\d{3,4}$",
                message = "Flight number must be valid (e.g., LO1234)"
        )
        String flightNumber,

        @Min(value = 1, message = "Passenger ID must be greater than 0")
        int passengerId
) {
}

