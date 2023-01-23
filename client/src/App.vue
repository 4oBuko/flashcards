<script>
import { ref } from "@vue/reactivity";
import { testGetId, login, refresh } from "./api/ApiRequestManager";
export default {
  async created() {
    const body = await testGetId();
    console.log(body);
  },
};
</script>
<template></template>
<style></style>
