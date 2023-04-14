import { API_URLS } from "@/config/api-routes";
import api from "./axios";
import TokenService from "@/services/TokenService";
import router from "@/router";

// todo: add option save me
export function login(email, password) {
  api
    .post(API_URLS.LOGIN, {
      email: email,
      password: password,
    })
    .then((response) => {
      if (response.data.token) {
        TokenService.setToken(response.data.token);
      }
    })
    .catch((err) => {
      console.log(err);
    });
}
export function logout() {
  localStorage.removeItem("token");
  api.defaults.withCredentials = true;
  api.post(API_URLS.LOGOUT);
  router.push("/login");
}
