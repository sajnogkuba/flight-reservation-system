import axios from "./axios";

const API_URL = "api/reservations";

export const getAllReservations = async () => axios.get(`${API_URL}`);
export const getReservationByReservationNumber = async (id: number) => axios.get(`${API_URL}/${id}`);
export const createReservation = async (passenger: any) => axios.post(`${API_URL}`, passenger);
export const updateReservation = async (id: number, passenger: any) => axios.put(`${API_URL}/${id}`, passenger);
export const deleteReservation = async (id: number) => axios.delete(`${API_URL}/${id}`);