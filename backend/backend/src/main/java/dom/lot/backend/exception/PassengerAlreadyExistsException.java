package dom.lot.backend.exception;

public class PassengerAlreadyExistsException extends RuntimeException {
    public PassengerAlreadyExistsException(int passengerId) {
        super("Passenger with id " + passengerId + " already exists");
    }
}
