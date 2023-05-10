<template>
  <div class="flex flex-column align-items-center m-auto h-screen gap-4">
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
        binary
      />
      <label for="stayLoggedIn"> Stay logged in </label>
    </div>
    <div class="gap-2 flex">
      <Button class="" v-on:click="loginUser" label="Login" />
      <Button
        class=""
        v-on:click="$router.push('/register')"
        label="Register"
      />
    </div>
    <p>{{ this.loginResult }}</p>
  </div>
</template>

<script>
import { mapActions } from "pinia";
import { useUserStore } from "@/store/useUserStore";

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
    ...mapActions(useUserStore, ["login"]),
    loginUser() {
      this.login(this.email, this.password, this.stayLoggedIn).then(
        (result) => {
          this.loginResult = result.message;
        }
      );
    },
  },
};
</script>

<style scoped></style>
