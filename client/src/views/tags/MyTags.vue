<script>
import router from "@/router";
import { mapActions, mapState, mapStores } from "pinia";
import { useTagStore } from "@/store/useTagStore";

export default {
  methods: {
    ...mapActions(useTagStore, ["getUserTags"]),
    newTag() {
      router.push("tags/new");
    },
    openTag(id) {
      router.push(`tags/${id}`);
    },
  },
  computed: {
    ...mapState(useTagStore, ["userTags"]),
  },
  beforeMount() {
    this.getUserTags();
  },
};
</script>

<template>
  <div class="flex flex-column flex-1">
    <Toolbar>
      <template #end>
        <Button
          v-on:click="newTag"
          label="New"
          icon="pi pi-plus"
          class="mr-2"
        />
      </template>
    </Toolbar>
    <DataView :value="userTags" paginator :rows="5" class="">
      <template #list="slotProps">
        <div class="col-12">
          <div
            class="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4"
          >
            <div
              v-on:click="openTag(slotProps.data.id)"
              class="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4"
            >
              <div class="flex flex-row align-items-center gap-3">
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
  //margin-right: 5px;
}
</style>
