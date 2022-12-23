<script>
import { register } from "./api/Registration";
export default {
  created() {
    register("user1", "user1", "user1");
  },
};
</script>
<template></template>
<style></style>
