import { defineStore } from "pinia";
import instance from "@/services/axios";
import { API_URLS } from "@/config/api-routes";

export const flashcardsSetStore = defineStore("flashcards", {
  state: () => ({
    set: {},
    sets: {},
  }),
  actions: {
    getById(id) {
      instance.get(API_URLS.SET_GET.replace(":id", id)).then((response) => {
        this.set = response.data;
      });
    },
    update(updatedSet) {},
  },
});
