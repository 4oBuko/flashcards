<script>
import { mapActions, mapState } from "pinia";
import { useSearchStore } from "@/store/useSearchStore";
import router from "@/router";
export default {
  data() {
    return {
      searchValue: "",
      searchResult: [],
    };
  },
  methods: {
    ...mapActions(useSearchStore, ["searchByName"]),
    search() {
      if (this.searchValue.length !== 0) {
        this.searchByName(this.searchValue);
      }
    },
    open(element) {
      if (element.type === "tag") {
        router.push(`/tags/${element.id}`);
      } else {
        router.push(`/sets/${element.id}`);
      }
    },
  },
  computed: {
    ...mapState(useSearchStore, ["result"]),
    searchItems() {},
  },
  watch: {
    result(newResult, old) {
      const items = [];
      this.result.sets.forEach((set) => {
        set.type = "set";
        items.push(set);
      });
      this.result.tags.forEach((tag) => {
        tag.type = "tag";
        items.push(tag);
      });
      this.searchResult = items;
    },
  },
};
</script>

<template>
  <div class="flex flex-column flex-1 mx-1">
    <div class="p-inputgroup">
      <InputText placeholder="Search" v-model="searchValue" />
      <Button v-on:click="search" icon="pi pi-search" />
    </div>
    <DataView :value="searchResult" paginator :rows="5">
      <template #list="slotProps">
        <div class="col-12">
          <div
            class="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4"
          >
            <div
              v-on:click="open(slotProps.data)"
              class="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4"
            >
              <div
                class="flex flex-column align-items-center sm:align-items-start gap-3"
                v-if="slotProps.data.type === 'set'"
              >
                <div class="text-2xl font-bold text-900">
                  {{ slotProps.data.name }}
                </div>
                <div class="text-color-secondary">
                  {{ slotProps.data.questionLanguage.name }} ->
                  {{ slotProps.data.answerLanguage.name }}
                </div>
              </div>
              <div class="flex flex-row align-items-center gap-3" v-else>
                <div
                  class="box"
                  :style="{ 'background-color': slotProps.data.color.code }"
                ></div>
                <div class="text-2xl font-bold text-900">
                  {{ slotProps.data.name }}
                </div>
                <div class="text-color-secondary">
                  {{
                    `contains ${slotProps.data.sets.length} ${
                      slotProps.data.sets.length === 1 ? "set" : "sets"
                    }`
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
}
</style>
