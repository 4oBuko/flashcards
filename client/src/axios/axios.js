import axios from "axios";
import { API_URL, ENDPOINTS, PUBLIC_ENDPOINTS } from "@/config/api-routes";
import TokenService from "@/services/tokenService";

const instance = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

instance.interceptors.request.use(
  (config) => {
    const isValid = TokenService.isTokenValid();
    if (isValid) {
      const token = TokenService.getToken();
      config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (res) => {
    return res;
  },
  async (error) => {
    const originalRequest = error.config;
    const isPublicPage = PUBLIC_ENDPOINTS.includes(originalRequest.url);
    if (!isPublicPage && error.response.status === 401) {
      // Access Token was expired
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        try {
          instance.defaults.withCredentials = true;
          const config = {
            headers: {},
          };
          await instance.post("/auth/refresh", {}, config).then((response) => {
            TokenService.setToken(response.data.token);
            // return instance.request(originalRequest);
          });
        } catch (_error) {
          return {
            status: _error.response.status,
            message: "Session expired. Please login again",
          };
        }
      }
      return instance.request(originalRequest);
    }

    return {
      status: error.response.status,
      message: error.response.data.message,
    };
  }
);
export default instance;
