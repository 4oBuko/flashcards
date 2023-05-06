<template>
  <p>New Tag</p>
  <div>
    <p>Name</p>
    <input type="text" name="tagName" id="tagName" v-model="tagName" />
    <br />
    <div>
      <p>Sets</p>
      <div v-for="set in setsStore.userSets">
        <input type="checkbox" id="{{set.id}}" :value="set" v-model="tagSets" />
        <label for="{{set.id}}">{{ set.name }}</label>
      </div>
    </div>

    <p>Color</p>
    <fieldset>
      <div v-for="color in colors.colors">
        <p>{{ color.name }}</p>
        <input
          type="radio"
          name="tagColor"
          id="tagColor"
          :value="color"
          v-model="tagColor"
        />
      </div>
    </fieldset>
    <br />
    <p>Is public</p>
    <fieldset>
      <p>Yes</p>
      <input
        type="radio"
        name="isPublic"
        id="public"
        v-model="isPublic"
        :value="true"
      />
      <p>No</p>
      <input
        type="radio"
        name="isPublic"
        id="notPublic"
        v-model="isPublic"
        :value="false"
      />
    </fieldset>
    <button v-on:click="createNewTag">Create</button>
  </div>
</template>

<script>
import { colorsStore } from "@/store/colorsStore";
import { flashcardsSetStore } from "@/store/flashcardsSetStore";
import tagService from "@/services/tagService";

export default {
  name: "CreateTag",
  created() {
    this.colors.loadColors();
    this.setsStore.getUserSets();
  },
  data() {
    return {
      colors: colorsStore(),
      setsStore: flashcardsSetStore(),
      tagName: "",
      isPublic: false,
      tagSets: [],
      tagColor: {},
    };
  },
  methods: {
    createNewTag() {
      const tagData = {
        name: this.tagName,
        isPublic: this.isPublic,
        sets: [...this.tagSets],
      };
      tagService.createNewTag(
        this.tagName,
        this.tagSets,
        this.isPublic,
        this.tagColor
      );
    },
  },
};
</script>

<style scoped></style>
