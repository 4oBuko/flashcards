import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";
import router from "@/router";

export const useTagStore = defineStore("tag", {
  state: () => ({
    userTags: [],
    tag: {},
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
    delete(id) {
      instance
        .delete(ENDPOINTS.TAG_DELETE.replace(":id", id))
        .then((response) => {
          this.tag = {};
          this.getUserTags();
        });
    },
    createNew(name, sets, isPublic, color) {
      instance
        .post(ENDPOINTS.TAG_CREATE, {
          name: name,
          sets: sets,
          isPublic: isPublic,
          colorId: color.id,
        })
        .then((response) => {
          this.tag = response.data;
          router.push(`/tags/${this.tag.id}`);
        });
    },
  },
});
