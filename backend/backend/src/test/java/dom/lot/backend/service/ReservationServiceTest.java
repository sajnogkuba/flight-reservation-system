package dom.lot.backend.service;

import dom.lot.backend.dto.ReservationRequestDto;
import dom.lot.backend.exception.FlightNotFoundException;
import dom.lot.backend.exception.PassengerNotFoundException;
import dom.lot.backend.exception.ReservationNotFoundException;
import dom.lot.backend.model.Flight;
import dom.lot.backend.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {
    private ReservationService reservationService;
    private FlightService flightService;
    private PassengerService passengerService;
    private MailService mailService;

    @BeforeEach
    void setUp() {
        flightService = mock(FlightService.class);
        passengerService = mock(PassengerService.class);
        mailService = mock(MailService.class);

        reservationService = new ReservationService(flightService, passengerService, mailService){
            @Override
            public void loadReservations() {
                this.setReservations(new ArrayList<>());
            }

            @Override
            public void saveReservations() {

            }
        };
    }
    @Test
    void addReservationShouldAddReservationWhenDataIsCorrect() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        assertEquals(1, reservationService.getReservations().getFirst().reservationNumber());
        assertEquals("LO123", reservationService.getReservations().getFirst().flight().getFlightNumber());
        assertEquals("1A", reservationService.getReservations().getFirst().seatNumber());
        verify(mailService, times(1)).sendReservationConfirmation(getPassenger(), getFlight(), "1A", 1);
        verify(flightService, times(1)).removeSeatFromAvailableSeats("1A", "LO123");
    }

    @Test
    void addReservationShouldThrowExceptionWhenSeatIsNotAvailable() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(
                1,
                false,
                "9A",
                "LO123",
                1
        );
        assertThrows(IllegalArgumentException.class, () -> reservationService.addReservation(reservationRequestDto));
        verify(mailService, never()).sendReservationConfirmation(any(), any(), any(), anyInt());
    }

    @Test
    void addReservationShouldThrowExceptionWhenFlightDoesNotExist() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenThrow(FlightNotFoundException.class);
        assertThrows(FlightNotFoundException.class, () -> reservationService.addReservation(getReservationRequestDto()));
        verify(mailService, never()).sendReservationConfirmation(any(), any(), any(), anyInt());
    }

    @Test
    void addReservationShouldThrowExceptionWhenPassengerDoesNotExist() {
        when(passengerService.getPassengerById(1)).thenThrow(PassengerNotFoundException.class);
        assertThrows(PassengerNotFoundException.class, () -> reservationService.addReservation(getReservationRequestDto()));
        verify(mailService, never()).sendReservationConfirmation(any(), any(), any(), anyInt());
    }

    @Test
    void getReservationsShouldReturnAllReservations() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        assertEquals(1, reservationService.getReservations().getFirst().reservationNumber());
        assertEquals("LO123", reservationService.getReservations().getFirst().flight().getFlightNumber());
        assertEquals("1A", reservationService.getReservations().getFirst().seatNumber());
    }

    @Test
    void getReservationsShouldReturnEmptyListWhenNoReservations() {
        assertEquals(0, reservationService.getReservations().size());
    }

    @Test
    void getReservationByReservationNumberShouldReturnReservationWhenExists() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservationDtoByReservationNumber(1).reservationNumber());
        assertEquals("LO123", reservationService.getReservationDtoByReservationNumber(1).flight().getFlightNumber());
        assertEquals("1A", reservationService.getReservationDtoByReservationNumber(1).seatNumber());
        assertEquals(1, reservationService.getReservationDtoByReservationNumber(1).passenger().getId());
        assertEquals("Test", reservationService.getReservationDtoByReservationNumber(1).passenger().getFirstName());
    }

    @Test
    void getReservationByReservationNumberShouldThrowExceptionWhenNotExists() {
        assertThrows(ReservationNotFoundException.class, () -> reservationService.getReservationDtoByReservationNumber(1));
    }

    @Test
    void deleteReservationShouldDeleteReservationWhenExists() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        reservationService.deleteReservation(1);
        assertEquals(0, reservationService.getReservations().size());
    }

    @Test
    void deleteReservationShouldThrowExceptionWhenNotExists() {
        assertThrows(ReservationNotFoundException.class, () -> reservationService.deleteReservation(1));
    }

    @Test
    void updateReservationShouldUpdateReservationWhenExists() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        ReservationRequestDto updatedReservationRequestDto = new ReservationRequestDto(
                1,
                true,
                "2B",
                "LO123",
                1
        );
        reservationService.updateReservation(1, updatedReservationRequestDto);
        assertEquals(1, reservationService.getReservations().size());
        assertEquals(1, reservationService.getReservations().getFirst().reservationNumber());
        assertEquals("LO123", reservationService.getReservations().getFirst().flight().getFlightNumber());
        assertEquals("2B", reservationService.getReservations().getFirst().seatNumber());
    }

    @Test
    void updateReservationShouldThrowExceptionWhenNotExists() {
        assertThrows(ReservationNotFoundException.class, () -> reservationService.updateReservation(1, getReservationRequestDto()));
    }

    @Test
    void updateReservationShouldReturnOldSeatToAvailableSeats() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        Flight flight = spy(getFlight());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(flight);
        reservationService.addReservation(getReservationRequestDto());
        ReservationRequestDto updated = new ReservationRequestDto(
                1,
                false,
                "2B", // nowe miejsce
                "LO123",
                1
        );
        reservationService.updateReservation(1, updated);
        verify(flightService).addSeatToAvailableSeats("1A", "LO123");
        verify(flightService).removeSeatFromAvailableSeats("2B", "LO123");
    }


    @Test
    void deleteReservationsByPassengerIdShouldDeleteAllReservationsForPassenger() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        reservationService.deleteReservationsByPassengerId(1);
        assertEquals(0, reservationService.getReservations().size());
    }

    @Test
    void deleteReservationsByPassengerIdShouldNotDeleteAnyReservationWhenGivenPassengerDoesNotHaveReservations() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        reservationService.deleteReservationsByPassengerId(2);
        assertEquals(1, reservationService.getReservations().size());
    }

    @Test
    void deleteReservationsByFlightNumberShouldDeleteAllReservationsForFlight() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        reservationService.deleteReservationsByFlightNumber("LO123");
        assertEquals(0, reservationService.getReservations().size());
    }

    @Test
    void deleteReservationsByFlightNumberShouldNotDeleteAnyReservationWhenGivenFlightDoesNotHaveReservations() {
        when(passengerService.getPassengerById(1)).thenReturn(getPassenger());
        when(flightService.getFlightByFlightNumber("LO123")).thenReturn(getFlight());
        reservationService.addReservation(getReservationRequestDto());
        assertEquals(1, reservationService.getReservations().size());
        reservationService.deleteReservationsByFlightNumber("LO456");
        assertEquals(1, reservationService.getReservations().size());
    }




    Passenger getPassenger() {
        return new Passenger("Test", "Test", "test@gmail.com", "123456789", 1);
    }

    Flight getFlight() {
        return new Flight("LO123", "Warsaw", "NY", Duration.ofHours(8), true, new ArrayList<>(List.of("1A", "2B")));
    }

    ReservationRequestDto getReservationRequestDto() {
        return new ReservationRequestDto(
                1,
                false,
                "1A",
                "LO123",
                1
        );
    }

    @AfterEach
    void tearDown() {
        this.reservationService = null;
    }

}