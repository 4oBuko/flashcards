import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const useSearchStore = defineStore("search", {
  state: () => ({
    result: {},
  }),
  actions: {
    searchByName(name) {
      instance
        .get(ENDPOINTS.SEARCH, {
          params: {
            name: name,
          },
        })
        .then((response) => {
          this.result = response.data;
        });
    },
  },
});
