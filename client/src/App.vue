<script>
import { ref } from '@vue/reactivity';
import { testLogin, login, refresh } from "./api/ApiRequestManager";
export default {
  created() {
    // const response = login("test1", "test1");
    // login("bocchi", "bocchi");
    // refresh();
    let json = testLogin().then(body => body);

  },
};
</script>
<template></template>
<style></style>
