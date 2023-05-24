<script>
import { mapActions, mapState } from "pinia";
import { useUserStore } from "@/store/useUserStore";
import userService from "@/services/userService";

export default {
  data() {
    return {
      newNickname: "",
      oldPassword: "",
      newPassword: "",
      newEmail: "",
      nicknameAvailable: true,
    };
  },
  mounted() {},
  methods: {
    ...mapActions(useUserStore, ["changeNickname"]),
    changeEmail() {},
    changePassword() {},
    async checkNickname() {
      if (
        this.newNickname.length >= 5 &&
        this.newNickname !== this.user.nickname
      ) {
        return await userService.isNicknameAvailable(this.newNickname);
      } else {
        return true;
      }
    },
  },
  computed: {
    ...mapState(useUserStore, ["user"]),
  },
  watch: {
    newNickname(newNickname, oldNickname) {
      this.checkNickname().then((isAvailable) => {
        this.nicknameAvailable = isAvailable;
      });
    },
  },
};
</script>

<template>
  <div>
    <h1>Profile settings</h1>
    <div class="">
      <h2>Change nickname</h2>
      <div class="flex flex-row gap-3">
        <div class="p-float-label">
          <InputText
            v-on:change="checkNickname"
            v-model="newNickname"
            id="setName"
            class=""
          />
          <label for="setName">New nickname</label>
        </div>
        <Button
          v-on:click="changeNickname(this.newNickname)"
          label="Change nickname"
        />
        <p v-if="newNickname === user.nickname" class="text-red-500 text-xs">
          you already have this nickname
        </p>
        <p v-else-if="!nicknameAvailable" class="text-red-500 text-xs">
          nickname is already used
        </p>
      </div>
    </div>
    <div>
      <h2>Change password</h2>
      <div class="flex flex-row gap-3">
        <div class="p-float-label">
          <InputText id="setName" class="" />
          <label for="setName">Old password</label>
        </div>
        <div class="p-float-label">
          <InputText id="setName" class="" />
          <label for="setName">New password</label>
        </div>
        <Button v-on:click="changePassword" label="Change password" />
      </div>
    </div>
    <div>
      <h2>Change email</h2>
      <div class="flex flex-row gap-3">
        <div class="p-float-label">
          <InputText v-on:change="checkNickname" id="setName" class="" />
          <label for="setName">New email</label>
        </div>
        <Button v-on:click="changeEmail" label="Change email" />
      </div>
    </div>
  </div>
</template>
<style scoped></style>
