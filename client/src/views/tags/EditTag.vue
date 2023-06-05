<script>
import { mapActions, mapState } from "pinia";
import { useTagStore } from "@/store/useTagStore";
import { useColorStore } from "@/store/useColorStore";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import router from "@/router";

export default {
  async beforeMount() {
    const id = this.$route.params.id;
    this.getTag(id);
    this.loadColors();
  },
  mounted() {},
  data() {
    return {
      tagColor: {},
      tagName: "",
      isPublic: false,
      tagSets: [],
    };
  },
  methods: {
    ...mapActions(useTagStore, ["getTag", "update"]),
    ...mapActions(useColorStore, ["loadColors"]),
    updateTag() {
      const updatedTag = { ...this.tag };
      updatedTag.public = this.isPublic;
      updatedTag.name = this.tagName;
      updatedTag.colorId = this.tagColor.id;
      updatedTag.sets = this.tagSets;
      this.update(updatedTag);
      router.push(`/tags/${this.tag.id}`);
    },
  },
  computed: {
    ...mapState(useColorStore, ["colors"]),
    ...mapState(useTagStore, ["tag"]),
    ...mapState(useFlashcardsSetStore, ["userSets"]),
  },
  watch: {
    tag(newTag, prev) {
      if (newTag) {
        this.tagColor = this.tag.color;
        this.tagName = this.tag.name;
        this.isPublic = this.tag.public;
        this.tagSets = this.tag.sets.map((set) => set.id);
      }
    },
  },
};
</script>

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
        :options="colors"
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
        :model-value="tagSets"
        placeholder="Select Sets"
        class="w-full md:w-20rem"
      />
    </div>
    <div class="flex flex-column gap-2">
      <div>Make tag public</div>
      <InputSwitch v-model="isPublic" />
    </div>
    <Button v-on:click="updateTag" label="Update" />
  </div>
</template>

<style scoped></style>
