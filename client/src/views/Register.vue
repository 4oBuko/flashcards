<template>
  <div>
    <p>Email</p>
    <input v-model="email" type="email" name="email" id="email" />
    <br />
    <p>Password</p>
    <input
      v-model="password"
      type="password"
      maxlength="50"
      name="password"
      id="password"
    />
    <br />
    <p>Nickname</p>
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
    <p>{{ this.registrationMessage }}</p>
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
      registrationMessage: "",
    };
  },
  methods: {
    registerUser() {
      const response = registerNewUser(
        this.nickname,
        this.email,
        this.password
      ).then((r) => (this.registrationMessage = r.message));
      this.nickname = "";
      this.email = "";
      this.password = "";
    },
  },
};
</script>

<style scoped></style>
