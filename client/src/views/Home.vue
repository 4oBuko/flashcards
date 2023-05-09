<template>
  <Button v-on:click="logout" label="Log out" />
  <NavigationBar />
</template>

<script>
import { logout } from "@/services/authService";
import { userStore } from "@/store/userStore";
import NavigationBar from "@/views/NavigationBar.vue";

export default {
  components: {
    NavigationBar,
  },
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
