import { defineStore } from "pinia";
import instance from "@/axios/axios";
import { ENDPOINTS } from "@/config/api-routes";
import api from "@/axios/axios";
import router from "@/router";

export const useFlashcardsSetStore = defineStore("flashcards", {
  state: () => ({
    set: {},
    sets: [],
    userSets: [],
    likes: [],
  }),
  actions: {
    getById(id) {
      instance.get(ENDPOINTS.SET_GET.replace(":id", id)).then((response) => {
        this.set = response.data;
      });
    },
    getUserSets() {
      instance.get(ENDPOINTS.OWN_SETS_GET).then((response) => {
        this.userSets = response.data;
      });
    },
    getLikes() {
      instance.get(ENDPOINTS.GET_LIKED_SETS).then((response) => {
        this.likes = response.data;
      });
    },
    createNew(
      name,
      questionLanguage,
      answerLanguage,
      description,
      cards,
      isPublic
    ) {
      return api
        .post(ENDPOINTS.SET_CREATE, {
          name: name,
          questionLanguageId: questionLanguage.id,
          answerLanguageId: answerLanguage.id,
          description: description,
          flashcards: cards,
          isPublic: isPublic,
        })
        .then((response) => {
          this.set = response.data;
          router.push(`/sets/${this.set.id}`);
        });
    },
    delete(id) {
      instance
        .delete(ENDPOINTS.SET_DELETE.replace(":id", id))
        .then((response) => {
          this.set = {};
          this.getUserSets();
        });
    },
    update(updatedSet) {
      instance
        .put(ENDPOINTS.SET_UPDATE.replace(":id", updatedSet.id), updatedSet)
        .then((response) => {
          this.set = response.data;
        });
    },
  },
});
