package dom.lot.backend.controller;

import dom.lot.backend.dto.PassengerRequestDto;
import dom.lot.backend.dto.PassengerResponseDto;
import dom.lot.backend.service.PassengerService;
import dom.lot.backend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
   private final PassengerService passengerService;
   private final ReservationService reservationService;

    public PassengerController(PassengerService passengerService, ReservationService reservationService) {
        this.passengerService = passengerService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<PassengerResponseDto>> getPassengers() {
        List<PassengerResponseDto> passengers = passengerService.getPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDto> getPassengerById(@PathVariable int id) {
        PassengerResponseDto passengerResponseDto = passengerService.getPassengerDtoById(id);
        return ResponseEntity.ok(passengerResponseDto);
    }

    @PostMapping
    public ResponseEntity<String> addPassenger(@Valid @RequestBody PassengerRequestDto passengerRequestDto) {
        passengerService.addPassenger(passengerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passenger added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
        reservationService.deleteReservationsByPassengerId(id);
        return ResponseEntity.ok("Passenger deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePassenger(@PathVariable int id, @Valid @RequestBody PassengerRequestDto passengerRequestDto) {
        passengerService.updatePassenger(id, passengerRequestDto);
        return ResponseEntity.ok("Passenger updated successfully");
    }
}
