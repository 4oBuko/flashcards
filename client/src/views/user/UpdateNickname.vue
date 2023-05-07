<template></template>
<input type="text" name="currentNickname" id="currentNickname" readonly :value="store.user.nickname">
<input type="text" name="newNickname" id="newNickname" v-model="newNickname" v-on:change="">
<button>update</button>
<button>cancel</button>
<script>
import {userStore} from "@/store/userStore";

export default {
    created() {
        this.store.loadUser();
    },
    data() {
        return {
            store: userStore(),
            newNickname: "",
            isAvailable: ""
        }
    },
    setName: "UpdateNickname",
    methods: {
        checkAvailability() {

        }
    },
};
</script>

<style scoped></style>
