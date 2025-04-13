package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.dto.ReservationRequestDto;
import dom.lot.backend.dto.ReservationResponseDto;
import dom.lot.backend.exception.ReservationNotFoundException;
import dom.lot.backend.model.Flight;
import dom.lot.backend.model.Passenger;
import dom.lot.backend.model.Reservation;
import dom.lot.backend.util.JsonDataAccess;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Setter
@Service
public class ReservationService {
    private static final String RESERVATIONS_FILE = Path.of("data", "reservations.json").toString();
    private List<Reservation> reservations;
    private final FlightService flightService;
    private final PassengerService passengerService;
    private final MailService mailService;

    public ReservationService(FlightService flightService, PassengerService passengerService, MailService mailService) {
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.mailService = mailService;
        loadReservations();
    }

    public void loadReservations() {
        this.reservations = JsonDataAccess.loadData(RESERVATIONS_FILE, new TypeReference<>() {});
    }

    public void saveReservations() {
        JsonDataAccess.saveData(RESERVATIONS_FILE, reservations);
    }

    public List<ReservationResponseDto> getReservations() {
         return this.reservations.stream()
                .map(reservation -> new ReservationResponseDto(
                        reservation.getReservationNumber(),
                        reservation.isAlreadyDeparted(),
                        reservation.getSeatNumber(),
                        flightService.getFlightByFlightNumber(reservation.getFlightNumber()),
                        passengerService.getPassengerById(reservation.getPassengerId())
                ))
                .toList();
    }

    public void addReservation(ReservationRequestDto reservationRequestDto) {
        Reservation reservation = getReservation(reservationRequestDto);
        reservations.add(reservation);
        saveReservations();
    }

    private Reservation getReservation(ReservationRequestDto reservationRequestDto) {
        Passenger passenger = passengerService.getPassengerById(reservationRequestDto.passengerId());
        Flight flight = flightService.getFlightByFlightNumber(reservationRequestDto.flightNumber());
        if (flight.getAvailableSeats().stream().anyMatch(reservationRequestDto.seatNumber()::equals)) {
            flightService.removeSeatFromAvailableSeats(reservationRequestDto.seatNumber(), flight.getFlightNumber());
        } else {
            throw new IllegalArgumentException("Seat " + reservationRequestDto.seatNumber() + " is not available.");
        }
        mailService.sendReservationConfirmation(passenger, flight, reservationRequestDto.seatNumber(), reservationRequestDto.reservationNumber());
        return new Reservation(
                reservationRequestDto.reservationNumber(),
                reservationRequestDto.alreadyDeparted(),
                reservationRequestDto.seatNumber(),
                flight.getFlightNumber(),
                passenger.getId()
        );
    }

    private Reservation getReservationByReservationNumber(int reservationNumber) {
        return reservations.stream()
                .filter((reservation) -> reservationNumber == reservation.getReservationNumber())
                .findFirst()
                .orElseThrow(() -> new ReservationNotFoundException(reservationNumber));
    }

    public void deleteReservation(int reservationNumber) {
        Reservation reservation = getReservationByReservationNumber(reservationNumber);
        reservations.remove(reservation);
        saveReservations();
    }

    public void updateReservation(int reservationNumber, ReservationRequestDto reservationRequestDto) {
        Reservation existingReservation = getReservationByReservationNumber(reservationNumber);
        String oldSeatNumber = existingReservation.getSeatNumber();
        String oldFlightNumber = existingReservation.getFlightNumber();
        flightService.addSeatToAvailableSeats(oldSeatNumber, oldFlightNumber);
        Reservation updatedReservation = getReservation(reservationRequestDto);
        existingReservation.setAlreadyDeparted(updatedReservation.isAlreadyDeparted());
        existingReservation.setSeatNumber(updatedReservation.getSeatNumber());
        existingReservation.setFlightNumber(updatedReservation.getFlightNumber());
        existingReservation.setPassengerId(updatedReservation.getPassengerId());
        existingReservation.setReservationNumber(updatedReservation.getReservationNumber());
        saveReservations();
    }

    public ReservationResponseDto getReservationDtoByReservationNumber(int reservationNumber) {
        Reservation reservation = getReservationByReservationNumber(reservationNumber);
        return new ReservationResponseDto(
                reservation.getReservationNumber(),
                reservation.isAlreadyDeparted(),
                reservation.getSeatNumber(),
                flightService.getFlightByFlightNumber(reservation.getFlightNumber()),
                passengerService.getPassengerById(reservation.getPassengerId())
        );


    }

    public void deleteReservationsByPassengerId(int id) {
        this.reservations.removeIf(reservation -> reservation.getPassengerId() == id);
        saveReservations();
    }

    public void deleteReservationsByFlightNumber(String flightNumber) {
        this.reservations.removeIf(reservation -> reservation.getFlightNumber().equals(flightNumber));
        saveReservations();
    }
}
