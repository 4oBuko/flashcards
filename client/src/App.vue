<script>
import { testLogin, login } from "./api/ApiRequestManager";
import {register } from "./api/Registration"
export default {
  created() {
    // const response = login("test1", "test1");
    register("bocchi", "bocchi", "bocchi").then(obj => console.log(obj));
    // testLogin();
  },
};
</script>
<template></template>
<style></style>
