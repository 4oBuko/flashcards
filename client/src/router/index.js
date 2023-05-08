import Login from "@/views/Login.vue";
import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Register from "@/views/Register.vue";
import FlashcardsSet from "@/views/sets/FlashcardsSet.vue";
import CreateSet from "@/views/sets/CreateSet.vue";
import CreateTag from "@/views/tags/CreateTag.vue";
import Tag from "@/views/tags/Tag.vue";
import EditTag from "@/views/tags/EditTag.vue";
import EditSet from "@/views/sets/EditSet.vue";
import Profile from "@/views/user/Profile.vue";
import EditUser from "@/views/user/EditUser.vue";
import MySets from "@/views/sets/MySets.vue";
import MyTags from "@/views/tags/MyTags.vue";
import Search from "@/views/Search.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", name: "home", component: Home },
    { path: "/login", name: "login", component: Login },
    { path: "/register", name: "register", component: Register },
    { path: "/users/:id", name: "user-profile", component: Profile },
    {
      path: "/settings/account",
      name: "account-settings",
      component: EditUser,
    },

    { path: "/sets", name: "sets", component: MySets },
    { path: "/sets/new", name: "create-set", component: CreateSet },
    { path: "/sets/:id", name: "flashcards-set", component: FlashcardsSet },
    { path: "/sets/:id/edit", name: "edit-set", component: EditSet },

    { path: "/tags", name: "tags", component: MyTags },
    { path: "/tags/new", name: "create-tag", component: CreateTag },
    { path: "/tags/:id", name: "view-tag", component: Tag },
    { path: "/tags/:id/edit", name: "edit-tag", component: EditTag },

    { path: "/search", name: "search", component: Search },

    { path: "/*", redirect: "/" },
  ],
});

router.beforeEach((to, from) => {
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("token");
  if (authRequired && !loggedIn) {
    return "/login";
  }
  if ((to.name === "login" || to.name === "register") && loggedIn) return "/";
});

export default router;
