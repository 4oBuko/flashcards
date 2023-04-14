import { API_URLS } from "@/config/api-routes";
import api from "./axios";
import TokenService from "@/services/TokenService";
import router from "@/router";
import { userStore } from "@/store/userStore";

// todo: add option save me
export function login(email, password, stayLoggedIn = false) {
  api
    .post(
      API_URLS.LOGIN,
      {
        email: email,
        password: password,
        stayLoggedIn: stayLoggedIn,
      },
      { withCredentials: true }
    )
    .then((response) => {
      if (response.data.token) {
        TokenService.setToken(response.data.token);
        router.push("/");
      }
    })
    .catch((err) => {
      console.log(err);
      return err.data; //todo: return message about failed login
    });
}

export function logout() {
  localStorage.removeItem("token");
  api.defaults.withCredentials = true;
  api.post(API_URLS.LOGOUT);
  userStore.logout;
  router.push("/login");
}
