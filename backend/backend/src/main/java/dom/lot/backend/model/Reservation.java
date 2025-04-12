package dom.lot.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private int reservationNumber;
    /**
     * The alreadyDeparted field is a manual operational status.
     * It may not always correlate with the scheduled departure time due to delays or overrides.
     */
    private boolean alreadyDeparted;
    private String seatNumber;
    private String flightNumber;
    private int passengerId;
}
