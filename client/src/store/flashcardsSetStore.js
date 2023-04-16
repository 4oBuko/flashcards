import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const flashcardsSetStore = defineStore("flashcards", {
  state: () => ({
    set: {},
    sets: {},
  }),
  actions: {
    getById(id) {
      instance.get(ENDPOINTS.SET_GET.replace(":id", id)).then((response) => {
        this.set = response.data;
      });
    },
    update(updatedSet) {},
  },
});
