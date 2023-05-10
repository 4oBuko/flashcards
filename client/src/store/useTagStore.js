import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const useTagStore = defineStore("tag", {
  state: () => ({
    userTags: [],
  }),
  actions: {
    getUserTags() {
      instance.get(ENDPOINTS.OWN_TAGS_GET).then((response) => {
        this.userTags = response.data;
      });
    },
  },
});