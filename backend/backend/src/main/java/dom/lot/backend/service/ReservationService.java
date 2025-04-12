package dom.lot.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import dom.lot.backend.dto.ReservationRequestDto;
import dom.lot.backend.dto.ReservationResponseDto;
import dom.lot.backend.exception.ReservationNotFoundException;
import dom.lot.backend.model.Reservation;
import dom.lot.backend.util.JsonDataAccess;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class ReservationService {
    private static final String RESERVATIONS_FILE = Path.of("data", "reservations.json").toString();
    private List<Reservation> reservations;
    private final FlightService flightService;
    private final PassengerService passengerService;

    public ReservationService(FlightService flightService, PassengerService passengerService) {
        this.flightService = flightService;
        this.passengerService = passengerService;
        loadReservations();
    }

    private void loadReservations() {
        this.reservations = JsonDataAccess.loadData(RESERVATIONS_FILE, new TypeReference<>() {});
    }

    private void saveReservations() {
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
        Reservation reservation = new Reservation(
                reservationRequestDto.getReservationNumber(),
                reservationRequestDto.isAlreadyDeparted(),
                reservationRequestDto.getSeatNumber(),
                reservationRequestDto.getFlightNumber(),
                reservationRequestDto.getPassengerId()
        );
        reservations.add(reservation);
        saveReservations();
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
        existingReservation.setAlreadyDeparted(reservationRequestDto.isAlreadyDeparted());
        existingReservation.setSeatNumber(reservationRequestDto.getSeatNumber());
        existingReservation.setFlightNumber(reservationRequestDto.getFlightNumber());
        existingReservation.setPassengerId(reservationRequestDto.getPassengerId());
        existingReservation.setReservationNumber(reservationRequestDto.getReservationNumber());
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
}
