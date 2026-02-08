import axios from "axios";
import { getToken } from "./AuthService";
import qs from "qs";
const BASE_REST_API_URL = "http://localhost:8082/api/bugs";
const BASE_REST_COMS_URL = "http://localhost:8082/api/coms";
const BASE_USERS_API_URL = "http://localhost:8082/api/users";
const BASE_SPRINTS_API_URL = "http://localhost:8082/api/sprints";
const BASE_IMAGES_API_URL = "http://localhost:8082/api/images";

axios.interceptors.request.use(
  function (config) {
    config.headers["Authorization"] = getToken();

    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export const getAllUsers = () => axios.get(BASE_USERS_API_URL);

export const getAllBugs = (page = 0, size = 8) =>
  axios.get(`${BASE_REST_API_URL}?page=${page}&size=${size}`);
export const getAllSprints = () => axios.get(BASE_SPRINTS_API_URL);

export const saveBug = (bug) => axios.post(BASE_REST_API_URL, bug);

export const getBug = (id) => axios.get(BASE_REST_API_URL + "/" + id);

export const updateBug = (id, bug) =>
  axios.put(BASE_REST_API_URL + "/" + id, bug);

export const deleteBug = (id) => axios.delete(BASE_REST_API_URL + "/" + id);

export const completeBug = (id) =>
  axios.patch(BASE_REST_API_URL + "/" + id + "/complete");

export const inCompleteBug = (id) =>
  axios.patch(BASE_REST_API_URL + "/" + id + "/incomplete");

export const getBugAnalytics = () =>
  axios.get(BASE_REST_API_URL + "/analytics");


export const uploadImage = (bugId, files) => {
    const formData = new FormData();
    
    files.forEach(file => {
        formData.append("files", file); 
    });

    return axios.post(`${BASE_IMAGES_API_URL}/upload/${bugId}`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
    });
};

// 🔹 Get images for a specific bug
export const getBugImages = (bugId) => 
    axios.get(`${BASE_IMAGES_API_URL}/${bugId}`, {
        headers: { "Authorization": getToken() } 
    })
    .then(response => {
           
        return Array.isArray(response.data) ? response.data : [];
    })
    .catch(error => {
        console.error(`No images available for bug ID: ${bugId}`, error);
        return [];
    });




export const saveBugWithImages = async (bug, imageFiles) => {
    const bugResponse = await saveBug(bug);
    const bugId = bugResponse.data.id;

    if (imageFiles.length > 0) {
        await uploadImage(bugId, imageFiles); 
    }

    return bugResponse;
};

export const updateBugWithImages = async (id, bug, imageFiles) => {
    await updateBug(id, bug);

    if (imageFiles.length > 0) {
        await uploadImage(id, imageFiles); 
    }
};


export const getBugComments = (bugId) => {
    return axios.get(`${BASE_REST_COMS_URL }/${bugId}/comments`); 
};

export const addBugComment = (bugId, userId, commentText) => {
  console.log("Sending Comment:", { bugId, userId, commentText });

  const data = qs.stringify({ userId, commentText });

  return axios.post(`${BASE_REST_COMS_URL}/${bugId}/comments`, data, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  })
  .then(response => {
    console.log("API Response:", response.data);
    return response.data;
  })
  .catch(error => {
    console.error("Error adding comment:", error.response?.data || error);
    throw error;
  });
};