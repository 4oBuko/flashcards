<script>
import NavigationBar from "@/views/NavigationBar.vue";

export default {
  components: {
    NavigationBar,
  },
};
</script>
<template>
  <div class="flex">
    <NavigationBar />
    <RouterView />
  </div>
</template>
<style></style>
