<script>
import router from "@/router";
import { useUserStore } from "@/store/useUserStore";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { mapActions, mapState } from "pinia";
import { useTagStore } from "@/store/useTagStore";
import { getAuthenticated, getNotAuthenticated } from "@/views/menu/menuItems";

export default {
  data() {
    return {
      menuItems: [],
    };
  },
  computed: {
    ...mapState(useUserStore, ["loggedIn", "user"]),
    ...mapState(useTagStore, ["userTags"]),
    ...mapState(useFlashcardsSetStore, ["userSets"]),
  },
  watch: {
    user(newUser, old) {
      this.initMenu();
    },
  },
  beforeMount() {
    this.getUserSets();
    this.getUserTags();
    this.initMenu();
  },

  methods: {
    ...mapActions(useTagStore, ["getUserTags"]),
    ...mapActions(useFlashcardsSetStore, ["getUserSets"]),
    ...mapActions(useUserStore, ["loadUser", "logout"]),
    initMenu() {
      let items = [];
      if (this.user) {
        items = getAuthenticated(this.user);
      } else {
        items = getNotAuthenticated();
      }
      items.forEach((item) => {
        if (item.label === "Log Out") {
          item.command = () => this.logout();
        } else {
          item.command = () => router.push(item.path);
        }
      });
      this.menuItems = items;
    },
  },
};
</script>

<template>
  <div class="card flex justify-content-start h-screen w-3">
    <Menu :model="menuItems" class="w-full"></Menu>
  </div>
</template>

<style scoped></style>
