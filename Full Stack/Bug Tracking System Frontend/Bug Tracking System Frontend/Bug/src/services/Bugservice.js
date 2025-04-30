import axios from "axios";
import { getToken } from "./AuthService";

const BASE_REST_API_URL = 'http://localhost:8082/api/bugs';

axios.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});


export const getAllBugs = () => axios.get(BASE_REST_API_URL)

export const saveBug = (bug) => axios.post(BASE_REST_API_URL, bug)

export const getBug = (id) => axios.get(BASE_REST_API_URL + '/' + id)

export const updateBug = (id, bug) => axios.put(BASE_REST_API_URL + '/' + id, bug)

export const deleteBug = (id) => axios.delete(BASE_REST_API_URL + '/' + id)

export const completeBug = (id) => axios.patch(BASE_REST_API_URL + '/' + id + '/complete')

export const inCompleteBug = (id) => axios.patch(BASE_REST_API_URL + '/' + id + '/incomplete')