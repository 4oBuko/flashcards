import { defineStore } from "pinia";

export const userStore = defineStore({
  state: () => {
    user: {
    }
  },
  actions: {
    getByNickname(nickname) {},
    logout() {
      this.user = {};
    },
    update() {
      // todo set parameters for update
    },
  },
});
