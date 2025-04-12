package dom.lot.backend.dto;

public class ReservationRequestDto {
    private int reservationNumber;
    private boolean alreadyDeparted;
    private String seatNumber;
    private String flightNumber;
    private int passengerId;

    public ReservationRequestDto() {
    }

    public ReservationRequestDto(int reservationNumber, boolean alreadyDeparted, String seatNumber, String flightNumber, int passengerId) {
        this.reservationNumber = reservationNumber;
        this.alreadyDeparted = alreadyDeparted;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
}
