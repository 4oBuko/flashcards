<template>
  <div class="flex flex-column align-items-center m-auto h-screen gap-4">
    <!--      todo: flex-1 class style-->
    <h1 class="text-4xl text-center">Register</h1>
    <div class="p-float-label">
      <InputText type="email" id="email" class="" v-model="email" />
      <label for="email">Email</label>
    </div>

    <div class="p-float-label">
      <InputText
        id="email"
        class=""
        v-on:change="checkNickname"
        v-model="nickname"
      />
      <label for="email">Nickname</label>
    </div>

    <div class="p-float-label">
      <Password v-model="password" inputId="password" :feedback="false" />
      <label for="password">Password</label>
    </div>
    <div class="p-float-label align-self-center">
      <Password
        v-model="repeatedPassword"
        inputId="password"
        :feedback="false"
        id="repeatedPassword"
        for="password"
      />
      <label for="password">Repeat Password</label>
    </div>
    <p v-if="!passwordsEqual" class="text-red-500 text-xs">
      passwords must be equal
    </p>
    <p v-if="!nicknameAvailable" class="text-red-500 text-xs">
      nickname is already used
    </p>
    <Button
      v-if="passwordsEqual && nicknameAvailable"
      v-on:click="registerUser"
      label="Register"
    />
    <p class="text-center">{{ this.registrationMessage }}</p>
  </div>
</template>

<script>
import { register } from "@/services/registrationService";
import userService from "@/services/userService";
import router from "@/router";
// todo: add data validation
export default {
  setName: "Register",
  data() {
    return {
      nickname: "",
      email: "",
      password: "",
      repeatedPassword: "",
      registrationMessage: "",
      nicknameAvailable: true,
    };
  },
  methods: {
    registerUser() {
      register(this.nickname, this.email, this.password)
        .then((data) => {
          this.registrationMessage = data;
        })
        .catch((error) => {
          console.log(error);
        });

      this.nickname = "";
      this.email = "";
      this.password = "";
      this.repeatedPassword = "";
      router.push("/");
    },
    async checkNickname() {
      if (this.nickname.length >= 5) {
        return await userService.isNicknameAvailable(this.nickname);
      } else {
        return true;
      }
    },
  },
  computed: {
    passwordsEqual() {
      if (this.password.length <= 5) {
        return true;
      }
      return this.password === this.repeatedPassword;
    },
  },
  watch: {
    nickname(newNickname, oldNickname) {
      this.checkNickname().then((isAvailable) => {
        this.nicknameAvailable = isAvailable;
      });
    },
  },
};
</script>

<style scoped></style>
