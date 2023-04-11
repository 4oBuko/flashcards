<template>
  <div>
    <input v-model="email" type="email" name="email" id="email" />
    <br />
    <input
      v-model="password"
      type="password"
      maxlength="50"
      name="password"
      id="password"
    />
    <br />
    <input
      v-model="nickname"
      type="text"
      minlength="5"
      maxlength="50"
      name="password"
      id="password"
    />
    <br />
    <button v-on:click="registerUser">Register</button>
  </div>
</template>

<script>
import { registerNewUser } from "@/api/FlashcardsApi";

export default {
  name: "Register",
  data() {
    return {
      nickname: "",
      email: "",
      password: "",
    };
  },
  methods: {
    registerUser() {
      const response = registerNewUser(
        this.nickname,
        this.email,
        this.password
      );
      console.log(response);
    },
  },
};
</script>

<style scoped></style>
