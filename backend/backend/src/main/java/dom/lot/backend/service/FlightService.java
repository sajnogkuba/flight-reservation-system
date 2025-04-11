package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.exception.FlightNotFoundException;
import dom.lot.backend.model.Flight;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
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

    public List<Flight> getFlights() {
        return Collections.unmodifiableList(flights);
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        saveFlights();
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
    }
}
