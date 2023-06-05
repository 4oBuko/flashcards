<script>
import { mapActions, mapState } from "pinia";
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { useLanguageStore } from "@/store/useLanguageStore";
import router from "@/router";

export default {
  beforeMount() {
    const id = this.$route.params.id;
    this.getById(id);
  },
  mounted() {
    this.addMoreCards();
  },
  data() {
    return {
      setName: "",
      description: "",
      questionLanguage: {},
      answerLanguage: {},
      flashcards: [],
      isPublic: false,
    };
  },
  methods: {
    ...mapActions(useFlashcardsSetStore, ["getById", "update"]),
    addMoreCards() {
      for (let i = 0; i < 5; i++) {
        this.flashcards.push({
          question: "",
          answer: "",
          index: this.flashcards.length,
        });
      }
    },
    updateSet() {
      this.removeEmptyCards();
      if (this.flashcards.length >= 1) {
        const updatedSet = { ...this.set };
        updatedSet.name = this.setName;
        updatedSet.description = this.description;
        updatedSet.questionLanguageId = this.questionLanguage.id;
        updatedSet.answerLanguageId = this.answerLanguage.id;
        updatedSet.flashcards = this.flashcards;
        updatedSet.public = this.isPublic;
        this.update(updatedSet);
        router.push(`/sets/${this.set.id}`);
      }
    },
    removeFlashcard(index) {
      if (this.flashcards.length - 1 === index) {
        this.flashcards.pop();
      } else {
        this.flashcards.splice(index, 1);
      }
      this.flashcards.forEach((f, i) => (f.index = i));
    },
    removeEmptyCards() {
      this.flashcards.forEach((f) => {
        f.question = f.question.trim();
        f.answer = f.answer.trim();
        return f;
      });
      this.flashcards = this.flashcards.filter((f) => f.question && f.answer);
    },
  },
  computed: {
    ...mapState(useFlashcardsSetStore, ["set"]),
    ...mapState(useLanguageStore, ["languages"]),
  },
  watch: {
    set(newSet, old) {
      if (newSet) {
        this.setName = newSet.name;
        this.description = newSet.description;
        this.questionLanguage = newSet.questionLanguage;
        this.answerLanguage = newSet.answerLanguage;
        this.flashcards = newSet.flashcards;
        this.isPublic = newSet.public;
      }
    },
  },
};
</script>

<template>
  <div class="flex m-auto align-items-center flex-column gap-4">
    <h1 class="text-4xl text-center">New set</h1>
    <div class="p-float-label">
      <InputText type="email" id="setName" class="" v-model="setName" />
      <label for="setName">Name</label>
    </div>
    <span class="p-float-label">
      <Textarea v-model="description" rows="5" cols="20" />
      <label>Description</label>
    </span>
    <div class="p-float-label">
      <Dropdown
        v-model="questionLanguage"
        inputId="dd-city"
        :options="languages"
        optionLabel="name"
        placeholder="Question language"
        class="w-full md:w-14rem"
      />
      <label for="dd-city">Question language</label>
    </div>
    <div class="p-float-label">
      <Dropdown
        v-model="answerLanguage"
        inputId="dd-city"
        :options="languages"
        optionLabel="name"
        placeholder="Answer language"
        class="w-full md:w-14rem"
      />
      <label for="dd-city">Answer language</label>
    </div>
    <div class="flex flex-column gap-4">
      <h2 class="text-2xl text-center">Cards</h2>
      <div v-for="(flashcard, index) in flashcards" class="flex flex-row gap-3">
        <div class="p-float-label">
          <InputText id="setName" class="" v-model="flashcard.question" />
          <label for="setName">Question</label>
        </div>
        <div class="p-float-label">
          <InputText id="setName" class="" v-model="flashcard.answer" />
          <label for="setName">Answer</label>
        </div>
        <Button
          v-on:click="removeFlashcard(index)"
          icon="pi pi-trash"
          aria-label="Submit"
        />
      </div>
      <Button
        v-on:click="addMoreCards"
        icon="pi pi-plus"
        class="w-12"
        aria-label="Submit"
      />
    </div>
    <div class="flex flex-column align-items-center gap-2">
      <div>Make set public</div>
      <InputSwitch v-model="isPublic" />
    </div>
    <Button v-on:click="updateSet" label="Update" />
  </div>
</template>

<style scoped></style>
