<template>
  <div>
    {{ store.set.name }}
  </div>
</template>
<script>
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";

export default {
  // name: "FlashcardsSet",
  data() {
    return {
      store: useFlashcardsSetStore(),
      flashcardsSet: {},
    };
  },
  created() {
    // get id from url
    this.store.getById(1);
  },
  methods: { flashcardsSetStore: useFlashcardsSetStore },
};
</script>

<style scoped></style>
