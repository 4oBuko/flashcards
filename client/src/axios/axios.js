import axios from "axios";
import { API_URL, ENDPOINTS } from "@/config/api-routes";
import TokenService from "@/services/tokenService";
import { ApiError } from "@/entities/Error";

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
    if (
      originalRequest.url !== "/auth/login" &&
      !originalRequest.url.startsWith(
        ENDPOINTS.CHECK_NICKNAME.replace("/:nickname", "")
      ) &&
      error.response.status === 401
    ) {
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
          return new ApiError(
            _error.response.status,
            "Session expired. Please login again"
          );
        }
      }
      return instance.request(originalRequest);
    }
    return new ApiError(error.response.status, error.response.data.message);
  }
);
export default instance;
