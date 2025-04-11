package dom.lot.backend.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(int id) {
        super("Passenger with id " + id + " not found");
    }
}
