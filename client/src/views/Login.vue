<template>
  <div>
    <p>Email</p>
    <input v-model="email" type="email" name="email" id="email" />
    <br />
    <p>Password</p>
    <input v-model="password" type="password" name="password" id="password" />
    <br />
    <p>stay logged in</p>
    <input
      v-model="stayLoggedIn"
      type="checkbox"
      name="stayLoggedIn"
      id="stayLoggedIn"
    />
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
      stayLoggedIn: false,
      loginResult: "",
    };
  },

  methods: {
    loginUser() {
      login(this.email, this.password, this.stayLoggedIn).then((result) => {
        this.loginResult = result.message;
      });
    },
  },
};
</script>

<style></style>
