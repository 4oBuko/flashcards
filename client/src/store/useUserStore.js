import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";
import tokenService from "@/services/tokenService";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: {},
  }),
  actions: {
    getByNickname(nickname) {},
    logout() {
      this.user = {};
    },
    loadUser() {
      const payload = tokenService.getTokenPayload();
      instance
        .get(
          ENDPOINTS.USER_GET_BY_NICKNAME.replace(":nickname", payload.nickname)
        )
        .then((response) => {
          this.user = response.data;
        });
    },
    update() {
      // todo set parameters for update
    },
  },
});
