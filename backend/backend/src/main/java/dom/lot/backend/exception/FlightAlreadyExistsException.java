package dom.lot.backend.exception;

public class FlightAlreadyExistsException extends RuntimeException {
    public FlightAlreadyExistsException(String flightNumber) {
        super("Flight with flight number " + flightNumber + " already exists");
    }
}
