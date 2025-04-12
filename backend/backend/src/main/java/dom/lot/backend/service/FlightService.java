package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.dto.FlightRequestDto;
import dom.lot.backend.dto.FlightResponseDto;
import dom.lot.backend.exception.FlightAlreadyExistsException;
import dom.lot.backend.exception.FlightNotFoundException;
import dom.lot.backend.model.Flight;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private static final String FLIGHTS_FILE = Path.of("data", "flights.json").toString();
    private List<Flight> flights;

    public FlightService() {
        loadFlights();
    }

    private void loadFlights() {
        this.flights = JsonDataAccess.loadData(FLIGHTS_FILE, new TypeReference<>() {});
    }

    private void saveFlights() {
        JsonDataAccess.saveData(FLIGHTS_FILE, flights);
    }

    public List<FlightResponseDto> getFlights() {
        return this.flights.stream()
                .map(flight -> new FlightResponseDto(
                        flight.getFlightNumber(),
                        flight.getPlaceOfDeparture(),
                        flight.getPlaceOfArrival(),
                        flight.getFlightDuration(),
                        flight.isOnWayFlight(),
                        flight.getAvailableSeats()))
                .toList();
    }

    public void addFlight(FlightRequestDto flightRequestDto) {
        boolean existing = this.flights.stream()
                .anyMatch(flight -> flight.getFlightNumber().equals(flightRequestDto.flightNumber()));
        if (existing) {
            throw new FlightAlreadyExistsException(flightRequestDto.flightNumber());
        }
        if(flightRequestDto.placeOfDeparture().equals(flightRequestDto.placeOfArrival())){
            throw new IllegalArgumentException("Place of departure cannot be equal to the place of arrival");
        }
        Flight flight = getFlight(flightRequestDto);
        flights.add(flight);
        saveFlights();
    }

    private static Flight getFlight(FlightRequestDto flightRequestDto) {
        Duration duration = Duration.parse(flightRequestDto.flightDuration());
        if(!duration.isPositive()) {
            throw new IllegalArgumentException("Flight duration must be positive");
        }
        return new Flight(
                flightRequestDto.flightNumber(),
                flightRequestDto.placeOfDeparture(),
                flightRequestDto.placeOfArrival(),
                duration,
                flightRequestDto.isOnWayFlight(),
                flightRequestDto.availableSeats()
        );
    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flights.stream()
                .filter((flight) -> flightNumber.equals(flight.getFlightNumber()))
                .findFirst()
                .orElseThrow(() -> new FlightNotFoundException(flightNumber));
    }

    public void deleteFlight(String flightNumber) {
        Flight flight = getFlightByFlightNumber(flightNumber);
        flights.remove(flight);
        saveFlights();
    }

    public void updateFlight(String flightNumber, FlightRequestDto flightRequestDto) {
        Flight existingFlight = getFlightByFlightNumber(flightNumber);
        existingFlight.setFlightNumber(flightRequestDto.flightNumber());
        existingFlight.setPlaceOfDeparture(flightRequestDto.placeOfDeparture());
        existingFlight.setPlaceOfArrival(flightRequestDto.placeOfArrival());
        existingFlight.setFlightDuration(Duration.parse(flightRequestDto.flightDuration()));
        existingFlight.setOnWayFlight(flightRequestDto.isOnWayFlight());
        existingFlight.setAvailableSeats(flightRequestDto.availableSeats());
        saveFlights();
    }

    public FlightResponseDto getFlightDtoByFlightNumber(String flightNumber) {
        Flight flight = getFlightByFlightNumber(flightNumber);
        return new FlightResponseDto(
                flight.getFlightNumber(),
                flight.getPlaceOfDeparture(),
                flight.getPlaceOfArrival(),
                flight.getFlightDuration(),
                flight.isOnWayFlight(),
                flight.getAvailableSeats()
        );
    }
}
