<template>
  <div
    class="flex flex-column align-items-center justify-content-center h-screen gap-4"
  >
    <h1 class="text-4xl text-center">Login</h1>
    <div class="p-float-label">
      <InputText id="email" class="" v-model="email" />
      <label for="email">Email</label>
    </div>
    <div class="p-float-label">
      <Password v-model="password" inputId="password" :feedback="false" />
      <label for="password">Password</label>
    </div>
    <div>
      <Checkbox
        v-model="stayLoggedIn"
        inputId="stayLoggedIn"
        name="stayLoggedIn"
        :value="true"
      />
      <label for="stayLoggedIn"> Stay logged in </label>
    </div>
    <div class="w-2 gap-2 flex justify-content-center">
      <Button class="w-5" v-on:click="loginUser" label="Login" />
      <Button
        class="w-5"
        v-on:click="$router.push('/register')"
        label="Register"
      />
    </div>
  </div>
  <p>{{ this.loginResult }}</p>
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

<style scoped></style>
