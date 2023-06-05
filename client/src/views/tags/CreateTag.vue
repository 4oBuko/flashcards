<template>
  <div class="flex m-auto flex-column gap-4">
    <h1 class="text-4xl text-center">New tag</h1>
    <div class="p-float-label">
      <InputText type="email" id="email" class="" v-model="tagName" />
      <label for="email">Name</label>
    </div>
    <div class="p-float-label">
      <Dropdown
        v-model="tagColor"
        inputId="dd-city"
        :options="colors.colors"
        optionLabel="name"
        placeholder="Select a color"
        class="w-full md:w-14rem"
      />
      <label for="dd-city">Select a Color</label>
    </div>
    <div class="w-12 flex flex-column gap-3">
      <h2 class="text-2xl">Sets</h2>
      <MultiSelect
        v-model="tagSets"
        :options="userSets"
        optionValue="id"
        optionLabel="name"
        placeholder="Select Sets"
        class="w-full md:w-20rem"
      />
    </div>
    <div class="flex flex-column gap-2">
      <div>Make tag public</div>
      <InputSwitch v-model="isPublic" />
    </div>
    <Button v-on:click="createNewTag" label="Create" />
  </div>
</template>

<script>
import { useColorStore } from "@/store/useColorStore";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import tagService from "@/services/tagService";
import { ref } from "vue";
import { mapActions, mapState } from "pinia";
import { useTagStore } from "@/store/useTagStore";
import router from "@/router";

export default {
  setName: "CreateTag",
  created() {
    this.colors.loadColors();
    this.setsStore.getUserSets();
  },
  beforeMount() {
    this.getUserSets();
  },
  data() {
    return {
      colors: useColorStore(),
      setsStore: useFlashcardsSetStore(),
      tagName: "",
      isPublic: false,
      tagSets: [],
      tagColor: {},
    };
  },
  methods: {
    ...mapActions(useTagStore, ["createNew", "getUserTags"]),
    ...mapActions(useFlashcardsSetStore, ["getUserSets"]),
    createNewTag() {
      this.createNew(this.tagName, this.tagSets, this.isPublic, this.tagColor);
    },
  },
  computed: {
    ...mapState(useFlashcardsSetStore, ["userSets"]),
  },
};
</script>

<style scoped></style>
