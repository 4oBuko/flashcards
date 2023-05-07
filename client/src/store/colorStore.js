import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const colorStore = defineStore("colorStore", {
  state: () => ({
    colors: [],
  }),
  actions: {
    loadColors() {
      instance.get(ENDPOINTS.COLORS_GET).then((response) => {
        this.colors = response.data;
      });
    },
  },
});
