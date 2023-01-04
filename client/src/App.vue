<script>
// import { register } from "./api/Registration";
import { login } from "./api/Authentication"
export default {
  created() {
    // let response = register("user1", "user1", "user1");
    let x = login("test", "test").then(token => console.log(token)).catch(error=> console.log(error));

  },
};
</script>
<template></template>
<style></style>
