package dom.lot.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String flightNumber;
    private String placeOfDeparture;
    private String placeOfArrival;
    private Duration flightDuration;
    private boolean isOnWayFlight;
    private List<String> availableSeats;
}
