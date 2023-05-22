<template>
  <div class="flex-1 h-full">
    <!--      todo: use a variable when will swap questions and answers-->
    <!--      todo: maybe add icon to identify current side is question or answer maybe icon in the bottom in the center of the cards -->
    <div class="ml-7">
      <h1>{{ store.set.name }}</h1>
      <p class="text-color-secondary">{{ store.set.description }}</p>
    </div>
    <Toolbar class="mx-7">
      <template #end>
        <!--          todo: show only for sets of other users and change heart icon if it's liked-->
        <Button
          v-on:click="likeSet"
          aria-label="Like"
          icon="pi pi-heart"
          class="mr-2"
        />
      </template>
      <template #start>
        <!--          todo: shuffle enabled and disabled-->
        <!--          todo: I can show it as outilined button-->
        <Button
          v-on:click="shuffleCards"
          label="Shuffle cards"
          icon="pi pi-sync"
          class="mr-2"
        />
        <!--          todo: show description when user  -->
        <Button
          v-on:click="swapValues"
          aria-label="Like"
          icon="pi pi-arrow-right-arrow-left"
          class="mr-2"
        />
      </template>
    </Toolbar>
    <Carousel :value="set.flashcards" :numVisible="1" :numScroll="1">
      <template #item="slotProps">
        <div
          v-on:click="showAnswer(slotProps.data)"
          class="border-1 surface-border border-round m-2 text-center py-5 px-3"
        >
          <div class="h-25rem flex align-items-center">
            <!--              todo I can use prime vue scroll panel here-->
            <h4 v-if="slotProps.data.showAnswer" class="mt-0 mb-3 m-auto">
              {{ slotProps.data.answer }}
            </h4>
            <h4 v-if="!slotProps.data.showAnswer" class="mt-0 mb-3 m-auto">
              {{ slotProps.data.question }}
            </h4>
          </div>
        </div>
      </template>
    </Carousel>
    <div class="flex">
      <Button
        aria-label="edit"
        class="ml-7"
        icon="pi pi-file-edit"
        severity="warning"
        outlined
      />
      <Button
        arialabel="delete"
        class="ml-auto mr-7"
        icon="pi pi-trash"
        severity="danger"
        outlined
      />
    </div>
  </div>
</template>
<script>
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { mapActions, mapState } from "pinia";

export default {
  data() {
    return {
      store: useFlashcardsSetStore(),
      flashcardsSet: {},
    };
  },
  created() {
    // get id from url
    const setId = this.$route.params.id;
    this.getById(setId);
  },

  methods: {
    ...mapActions(useFlashcardsSetStore, ["getById"]),
    showAnswer(card) {
      card.showAnswer = !card.showAnswer;
    },
    likeSet() {},
    shuffleCards() {},
    swapValues() {},
  },
  computed: {
    ...mapState(useFlashcardsSetStore, ["set"]),
  },
};
</script>

<style scoped></style>
