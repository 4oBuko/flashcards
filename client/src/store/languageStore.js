import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const languageStore = defineStore("languageStore", {
  state: () => ({
    languages: [],
  }),
  actions: {
    loadLanguages() {
      instance.get(ENDPOINTS.LANGUAGES_GET).then((response) => {
        this.languages = response.data;
      });
    },
  },
});
