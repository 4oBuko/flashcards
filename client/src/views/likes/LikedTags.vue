<script>
import { mapActions, mapState } from "pinia";
import router from "@/router";
import { useTagStore } from "@/store/useTagStore";

export default {
  methods: {
    ...mapActions(useTagStore, ["getLikes"]),
    openTag(id) {
      router.push(`/tags/${id}`);
    },
  },
  computed: {
    ...mapState(useTagStore, ["likes"]),
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
