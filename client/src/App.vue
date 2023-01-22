<script>
import { ref } from "@vue/reactivity";
import { testGetId, login, refresh } from "./api/ApiRequestManager";
export default {
  created() {
    const body = testGetId();
    console.log(body);
  },
};
</script>
<template></template>
<style></style>
