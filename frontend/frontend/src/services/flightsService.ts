import axios from "./axios";

const API_URL = "api/flights";
import { Duration } from "luxon";

export const getAllFlights = async () => axios.get(`${API_URL}`);
export const getFlightByFlightNumber = async (id: number) => axios.get(`${API_URL}/${id}`);
export const createFlight = async (passenger: any) => axios.post(`${API_URL}`, passenger);
export const updateFlight = async (id: number, passenger: any) => axios.put(`${API_URL}/${id}`, passenger);
export const deleteFlight = async (id: string) => axios.delete(`${API_URL}/${id}`);
export const formatDuration = (duration: string) => {
  return Duration.fromISO(duration).toFormat("h'h' mm'm'");
};