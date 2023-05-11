<script>
import router from "@/router";

export default {
  methods: {
    newTag() {
      router.push("tags/new");
    },
  },
};
</script>

<template>
  <Button v-on:click="newTag">New Tag</Button>
</template>

<style scoped></style>
