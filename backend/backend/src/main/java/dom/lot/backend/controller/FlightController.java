package dom.lot.backend.controller;

import dom.lot.backend.dto.FlightRequestDto;
import dom.lot.backend.dto.FlightResponseDto;
import dom.lot.backend.model.Flight;
import dom.lot.backend.service.FlightService;
import dom.lot.backend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;
    private final ReservationService reservationService;

    public FlightController(FlightService flightService, ReservationService reservationService) {
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getFlights() {
        List<FlightResponseDto> flights = flightService.getFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightResponseDto> getFlightByFlightNumber(@PathVariable String flightNumber) {
        FlightResponseDto flight = flightService.getFlightDtoByFlightNumber(flightNumber);
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    public ResponseEntity<String> addFlight(@Valid @RequestBody FlightRequestDto flightRequestDto) {
        flightService.addFlight(flightRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flight added successfully");
    }

    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        reservationService.deleteReservationsByFlightNumber(flightNumber);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @PutMapping("/{flightNumber}")
    public ResponseEntity<String> updateFlight(@PathVariable String flightNumber, @Valid @RequestBody FlightRequestDto flightRequestDto) {
        flightService.updateFlight(flightNumber, flightRequestDto);
        return ResponseEntity.ok("Flight updated successfully");
    }
}
