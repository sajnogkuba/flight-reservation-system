package dom.lot.backend.model;

import java.time.Duration;
import java.util.List;

public class Flight {
    private String flightNumber;
    private String placeOfDeparture;
    private String placeOfArrival;
    private Duration flightDuration;
    private boolean isOnWayFlight;
    private List<String> availableSeats;

    public Flight() {
    }

    public Flight(String flightNumber, String placeOfDeparture, String placeOfArrival, Duration flightDuration, boolean isOnWayFlight, List<String> availableSeats) {
        this.flightNumber = flightNumber;
        this.placeOfDeparture = placeOfDeparture;
        this.placeOfArrival = placeOfArrival;
        this.flightDuration = flightDuration;
        this.isOnWayFlight = isOnWayFlight;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public String getPlaceOfArrival() {
        return placeOfArrival;
    }

    public void setPlaceOfArrival(String placeOfArrival) {
        this.placeOfArrival = placeOfArrival;
    }

    public Duration getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Duration flightDuration) {
        this.flightDuration = flightDuration;
    }

    public boolean isOnWayFlight() {
        return isOnWayFlight;
    }

    public void setOnWayFlight(boolean onWayFlight) {
        isOnWayFlight = onWayFlight;
    }

    public List<String> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<String> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
