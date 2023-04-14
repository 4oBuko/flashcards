<template>
  <button v-on:click="logout">logout</button>
</template>

<script>
import { logout } from "@/services/authService";

export default {
  name: "Home.vue",
  methods: { logout },
};
</script>

<style scoped></style>
