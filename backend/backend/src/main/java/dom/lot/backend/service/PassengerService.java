package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.dto.PassengerRequestDto;
import dom.lot.backend.dto.PassengerResponseDto;
import dom.lot.backend.exception.PassengerAlreadyExistsException;
import dom.lot.backend.exception.PassengerNotFoundException;
import dom.lot.backend.model.Passenger;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
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

    public List<PassengerResponseDto> getPassengers() {
        return this.passengers.stream()
                .map(passenger -> new PassengerResponseDto(
                        passenger.getFirstName(),
                        passenger.getLastName(),
                        passenger.getEmail(),
                        passenger.getPhoneNumber(),
                        passenger.getId()
                ))
                .toList();
    }

    public void addPassenger(PassengerRequestDto passengerRequestDto) {
        boolean existing = passengers.stream()
                .anyMatch(passenger -> passenger.getId() == passengerRequestDto.passengerId());

        if (existing) {
            throw new PassengerAlreadyExistsException(passengerRequestDto.passengerId());
        }

        Passenger passenger = getPassenger(passengerRequestDto);
        passengers.add(passenger);
        savePassengers();
    }

    private Passenger getPassenger(PassengerRequestDto passengerRequestDto) {
        return new Passenger(
                passengerRequestDto.firstName(),
                passengerRequestDto.lastName(),
                passengerRequestDto.email(),
                passengerRequestDto.phoneNumber(),
                passengerRequestDto.passengerId()
        );
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

    public void updatePassenger(int id, PassengerRequestDto passengerRequestDto) {
        Passenger existingPassenger = getPassengerById(id);
        existingPassenger.setFirstName(passengerRequestDto.firstName());
        existingPassenger.setLastName(passengerRequestDto.lastName());
        existingPassenger.setEmail(passengerRequestDto.email());
        existingPassenger.setPhoneNumber(passengerRequestDto.phoneNumber());
        savePassengers();
    }

    public PassengerResponseDto getPassengerDtoById(int id) {
        Passenger passenger = getPassengerById(id);
        return new PassengerResponseDto(
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getEmail(),
                passenger.getPhoneNumber(),
                passenger.getId()
        );
    }
}
