<template>
  <div>
    {{ store.set.name }}
  </div>
</template>
<script>
import SetService from "@/services/setService";
import { flashcardsSetStore } from "@/store/flashcardsSetStore";

export default {
  // name: "FlashcardsSet",
  data() {
    return {
      store: flashcardsSetStore(),
      flashcardsSet: {},
    };
  },
  created() {
    // get id from url
    this.store.getById(1);
  },
  methods: { flashcardsSetStore },
};
</script>

<style scoped></style>
