<script>
import { useFlashcardsSetStore } from "@/store/useFlashcardsSetStore";
import { mapActions, mapState } from "pinia";
import router from "@/router";

export default {
  methods: {
    ...mapActions(useFlashcardsSetStore, ["getLikes"]),
    openSet(id) {
      router.push(`/sets/${id}`);
    },
  },
  computed: {
    ...mapState(useFlashcardsSetStore, ["likes"]),
  },
  beforeMount() {
    this.getLikes();
  },
};
</script>

<template>
  <div class="flex flex-column flex-1">
    <DataView :value="likes" paginator :rows="5">
      <template #list="slotProps">
        <div class="col-12">
          <div
            class="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4"
          >
            <div
              v-on:click="openSet(slotProps.data.id)"
              class="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4"
            >
              <div
                class="flex flex-column align-items-center sm:align-items-start gap-3"
              >
                <div class="text-2xl font-bold text-900">
                  {{ slotProps.data.name }}
                </div>
                <div class="text-color-secondary">
                  {{ slotProps.data.questionLanguage.name }} ->
                  {{ slotProps.data.answerLanguage.name }}
                </div>
                <div class="text-color-secondary">
                  {{
                    slotProps.data.description.length > 75
                      ? slotProps.data.description.substring(0, 75) + "..."
                      : slotProps.data.description
                  }}
                </div>
              </div>
              <div
                class="flex sm:flex-column align-items-center sm:align-items-end gap-3 sm:gap-2"
              ></div>
            </div>
          </div>
        </div>
      </template>
    </DataView>
  </div>
</template>

<style scoped>
.box {
  height: 20px;
  width: 20px;
  //margin-right: 5px;
}
</style>
