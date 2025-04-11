package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.model.Flight;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightService {
    private static final String FLIGHTS_FILE = "data/flights.json";
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
}
