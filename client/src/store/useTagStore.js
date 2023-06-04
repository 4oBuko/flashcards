import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";

export const useTagStore = defineStore("tag", {
  state: () => ({
    userTags: [],
    tag: [],
    likes: [],
  }),
  actions: {
    getUserTags() {
      instance.get(ENDPOINTS.OWN_TAGS_GET).then((response) => {
        this.userTags = response.data;
      });
    },
    getTag(id) {
      instance.get(ENDPOINTS.TAG_GET.replace(":id", id)).then((response) => {
        this.tag = response.data;
        let cards = [];
        this.tag.sets.forEach((set) => {
          set.flashcards.forEach((card) => cards.push(card));
        });
        console.log(JSON.stringify(cards));
        this.tag.flashcards = cards;
      });
    },
    getLikes() {
      instance.get(ENDPOINTS.GET_LIKED_TAGS).then((response) => {
        this.likes = response.data;
      });
    },
  },
});
