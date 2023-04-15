import { ENDPOINTS } from "@/config/api-routes";
import api from "./axios";
import TokenService from "@/services/TokenService";
import router from "@/router";
import { userStore } from "@/store/userStore";
import { ApiError } from "@/entities/Error";

// todo: add option save me
export function login(email, password, stayLoggedIn = false) {
  api
    .post(
      "/auth/login",
      {
        email: email,
        password: password,
        stayLoggedIn: stayLoggedIn,
      },
      { withCredentials: true }
    )
    .then((response) => {
      console.log("status", response.status);
      if (response.data.token) {
        TokenService.setToken(response.data.token);
        router.push("/");
      }
    })
    .catch((err) => {
      console.log(err.response.status);
      console.log(err.response.data);
      return new ApiError(err.response.status, err.response.data); //todo: return message about failed login
    });
}

export function logout() {
  localStorage.removeItem("token");
  api.defaults.withCredentials = true;
  api.post(ENDPOINTS.LOGOUT);
  userStore.logout;
  router.push("/login");
}
