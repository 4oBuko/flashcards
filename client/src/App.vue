<script>
import NavigationBar from "@/views/menu/NavigationBar.vue";
import { mapActions, mapState } from "pinia";
import { useLanguageStore } from "@/store/useLanguageStore";
import { useColorStore } from "@/store/useColorStore";
import { useUserStore } from "@/store/useUserStore";

export default {
  components: {
    NavigationBar,
  },
  methods: {
    ...mapActions(useUserStore, ["loadUser"]),
    ...mapActions(useLanguageStore, ["loadLanguages"]),
    ...mapActions(useColorStore, ["loadColors"]),
  },
  computed: {
    ...mapState(useLanguageStore, ["languages"]),
    ...mapState(useColorStore, ["colors"]),
  },
  beforeMount() {
    this.loadUser();
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
