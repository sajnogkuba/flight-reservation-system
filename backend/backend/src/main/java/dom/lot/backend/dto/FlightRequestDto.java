package dom.lot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.Duration;
import java.util.List;

public record FlightRequestDto(

        @NotBlank(message = "Flight number is mandatory")
        @Pattern(regexp = "^LO\\d{3,4}$", message = "Flight number must start with 'LO' followed by 3 or 4 digits")
        String flightNumber,

        @NotBlank(message = "Departure place is mandatory")
        @Pattern(regexp = "^[A-Z][a-zA-Z\\s\\-]{1,50}$", message = "Place must be a valid city name")
        String placeOfDeparture,

        @NotBlank(message = "Arrival place is mandatory")
        @Pattern(regexp = "^[A-Z][a-zA-Z\\s\\-]{1,50}$", message = "Place must be a valid city name")
        String placeOfArrival,

        @Pattern(
                regexp = "^PT(?:[0-9]{1,2}H)?(?:[0-9]{1,2}M)?$",
                message = "Flight duration must be in format PT#H#M (e.g., PT2H30M)"
        )
        String flightDuration,

        boolean isOnWayFlight,

        @NotNull(message = "Seat list must be provided")
        List<String> availableSeats
) {
}
