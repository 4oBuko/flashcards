<template>
  <Toast />
  <ConfirmPopup></ConfirmPopup>
  <div class="flex-1 h-full">
    <!--      todo: use a variable when will swap questions and answers-->
    <!--      todo: maybe add icon to identify current side is question or answer maybe icon in the bottom in the center of the cards -->
    <div class="ml-7">
      <h1>{{ store.set.name }}</h1>
      <p class="text-color-secondary">{{ store.set.description }}</p>
    </div>
    <Toolbar class="mx-7">
      <template #end>
        <Button
          v-if="!liked"
          v-on:click="pressLike"
          aria-label="Like"
          icon="pi pi-heart"
          class="mr-2"
        />
        <Button
          v-else
          v-on:click="pressLike"
          aria-label="Like"
          icon="pi pi-heart-fill"
          class="mr-2"
          outlined
        />
      </template>
      <template #start>
        <!--          todo: shuffle enabled and disabled-->
        <!--          todo: I can show it as outilined button-->
        <!--        todo: just add answer/question text on top of the card-->
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
    <Carousel :value="set.flashcards" :numVisible="1" :numScroll="1" circular>
      <template #item="slotProps">
        <div
          v-on:click="showAnswer(slotProps.data)"
          class="border-1 surface-border border-round m-2 text-center py-5 px-3 surface-300"
        >
          <div
            class="h-25rem flex align-items-center justify-content-center flex-column"
            v-if="slotProps.data.showAnswer"
          >
            <h5 class="mt-0 mb-3 m-auto text-color-secondary">
              {{ "answer" }}
            </h5>
            <h4 class="mt-0 mb-3 m-auto">
              {{ slotProps.data.answer }}
            </h4>
          </div>
          <div
            class="h-25rem flex align-items-center justify-content-center flex-column"
            v-else
          >
            <!--              todo I can use prime vue scroll panel here-->
            <h5 class="mt-0 mb-3 m-auto text-color-secondary">
              {{ "question" }}
            </h5>
            <h4 class="mt-0 mb-3 m-auto">
              {{ slotProps.data.question }}
            </h4>
          </div>
        </div>
      </template>
    </Carousel>
    <div v-if="set.userId === user.id" class="flex">
      <Button
        aria-label="edit"
        class="ml-7"
        icon="pi pi-file-edit"
        severity="warning"
        v-on:click="editSet"
        outlined
      />
      <Button
        arialabel="delete"
        class="ml-auto mr-7"
        icon="pi pi-trash"
        severity="danger"
        v-on:click="confirm1"
        outlined
      />
    </div>
  </div>
</template>
<script>
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { mapActions, mapState } from "pinia";
import { useUserStore } from "@/store/useUserStore";
import { useConfirm } from "primevue/useconfirm";
import { useToast } from "primevue/usetoast";
import router from "@/router";

export default {
  data() {
    return {
      store: useFlashcardsSetStore(),
      flashcardsSet: {},
      confirm: useConfirm(),
      toast: useToast(),
    };
  },
  beforeMount() {
    // get id from url
    const setId = this.$route.params.id;
    this.getById(setId);
  },

  methods: {
    ...mapActions(useFlashcardsSetStore, ["getById", "delete"]),
    ...mapActions(useUserStore, ["likeSet", "unlikeSet", "loadUser"]),
    showAnswer(card) {
      this.set.flashcards.forEach((c) => {
        if (JSON.stringify(card) !== JSON.stringify(c)) {
          c.showAnswer = false;
        } else {
          card.showAnswer = !card.showAnswer;
        }
      });
    },
    pressLike() {
      if (this.liked) {
        this.unlikeSet(this.set.id);
      } else {
        this.likeSet(this.set.id);
      }
    },
    shuffleCards() {
      this.set.flashcards.sort((a, b) => Math.random() - 0.5);
      this.set.flashcards.forEach((card) => {
        card.showAnswer = false;
      });
    },
    swapValues() {
      this.set.flashcards.forEach((card) => {
        let temp = card.question;
        card.question = card.answer;
        card.answer = temp;
      });
    },
    deleteSet() {
      this.delete(this.set.id);
      router.push("/sets");
    },
    editSet() {
      router.push(`/sets/${this.set.id}/edit`);
    },
    confirm1(event) {
      this.confirm.require({
        target: event.currentTarget,
        message: "Do you want to delete this tag?",
        icon: "pi pi-info-circle",
        acceptClass: "p-button-danger",
        accept: () => {
          this.toast.add({
            severity: "info",
            summary: "Confirmed",
            detail: "Record deleted",
            life: 300,
          });
          this.deleteSet();
        },
        reject: () => {
          this.toast.add({
            severity: "error",
            summary: "Rejected",
            detail: "You have rejected",
            life: 3000,
          });
        },
      });
    },
  },
  computed: {
    // todo: don't show edit buttons if user is not the author
    ...mapState(useFlashcardsSetStore, ["set"]),
    ...mapState(useUserStore, ["user"]),
    liked() {
      return this.user.likedSets.includes(this.set.id);
    },
  },
};
</script>

<style scoped></style>
