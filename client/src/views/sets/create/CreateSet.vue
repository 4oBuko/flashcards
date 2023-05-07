<template>
  <div>
    <p>name</p>
    <input
      type="text"
      name="setName"
      id="setName"
      maxlength="50"
      minlength="1"
      v-model="setName"
    />
    <br />
    <p>description</p>
    <textarea
      name="description"
      id="description"
      cols="30"
      rows="10"
      maxlength="200"
      v-model="description"
    ></textarea>
    <br />
    <p>Questions language</p>
    <fieldset>
      <div v-for="language in languageStore.languages">
        <p>{{ language.name }}</p>
        <input
          type="radio"
          :value="language"
          v-model="questionLanguage"
          value="{{language.name}}"
        />
      </div>
    </fieldset>
    <br />
    <p>Answer language</p>
    <fieldset>
      <div v-for="language in languageStore.languages">
        <p>{{ language.name }}</p>
        <input
          type="radio"
          :value="language"
          v-model="answerLanguage"
          value="{{language.name}}"
        />
      </div>
    </fieldset>
    <br />
    <p>is Public</p>
    <div>
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
    </div>
    <div>
      <p>Cards</p>

      <div
        v-for="(flashcard, index) in flashcards"
        v-bind:key="flashcard"
        style="display: flex; align-items: stretch"
      >
        <button v-on:click="removeFlashcard(index)">d</button>
        <p>Question</p>
        <input
          type="text"
          name="cardQuestion"
          v-model="flashcard.question"
          id="card-question"
        />
        <p>Answer</p>
        <input
          type="text"
          name="cardAnswer"
          v-model="flashcard.answer"
          id="card-answer"
        />
        <br />
      </div>
      <button v-on:click="addMoreCards">add more</button>
    </div>
    <br />
  </div>
  <div>
    <button v-on:click="createSet">create</button>
    <button>cancel</button>
  </div>
</template>
<script>
import setService from "@/services/setService";
import { languageStore } from "@/store/languageStore";

export default {
  setName: "CreateSet",
  created() {
    this.addMoreCards();
    // todo: load languages after first load of the app
    this.languageStore.loadLanguages();
  },
  data() {
    return {
      setName: "",
      description: "",
      questionLanguage: {},
      answerLanguage: {},
      flashcards: [],
      isPublic: false,
      languageStore: languageStore(),
    };
  },
  methods: {
    addMoreCards() {
      this.flashcards.push({
        question: "",
        answer: "",
        index: this.flashcards.length,
      });
      this.flashcards.push({
        question: "",
        answer: "",
        index: this.flashcards.length,
      });
      this.flashcards.push({
        question: "",
        answer: "",
        index: this.flashcards.length,
      });
      this.flashcards.push({
        question: "",
        answer: "",
        index: this.flashcards.length,
      });
      this.flashcards.push({
        question: "",
        answer: "",
        index: this.flashcards.length,
      });
    },

    createSet() {
      this.removeEmptyCards();
      if (this.flashcards.length >= 1) {
        setService.createNew(
          this.setName,
          this.questionLanguage,
          this.answerLanguage,
          this.description,
          this.flashcards,
          this.isPublic
        );
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
};
</script>

<style scoped></style>
