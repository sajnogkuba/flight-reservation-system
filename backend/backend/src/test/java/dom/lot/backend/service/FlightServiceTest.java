package dom.lot.backend.service;

import dom.lot.backend.dto.FlightRequestDto;
import dom.lot.backend.dto.FlightResponseDto;
import dom.lot.backend.exception.FlightAlreadyExistsException;
import dom.lot.backend.exception.FlightNotFoundException;
import dom.lot.backend.model.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FlightServiceTest {
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightService(){
            @Override
            public void loadFlights() {
                this.setFlights(new ArrayList<>());
            }

            @Override
            public void saveFlights() {

            }
        };
    }

    @Test
    void addFlightShouldAddFlightWhenDataIsCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertEquals(1, flightService.getFlights().size());
        assertEquals("LO123", flightService.getFlights().getFirst().flightNumber());
        assertEquals("Warsaw", flightService.getFlights().getFirst().placeOfDeparture());
        assertEquals("New York", flightService.getFlights().getFirst().placeOfArrival());
    }

    @Test
    void addFlightShouldThrowExceptionWhenFlightNumberIsAlreadyTaken(){
        flightService.addFlight(getFlightRequestDto());
        assertThrows(FlightAlreadyExistsException.class, () -> flightService.addFlight(getFlightRequestDto()));
    }

    @Test
    void addFlightShouldThrowExceptionWhenPlaceOfDepartureAndPlaceOfArrivalAreEqual(){
        FlightRequestDto flightRequestDto = new FlightRequestDto(
                "LO123",
                "Warsaw",
                "Warsaw",
                "PT2H30M",
                false,
                List.of("1A", "1B", "2A")
        );
        assertThrows(IllegalArgumentException.class, () -> flightService.addFlight(flightRequestDto));
    }

    @Test
    void addFlightShouldThrowExceptionWhenFlightDurationIsNotPositive(){
        FlightRequestDto flightRequestDto = new FlightRequestDto(
                "LO123",
                "Warsaw",
                "New York",
                "PT0H0M",
                false,
                List.of("1A", "1B", "2A")
        );
        assertThrows(IllegalArgumentException.class, () -> flightService.addFlight(flightRequestDto));
    }

    @Test
    void getFlightsShouldReturnListOfFlights(){
        flightService.addFlight(getFlightRequestDto());
        List<FlightResponseDto> flights = flightService.getFlights();
        assertEquals(1, flights.size());
        assertEquals("LO123", flights.getFirst().flightNumber());
        assertEquals("Warsaw", flights.getFirst().placeOfDeparture());
        assertEquals("New York", flights.getFirst().placeOfArrival());
    }

    @Test
    void getFlightByFlightNumberShouldReturnFlightWhenFlightNumberIsCorrect(){
        flightService.addFlight(getFlightRequestDto());
        Flight flight = flightService.getFlightByFlightNumber("LO123");
        assertEquals("LO123", flight.getFlightNumber());
        assertEquals("Warsaw", flight.getPlaceOfDeparture());
        assertEquals("New York", flight.getPlaceOfArrival());
    }

    @Test
    void getFlightByFlightNumberShouldThrowExceptionWhenFlightNumberIsNotCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightByFlightNumber("LO456"));
    }

    @Test
    void deleteFlightShouldDeleteFlightWhenFlightNumberIsCorrect(){
        flightService.addFlight(getFlightRequestDto());
        flightService.deleteFlight("LO123");
        assertEquals(0, flightService.getFlights().size());
    }

    @Test
    void deleteFlightShouldThrowExceptionWhenFlightNumberIsNotCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertThrows(FlightNotFoundException.class, () -> flightService.deleteFlight("LO456"));
    }

    @Test
    void updateFlightShouldUpdateFlightWhenDataIsCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertEquals(1, flightService.getFlights().size());
        assertEquals("LO123", flightService.getFlights().getFirst().flightNumber());
        assertEquals("Warsaw", flightService.getFlights().getFirst().placeOfDeparture());
        assertEquals("New York", flightService.getFlights().getFirst().placeOfArrival());
        FlightRequestDto updatedFlightRequestDto = new FlightRequestDto(
                "LO123",
                "Warsaw",
                "Los Angeles",
                "PT2H30M",
                false,
                List.of("1A", "1B", "2A")
        );
        flightService.updateFlight("LO123", updatedFlightRequestDto);
        Flight flight = flightService.getFlightByFlightNumber("LO123");
        assertEquals("LO123", flight.getFlightNumber());
        assertEquals("Warsaw", flight.getPlaceOfDeparture());
        assertEquals("Los Angeles", flight.getPlaceOfArrival());
    }

    @Test
    void updateFlightShouldThrowExceptionWhenFlightNumberIsNotCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertThrows(FlightNotFoundException.class, () -> flightService.updateFlight("LO456", getFlightRequestDto()));
    }

    @Test
    void updateFlightShouldThrowExceptionWhenPlaceOfDepartureAndPlaceOfArrivalAreEqual(){
        flightService.addFlight(getFlightRequestDto());
        FlightRequestDto updatedFlightRequestDto = new FlightRequestDto(
                "LO123",
                "Warsaw",
                "Warsaw",
                "PT2H30M",
                false,
                List.of("1A", "1B", "2A")
        );
        assertThrows(IllegalArgumentException.class, () -> flightService.updateFlight("LO123", updatedFlightRequestDto));
    }

    @Test
    void updateFlightShouldThrowExceptionWhenFlightDurationIsNotPositive(){
        flightService.addFlight(getFlightRequestDto());
        FlightRequestDto updatedFlightRequestDto = new FlightRequestDto(
                "LO123",
                "Warsaw",
                "New York",
                "PT0H0M",
                false,
                List.of("1A", "1B", "2A")
        );
        assertThrows(IllegalArgumentException.class, () -> flightService.updateFlight("LO123", updatedFlightRequestDto));
    }

    @Test
    void getFlightDtoByFlightNumberShouldReturnFlightDtoWhenFlightNumberIsCorrect(){
        flightService.addFlight(getFlightRequestDto());
        FlightResponseDto flightResponseDto = flightService.getFlightDtoByFlightNumber("LO123");
        assertEquals("LO123", flightResponseDto.flightNumber());
        assertEquals("Warsaw", flightResponseDto.placeOfDeparture());
        assertEquals("New York", flightResponseDto.placeOfArrival());
    }

    @Test
    void getFlightDtoByFlightNumberShouldThrowExceptionWhenFlightNumberIsNotCorrect(){
        flightService.addFlight(getFlightRequestDto());
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightDtoByFlightNumber("LO456"));
    }

    @Test
    void removeSeatShouldRemoveSeatWhenSeatIsAvailable() {
        flightService.addFlight(getFlightRequestDto());
        flightService.removeSeatFromAvailableSeats("2A", "LO123");
        Flight flight = flightService.getFlightByFlightNumber("LO123");
        assertFalse(flight.getAvailableSeats().contains("2A"));
    }

    @Test
    void removeSeatShouldDoNotRemoveSeatWhenSeatDoesNotExist() {
        flightService.addFlight(getFlightRequestDto());
        flightService.removeSeatFromAvailableSeats("3B", "LO123");
        Flight flight = flightService.getFlightByFlightNumber("LO123");
        assertEquals(3, flight.getAvailableSeats().size());
    }


    FlightRequestDto getFlightRequestDto(){
        return new FlightRequestDto(
                "LO123",
                "Warsaw",
                "New York",
                "PT2H30M",
                false,
                new ArrayList<>(List.of("1A", "1B", "2A"))
        );
    }


    @AfterEach
    void tearDown() {
        flightService = null;
    }
  
}