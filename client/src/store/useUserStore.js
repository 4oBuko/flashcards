import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";
import tokenService from "@/services/tokenService";
import router from "@/router";
import api from "@/axios/axios";
import TokenService from "@/services/tokenService";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: {},
  }),
  actions: {
    getByNickname(nickname) {},
    logout() {
      this.user = {};
      localStorage.removeItem("token");
      instance.defaults.withCredentials = true;
      instance.post(ENDPOINTS.LOGOUT);
      router.push("/login");
    },
    loadUser() {
      if (tokenService.isTokenValid()) {
        const payload = tokenService.getTokenPayload();
        instance
          .get(
            ENDPOINTS.USER_GET_BY_NICKNAME.replace(
              ":nickname",
              payload.nickname
            )
          )
          .then((response) => {
            this.user = response.data;
          });
      }
    },
    login(email, password, stayLoggedIn = false) {
      return instance
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
    },
    changeNickname(nickname) {
      instance
        .put(ENDPOINTS.USER_CHANGE_NICKNAME, { newNickname: nickname })
        .then((response) => {
          this.user = response.data;
          this.logout();
        });
    },
  },
});
