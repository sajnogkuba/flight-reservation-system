package dom.lot.backend.service;

import dom.lot.backend.dto.PassengerRequestDto;
import dom.lot.backend.dto.PassengerResponseDto;
import dom.lot.backend.exception.EmailAlreadyInUseException;
import dom.lot.backend.exception.PassengerAlreadyExistsException;
import dom.lot.backend.exception.PassengerNotFoundException;
import dom.lot.backend.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PassengerServiceTest {
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passengerService = new PassengerService() {
            @Override
            public void loadPassengers() {
                this.setPassengers(new ArrayList<>());
            }

            @Override
            public void savePassengers() {

            }
        };
    }

    @Test
    void addPassengerShouldAddPassengerWhenDataIsCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        assertEquals(1, passengerService.getPassengers().size());
        assertEquals("Test", passengerService.getPassengers().getFirst().firstName());
        assertEquals("Test", passengerService.getPassengers().getFirst().lastName());
        assertEquals("Test", passengerService.getPassengers().getFirst().lastName());
        assertEquals("test@gmail.com", passengerService.getPassengers().getFirst().email());
        assertEquals("+123456789", passengerService.getPassengers().getFirst().phoneNumber());
    }

    @Test
    void addPassengerShouldThrowExceptionWhenPassengerIdIsAlreadyTaken(){
        passengerService.addPassenger(getPassengerRequestDto());
        assertThrows(PassengerAlreadyExistsException.class, () -> {
            passengerService.addPassenger(getPassengerRequestDto());
        });
    }

    @Test
    void addPassengerShouldThrowExceptionWhenPassengerEmailIsAlreadyTaken(){
        PassengerRequestDto passengerRequestDto2 = new PassengerRequestDto(2, "Test2", "Test2", "test@gmail.com", "+987654321");
        passengerService.addPassenger(getPassengerRequestDto());
        assertThrows(EmailAlreadyInUseException.class, () -> {
            passengerService.addPassenger(passengerRequestDto2);
        });
    }


    @Test
    void getPassengerShouldReturnPassengerWhenPassengerIdIsCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        Passenger passenger = passengerService.getPassengerById(1);
        assertEquals("Test", passenger.getFirstName());
        assertEquals("Test", passenger.getLastName());
        assertEquals("test@gmail.com", passenger.getEmail());
        assertEquals("+123456789", passenger.getPhoneNumber());
    }

    @Test
    void getPassengerShouldThrowExceptionWhenPassengerIdIsNotCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        assertThrows(PassengerNotFoundException.class, () -> passengerService.getPassengerById(2));
    }

    @Test
    void deletePassengerShouldDeletePassengerWhenIdIsCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        passengerService.deletePassenger(1);
        assertEquals(0, passengerService.getPassengers().size());
    }

    @Test
    void deletePassengerShouldThrowExceptionWhenPassengerIdIsNotCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        assertThrows(PassengerNotFoundException.class, () -> passengerService.deletePassenger(2));
    }

    @Test
    void getPassengersShouldReturnPassengers(){
        PassengerRequestDto passengerRequestDto2 = new PassengerRequestDto(2, "Test2", "Test2", "test2@gmail.com", "+987654321");
        PassengerRequestDto passengerRequestDto3 = new PassengerRequestDto(3, "Test3", "Test3", "test3@gmail.com", "+192837465");
        passengerService.addPassenger(getPassengerRequestDto());
        passengerService.addPassenger(passengerRequestDto2);
        passengerService.addPassenger(passengerRequestDto3);
        assertEquals(3, passengerService.getPassengers().size());
        assertEquals("Test", passengerService.getPassengers().getFirst().firstName());
        assertEquals("Test2", passengerService.getPassengers().get(1).firstName());
        assertEquals("Test3", passengerService.getPassengers().get(2).firstName());
        assertEquals("Test", passengerService.getPassengers().getFirst().lastName());
        assertEquals("Test2", passengerService.getPassengers().get(1).lastName());
        assertEquals("Test3", passengerService.getPassengers().get(2).lastName());
    }

    @Test
    void updateShouldCorrectlyUpdatePassengerWhenDataIsCorrect(){
        PassengerRequestDto passengerRequestDto2 = new PassengerRequestDto(1, "TestUpdated", "TestUpdated", "test@gmail.com", "+123456789");
        passengerService.addPassenger(getPassengerRequestDto());
        assertEquals(1, passengerService.getPassengers().size());
        assertEquals("Test", passengerService.getPassengers().getFirst().firstName());
        assertEquals("Test", passengerService.getPassengers().getFirst().lastName());
        passengerService.updatePassenger(1, passengerRequestDto2);
        Passenger passenger = passengerService.getPassengerById(1);
        assertEquals("TestUpdated", passenger.getFirstName());
        assertEquals("TestUpdated", passenger.getLastName());
        assertEquals("test@gmail.com", passenger.getEmail());
        assertEquals("+123456789", passenger.getPhoneNumber());

    }

    @Test
    void updateShouldThrowExceptionWhenPassengerIdIsNotCorrect(){
        PassengerRequestDto passengerRequestDto = new PassengerRequestDto(1, "Test", "Test", "test@gmail.com", "+123456789");
        PassengerRequestDto passengerRequestDto2 = new PassengerRequestDto(1, "TestUpdated", "TestUpdated", "test@gmail.com", "+123456789");
        passengerService.addPassenger(passengerRequestDto);
        assertThrows(PassengerNotFoundException.class, () -> passengerService.updatePassenger(2, passengerRequestDto2));
    }

    @Test
    void getPassengerDtoByIdShouldReturnCorrectDtoWhenPassengerIdIsCorrect(){
        passengerService.addPassenger(getPassengerRequestDto());
        PassengerResponseDto passengerDtoById = passengerService.getPassengerDtoById(1);
        assertEquals("Test", passengerDtoById.firstName());
        assertEquals("Test", passengerDtoById.lastName());
        assertEquals("test@gmail.com", passengerDtoById.email());
        assertEquals("+123456789", passengerDtoById.phoneNumber());
    }

    @Test
    void getPassengerDtoByIdShouldThrowExceptionWhenPassengerIdIsIncorrect(){
        assertThrows(PassengerNotFoundException.class, () -> passengerService.getPassengerDtoById(2));
    }

    PassengerRequestDto getPassengerRequestDto() {
        return new PassengerRequestDto(1, "Test", "Test", "test@gmail.com", "+123456789");
    }

    @AfterEach
    void tearDown() {
        passengerService = null;
    }
}