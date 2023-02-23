<template>
  <input v-model="email" type="email" name="email" id="email"/>
  <br/>
  <input v-model="password" type="password" name="password" id="password"/>
  <br/>
  <button v-on:click="loginUser">Login</button>
  <p>{{ this.loginResult }}</p>
</template>

<script>
import {login, getUserById} from "@/api/FlashcardsApi";

export default {
  data() {
    return {
      email: "",
      password: "",
      loginResult: ""
    };
  },

  methods: {
    loginUser() {
      const result = login(this.email, this.password).then(response => this.loginResult = response.message);
      console.log(result);
    },
  },
};
</script>

<style></style>
