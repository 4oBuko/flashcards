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
      <div v-for="set in setsStore.userSets" class="">
        <Checkbox
          v-model="tagSets"
          :inputId="set.id"
          name="category"
          :value="set.name"
        />
        <label :for="set.key">{{ set.name }}</label>
      </div>
    </div>
    <div class="flex flex-column gap-2">
      <div>Make tag public</div>
      <InputSwitch v-model="isPublic" />
    </div>
    <Button v-on:click="createNewTag" label="Create" />
  </div>
</template>

<script>
import { colorStore } from "@/store/colorStore";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import tagService from "@/services/tagService";
import { ref } from "vue";

export default {
  setName: "CreateTag",
  created() {
    this.colors.loadColors();
    this.setsStore.getUserSets();
  },
  data() {
    return {
      colors: colorStore(),
      setsStore: useFlashcardsSetStore(),
      tagName: "",
      isPublic: false,
      tagSets: [],
      tagColor: {},
    };
  },
  methods: {
    createNewTag() {
      tagService.createNewTag(
        this.tagName,
        this.tagSets,
        this.isPublic,
        this.tagColor
      );
    },
  },
  computed: {},
};
</script>

<style scoped></style>
