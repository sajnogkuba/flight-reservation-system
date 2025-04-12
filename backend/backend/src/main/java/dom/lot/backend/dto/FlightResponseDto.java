package dom.lot.backend.dto;

import java.time.Duration;
import java.util.List;

public record FlightResponseDto(
        String flightNumber,
        String placeOfDeparture,
        String placeOfArrival,
        Duration flightDuration,
        boolean isOnWayFlight,
        List<String>availableSeats) {
}
