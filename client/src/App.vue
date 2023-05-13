<script>
import NavigationBar from "@/views/NavigationBar.vue";
import { mapActions, mapState } from "pinia";
import { useLanguageStore } from "@/store/useLanguageStore";
import { useColorStore } from "@/store/useColorStore";

export default {
  components: {
    NavigationBar,
  },
  methods: {
    ...mapActions(useLanguageStore, ["loadLanguages"]),
    ...mapActions(useColorStore, ["loadColors"]),
  },
  computed: {
    ...mapState(useLanguageStore, ["languages"]),
    ...mapState(useColorStore, ["colors"]),
  },
  beforeMount() {
    this.loadLanguages();
    this.loadColors();
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
