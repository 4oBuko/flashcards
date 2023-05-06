<template>
  <div>
    <p>name</p>
    <input
      type="text"
      name="setName"
      id="setName"
      maxlength="50"
      minlength="1"
    />
    <br />
    <p>description</p>
    <textarea
      name="description"
      id="description"
      cols="30"
      rows="10"
      maxlength="200"
    ></textarea>
    <br />
    <p>Questions language</p>
    <select>
      <option value="non">question languages</option>
    </select>
    <br />
    <p>Answer language</p>
    <select>
      <option value="non">answer languages</option>
    </select>
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
export default {
  name: "CreateSet",
  created() {
    this.addMoreCards();
    //   todo get languages from storage
  },
  data() {
    return {
      name: "",
      description: "",
      questionLanguage: "",
      answerLanguage: "",
      flashcards: [],
      isPublic: false,
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
      //   remove empty elements
      this.flashcards.forEach((f) => {
        f.question = f.question.trim();
        f.answer = f.answer.trim();
        return f;
      });
      const filledCards = this.flashcards.filter((f) => f.question && f.answer);
      if (filledCards.length >= 1) {
      }
      console.log(filledCards);
    },
    removeFlashcard(index) {
      if (this.flashcards.length - 1 === index) {
        this.flashcards.pop();
      } else {
        this.flashcards.splice(index, 1);
      }
      this.flashcards.forEach((f, i) => (f.index = i));
    },
  },
};
</script>

<style scoped></style>
