<script>
import { mapActions, mapState } from "pinia";
import { useTagStore } from "@/store/useTagStore";
import { useUserStore } from "@/store/useUserStore";
import { useConfirm } from "primevue/useconfirm";
import { useToast } from "primevue/usetoast";
import router from "@/router";

export default {
  data() {
    return {
      confirm: useConfirm(),
      toast: useToast(),
    };
  },
  beforeMount() {
    const tagId = this.$route.params.id;
    this.getTag(tagId);
  },
  methods: {
    ...mapActions(useTagStore, ["getTag", "delete"]),
    ...mapActions(useUserStore, ["likeTag", "unlikeTag", "load"]),
    shuffleCards() {},
    swapValues() {},
    showAnswer(card) {
      card.showAnswer = !card.showAnswer;
    },
    pressLike() {
      if (this.liked) {
        this.unlikeTag(this.tag.id);
      } else {
        this.likeTag(this.tag.id);
      }
    },
    deleteTag() {
      this.delete(this.tag.id);
      router.push("/tags");
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
          this.deleteTag();
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
    ...mapState(useTagStore, ["tag"]),
    ...mapState(useUserStore, ["user"]),
    liked() {
      return this.user.likedTags.includes(this.tag.id);
    },
  },
};
</script>

<template>
  <Toast />
  <ConfirmPopup></ConfirmPopup>
  <div class="flex-1 h-full">
    <!--      todo: use a variable when will swap questions and answers-->
    <!--      todo: maybe add icon to identify current side is question or answer maybe icon in the bottom in the center of the cards -->
    <div class="ml-7">
      <h1>{{ tag.name }}</h1>
    </div>
    <Toolbar class="mx-7">
      <template #end>
        <!--          todo: show only for sets of other users and change heart icon if it's liked-->
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
        <Button
          v-on:click="shuffleCards"
          label="Shuffle cards"
          icon="pi pi-sync"
          class="mr-2"
        />
        <!--          todo: show description when user  -->
        <Button
          @click="confirm1"
          aria-label="Like"
          icon="pi pi-arrow-right-arrow-left"
          class="mr-2"
        />
      </template>
    </Toolbar>
    <Carousel :value="tag.flashcards" :numVisible="1" :numScroll="1" circular>
      <template #item="slotProps">
        <div
          v-on:click="showAnswer(slotProps.data)"
          class="border-1 surface-border border-round m-2 text-center py-5 px-3 surface-300"
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
        v-on:click="confirm1"
      />
    </div>
  </div>
</template>

<style scoped></style>
