<template>
  <div>
    {{ flashcardsSet.name }}
  </div>
</template>
<script>
import SetService from "@/services/SetService";

export default {
  // name: "FlashcardsSet",
  data() {
    return {
      flashcardsSet: {},
    };
  },
  created() {
    // get id from url
    SetService.getById(1).then((data) => (this.flashcardsSet = data));
  },
  methods: {},
};
</script>

<style scoped></style>
