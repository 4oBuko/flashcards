<template>
  <input v-model="email" type="email" name="email" id="email"/>
  <br/>
  <input v-model="password" type="password" name="password" id="password"/>
  <br/>
  <button v-on:click="loginUser">Login</button>
</template>

<script>
import {login, getUserById} from "@/api/FlashcardsApi";

export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },

  methods: {
    loginUser() {
      console.log(this.email, this.password);
      login(this.email, this.password).then(data => console.log(data));
      console.log(getUserById(1));
    },
  },
};
</script>

<style></style>
