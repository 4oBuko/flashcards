<template>
  <div>
    <p>Email</p>
    <input v-model="email" type="email" name="email" id="email" />
    <br />
    <p>Password</p>
    <input v-model="password" type="password" name="password" id="password" />
    <br />
    <button v-on:click="loginUser">Login</button>
    <p>{{ this.loginResult }}</p>
  </div>
</template>

<script>
import { login } from "@/services/authService";

export default {
  data() {
    return {
      email: "",
      password: "",
      loginResult: "",
    };
  },

  methods: {
    loginUser() {
      const result = login(this.email, this.password).catch((error) => {
        this.loginResult = error;
      });
    },
  },
};
</script>

<style></style>
