package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.exception.PassengerNotFoundException;
import dom.lot.backend.model.Passenger;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Service
public class PassengerService {
    private static final String PASSENGERS_FILE = Path.of("data", "passengers.json").toString();
    private List<Passenger> passengers;

    public PassengerService() {
        loadPassengers();
    }

    private void loadPassengers() {
        this.passengers = JsonDataAccess.loadData(PASSENGERS_FILE, new TypeReference<>() {});
    }

    private void savePassengers() {
        JsonDataAccess.saveData(PASSENGERS_FILE, passengers);
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        savePassengers();
    }

    public Passenger getPassengerById(int id) {
        return passengers.stream()
                .filter((passenger) -> id == passenger.getId())
                .findFirst()
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    public void deletePassenger(int id) {
        Passenger passenger = getPassengerById(id);
        passengers.remove(passenger);
        savePassengers();
    }

    public void updatePassenger(int id, Passenger passenger) {
        Passenger existingPassenger = getPassengerById(id);
        existingPassenger.setFirstName(passenger.getFirstName());
        existingPassenger.setLastName(passenger.getLastName());
        existingPassenger.setEmail(passenger.getEmail());
        existingPassenger.setPhoneNumber(passenger.getPhoneNumber());
        savePassengers();
    }
}
