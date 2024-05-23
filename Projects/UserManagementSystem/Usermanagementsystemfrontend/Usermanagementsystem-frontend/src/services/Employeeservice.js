import axios from 'axios';

const REST_API__BASE_URL ='http://localhost:8080/api/employees';
export const listUsers =  () =>axios.get(REST_API__BASE_URL);        
export const createUser =  (User) =>axios.post(REST_API__BASE_URL,User);        
export const getUser =  (Userid) =>axios.get(REST_API__BASE_URL+'/'+ Userid);        
export const updateUser =  (Userid,User) =>axios.put(REST_API__BASE_URL+'/'+ Userid,User);   
export const deleteUser =  (Userid) =>axios.delete(REST_API__BASE_URL+'/'+ Userid);            