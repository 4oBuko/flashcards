import Login from "@/views/Login.vue";
import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Register from "@/views/Register.vue";
import FlashcardsSet from "@/views/FlashcardsSet.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", name: "home", component: Home },
    { path: "/login", name: "login", component: Login },
    { path: "/register", name: "register", component: Register },
    { path: "/sets", name: "sets", component: FlashcardsSet },
    { path: "/*", redirect: "/" },
  ],
});

router.beforeEach((to, from) => {
  const publicPages = ["/login", "/register"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("token");
  if (authRequired && !loggedIn) return "/login";
  if ((to.name === "login" || to.name === "register") && loggedIn) return "/";
});

export default router;
