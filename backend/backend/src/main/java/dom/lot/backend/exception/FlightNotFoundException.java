package dom.lot.backend.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String flightNumber) {
        super("Flight with number " + flightNumber + " not found");
    }
}
