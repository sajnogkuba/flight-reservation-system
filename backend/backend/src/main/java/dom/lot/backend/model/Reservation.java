package dom.lot.backend.model;

public class Reservation {
    private int reservationNumber;
    /**
     * The alreadyDeparted field is a manual operational status.
     * It may not always correlate with the scheduled departure time due to delays or overrides.
     */
    private boolean alreadyDeparted;
    private String seatNumber;
    private Flight flight;
    private Passenger passenger;

    public Reservation() {
    }

    public Reservation(int reservationNumber, boolean alreadyDeparted, String seatNumber, Flight flight, Passenger passenger) {
        this.reservationNumber = reservationNumber;
        this.alreadyDeparted = alreadyDeparted;
        this.seatNumber = seatNumber;
        this.flight = flight;
        this.passenger = passenger;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public boolean isAlreadyDeparted() {
        return alreadyDeparted;
    }

    public void setAlreadyDeparted(boolean alreadyDeparted) {
        this.alreadyDeparted = alreadyDeparted;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
