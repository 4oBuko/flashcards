<template>
  <p>Question</p>
  <input
    type="text"
    name="cardQuestion"
    v-model="this.question"
    id="card-question"
  />

  <p>Answer</p>
  <input type="text" name="cardAnswer" v-model="this.answer" id="card-answer" />
  <br />
</template>

<script>
export default {
  name: "FlashcardForm",
  props: ["question", "answer"],
};
</script>

<style scoped></style>
