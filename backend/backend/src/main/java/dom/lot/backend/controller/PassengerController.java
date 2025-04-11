package dom.lot.backend.controller;

import dom.lot.backend.model.Passenger;
import dom.lot.backend.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
   private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> passengers = passengerService.getPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Passenger passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping
    public ResponseEntity<String> addPassenger(@RequestBody Passenger passenger) {
        passengerService.addPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passenger added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.ok("Passenger deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger) {
        passengerService.updatePassenger(id, passenger);
        return ResponseEntity.ok("Passenger updated successfully");
    }
}
