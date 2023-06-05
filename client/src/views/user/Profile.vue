<script>
import { mapActions, mapState } from "pinia";
import { useUserStore } from "@/store/useUserStore";
import router from "@/router";

export default {
  computed: {
    ...mapState(useUserStore, ["user"]),
  },
  methods: {
    ...mapActions(useUserStore, ["getByNickname"]),
    editProfile() {
      router.push("/settings/account");
    },
  },
};
</script>

<template>
  <div
    class="flex flex-column align-items-center justify-content-center flex-1"
  >
    <div class="flex flex-row">
      <Avatar :label="this.user.nickname[0]" class="mr-2" size="xlarge" />
      <h1>{{ this.user.nickname }}</h1>
    </div>
    <div class="">
      <Button label="edit profile" v-on:click="editProfile" />
    </div>
  </div>
</template>

<style scoped></style>
