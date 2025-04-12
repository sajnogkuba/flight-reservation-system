package dom.lot.backend.dto;

import dom.lot.backend.model.Flight;
import dom.lot.backend.model.Passenger;

public record ReservationResponseDto (
    int reservationNumber,
    boolean alreadyDeparted,
    String seatNumber,
    Flight flight,
    Passenger passenger
){}