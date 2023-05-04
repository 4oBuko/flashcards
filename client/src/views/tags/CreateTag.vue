<template>
  <p>New Tag</p>
</template>

<script>
import { colorsStore } from "@/store/colorsStore";

export default {
  name: "CreateTag",
  created() {
    this.colors.loadColors();
  },
  data() {
    return {
      colors: colorsStore(),
    };
  },
};
</script>

<style scoped></style>
