<script>
import { mapActions, mapState } from "pinia";
import { useSearchStore } from "@/store/useSearchStore";

export default {
  data() {
    return {
      searchValue: "",
    };
  },
  methods: {
    ...mapActions(useSearchStore, ["searchByName"]),
    search() {
      if (this.searchValue.length !== 0) {
        this.searchByName(this.searchValue);
      }
    },
    open(id) {},
  },
  computed: {
    ...mapState(useSearchStore, ["result"]),
  },
};
</script>

<template>
  <div class="flex flex-column flex-1 mx-1">
    <div class="p-inputgroup">
      <InputText placeholder="Search" v-model="searchValue" />
      <Button v-on:click="search" icon="pi pi-search" />
    </div>
    <DataView :value="result.sets" paginator :rows="5">
      <template #list="slotProps">
        <div class="col-12">
          <div
            class="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4"
          >
            <div
              v-on:click="open(slotProps.data.id)"
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

<style scoped></style>
