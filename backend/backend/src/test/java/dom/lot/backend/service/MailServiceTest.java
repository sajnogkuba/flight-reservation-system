package dom.lot.backend.service;

import dom.lot.backend.model.Flight;
import dom.lot.backend.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MailServiceTest {
    private MailService mailService;
    private JavaMailSender mailSender;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        mailService = new MailService(mailSender);
    }

    @Test
    void testSendReservationConfirmation() {
        Passenger passenger = new Passenger("Test", "Test", "test.test@test.com", "123456789", 1);
        Flight flight = new Flight("LO123", "Warsaw", "Berlin", Duration.ofHours(9), false, List.of("1A", "1B"));
        String seatNumber = "1A";
        int reservationNumber = 123;
        mailService.sendReservationConfirmation(passenger, flight, seatNumber, reservationNumber);
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());
        SimpleMailMessage message = captor.getValue();
        assertEquals("test.test@test.com", message.getTo()[0]);
        assertTrue(message.getSubject().contains("#123"));
        assertTrue(message.getText().contains("Your reservation #123 has been successfully created"));
        assertTrue(message.getText().contains("Flight Number: LO123"));
        assertTrue(message.getText().contains("Route: Warsaw -> Berlin"));
        assertTrue(message.getText().contains("Seat Number: 1A"));
    }

}