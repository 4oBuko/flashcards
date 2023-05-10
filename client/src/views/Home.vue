<template>
  <NavigationBar />
</template>

<script>
import { logout } from "@/services/authService";
import { useUserStore } from "@/store/useUserStore";
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
      store: useUserStore(),
    };
  },
};
</script>

<style scoped></style>
