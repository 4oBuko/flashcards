import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";
import { createPinia } from "pinia";
import PrimeVue from "primevue/config";

import "primevue/resources/themes/lara-dark-purple/theme.css";
import "primevue/resources/primevue.css";
import "primeflex/primeflex.css";
import "primeicons/primeicons.css";
import "@/assets/app.css";
import Button from "primevue/button";
import InputText from "primevue/inputtext";
import Password from "primevue/password";
import Checkbox from "primevue/checkbox";
import ToggleButton from "primevue/togglebutton";
import Tree from "primevue/tree";
import Menu from "primevue/menu";
import Toast from "primevue/toast";
import Badge from "primevue/badge";
import NavigationBar from "@/views/NavigationBar.vue";
import InputSwitch from "primevue/inputswitch";
import Dropdown from "primevue/dropdown";
import Textarea from "primevue/textarea";

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.use(PrimeVue, { ripple: true });

// ui components
app.component("Button", Button);
app.component("InputText", InputText);
app.component("Password", Password);
app.component("Checkbox", Checkbox);
app.component("ToggleButton", ToggleButton);
app.component("Tree", Tree);
app.component("Toast", Toast);
app.component("Menu", Menu);
app.component("InputSwitch", InputSwitch);
app.component("Dropdown", Dropdown);
app.component("Textarea", Textarea);

const customComponents = ["Toast", "Tree"];
app.config.compilerOptions.isCustomElement = (tag) => {
  return customComponents.includes(tag);
};

app.mount("#app");
