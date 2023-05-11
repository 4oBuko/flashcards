<script>
import router from "@/router";

export default {
  methods: {
    newSet() {
      router.push("/sets/new");
    },
  },
};
</script>

<template>
  <Button v-on:click="newSet">add New</Button>
</template>

<style scoped></style>
