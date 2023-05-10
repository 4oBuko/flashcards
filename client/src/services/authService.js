import { ENDPOINTS } from "@/config/api-routes";
import api from "../axios/axios";
import TokenService from "@/services/tokenService";
import router from "@/router";
import { useUserStore } from "@/store/useUserStore";
import { ApiError } from "@/entities/Error";

export function login(email, password, stayLoggedIn = false) {
  return api
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
      if (response.status === 200) {
        TokenService.setToken(response.data.token);
        router.push("/");
        return { message: "login successful" };
      } else if (response.status === 401) {
        return response;
      }
    });
}

export function logout() {
  localStorage.removeItem("token");
  api.defaults.withCredentials = true;
  api.post(ENDPOINTS.LOGOUT);
  useUserStore.logout;
  router.push("/login");
}
