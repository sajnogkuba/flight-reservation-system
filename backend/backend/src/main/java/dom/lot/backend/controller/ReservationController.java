package dom.lot.backend.controller;

import dom.lot.backend.dto.ReservationRequestDto;
import dom.lot.backend.dto.ReservationResponseDto;
import dom.lot.backend.model.Reservation;
import dom.lot.backend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations() {
        List<ReservationResponseDto> reservations = reservationService.getReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationNumber}")
    public ResponseEntity<ReservationResponseDto> getReservationNumber(@PathVariable int reservationNumber) {
        ReservationResponseDto reservationResponseDto = reservationService.getReservationDtoByReservationNumber(reservationNumber);
        return ResponseEntity.ok(reservationResponseDto);

    }

    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<String> deleteReservation(@PathVariable int reservationNumber) {
        reservationService.deleteReservation(reservationNumber);
        return ResponseEntity.ok("Reservation deleted successfully");
    }

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.addReservation(reservationRequestDto);
        return ResponseEntity.status(201).body("Reservation added successfully");
    }

    @PutMapping("/{reservationNumber}")
    public ResponseEntity<String> updateReservation(@PathVariable int reservationNumber, @RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.updateReservation(reservationNumber, reservationRequestDto);
        return ResponseEntity.ok("Reservation updated successfully");
    }
}
