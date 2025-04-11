package dom.lot.backend.controller;

import dom.lot.backend.model.Flight;
import dom.lot.backend.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = flightService.getFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByFlightNumber(flightNumber);
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    public ResponseEntity<String> addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flight added successfully");
    }

    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @PutMapping("/{flightNumber}")
    public ResponseEntity<String> updateFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
        flightService.updateFlight(flightNumber, flight);
        return ResponseEntity.ok("Flight updated successfully");
    }
}
