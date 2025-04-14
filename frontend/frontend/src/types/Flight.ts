export interface Flight {
    flightNumber: string;
    placeOfDeparture: string;
    placeOfArrival: string;
    flightDuration: string; // ISO 8601 duration, e.g. "PT2H30M"
    isOnWayFlight: boolean;
    availableSeats: string[]; // array of seat identifiers like "1A", "1B"
}
