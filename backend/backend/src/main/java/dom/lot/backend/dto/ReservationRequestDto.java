package dom.lot.backend.dto;

public record ReservationRequestDto (
    int reservationNumber,
    boolean alreadyDeparted,
    String seatNumber,
    String flightNumber,
    int passengerId
){}

