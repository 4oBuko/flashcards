import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const colorsStore = defineStore("colorsStore", {
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
