<template class="flex-1">
  <p>main</p>
</template>

<script>
import { useUserStore } from "@/store/useUserStore";

export default {
  created() {
    this.store.loadUser();
  },
  setName: "Home.vue",
  data() {
    return {
      store: useUserStore(),
    };
  },
};
</script>

<style scoped></style>
