package dom.lot.backend.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(int reservationNumber) {
        super("Reservation with number " + reservationNumber + " not found.");
    }
}
