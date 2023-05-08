<template>
  <Button v-on:click="logout" label="Log out" />
</template>

<script>
import { logout } from "@/services/authService";
import { userStore } from "@/store/userStore";

export default {
  created() {
    this.store.loadUser();
  },
  setName: "Home.vue",
  methods: { logout },
  data() {
    return {
      store: userStore(),
    };
  },
};
</script>

<style scoped></style>
