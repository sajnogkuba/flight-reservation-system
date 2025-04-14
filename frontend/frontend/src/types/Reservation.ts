import {Flight} from "./Flight.ts";
import {Passenger} from "./Passenger.ts";

export interface Reservation{
    reservationNumber: number;
    alreadyDeparted: boolean;
    seatNumber: string;
    flight: Flight;
    passenger: Passenger;
}