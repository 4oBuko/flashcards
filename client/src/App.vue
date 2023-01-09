<script>
import { testLogin } from "./api/ApiRequestManager";
import { login } from "./api/Authentication";
export default {
  created() {
    testLogin();
    //const token = login("test","test").then(response => response.token);
    //const request = new Request("http://localhost:8080/sets/6", {
     // method: "GET",

    //})
  },
};
</script>
<template></template>
<style></style>
