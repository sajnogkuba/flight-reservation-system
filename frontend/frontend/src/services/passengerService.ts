import axios from "./axios";

const API_URL = "api/passengers";

export const getAllPassengers = async () => axios.get(`${API_URL}`);
export const getPassengerById = async (id: number) => axios.get(`${API_URL}/${id}`);
export const createPassenger = async (passenger: any) => axios.post(`${API_URL}`, passenger);
export const updatePassenger = async (id: number, passenger: any) => axios.put(`${API_URL}/${id}`, passenger);
export const deletePassenger = async (id: number) => axios.delete(`${API_URL}/${id}`);