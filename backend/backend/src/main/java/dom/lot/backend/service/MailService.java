package dom.lot.backend.service;

import dom.lot.backend.model.Flight;
import dom.lot.backend.model.Passenger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReservationConfirmation(Passenger passenger, Flight flight, String seatNumber, int reservationNumber) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(passenger.getEmail());
        message.setSubject("LOT Reservation Confirmation - #" + reservationNumber);
        message.setText("""
                Hello %s %s,
                
                Your reservation #%d has been successfully created.
                
                Flight Number: %s
                Route: %s -> %s
                Seat Number: %s
                
                Thank you for flying with LOT Polish Airlines!
                """.formatted(
                passenger.getFirstName(),
                passenger.getLastName(),
                reservationNumber,
                flight.getFlightNumber(),
                flight.getPlaceOfDeparture(),
                flight.getPlaceOfArrival(),
                seatNumber
                )
        );
        mailSender.send(message);
    }
}
