import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";
import { createPinia } from "pinia";
import PrimeVue from "primevue/config";

// import "primevue/resources/themes/lara-dark-purple/theme.css";
import "primevue/resources/themes/lara-light-purple/theme.css";
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
import InputSwitch from "primevue/inputswitch";
import Dropdown from "primevue/dropdown";
import Textarea from "primevue/textarea";
import Carousel from "primevue/carousel";
import DataView from "primevue/dataview";
import Toolbar from "primevue/toolbar";
import Avatar from "primevue/avatar";
import Divider from "primevue/divider";
import ConfirmPopup from "primevue/confirmpopup";
import ConfirmDialog from "primevue/confirmdialog";
import ConfirmationService from "primevue/confirmationservice";
import ToastService from "primevue/toastservice";
import DialogService from "primevue/dialogservice";
import ScrollPanel from "primevue/scrollpanel";
import MultiSelect from "primevue/multiselect";

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.use(PrimeVue, { ripple: true });
app.use(ConfirmationService);
app.use(ToastService);
app.use(DialogService);
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
app.component("Carousel", Carousel);
app.component("DataView", DataView);
app.component("Toolbar", Toolbar);
app.component("Avatar", Avatar);
app.component("Toast", Toast);
app.component("ConfirmDialog", ConfirmDialog);
app.component("ConfirmPopup", ConfirmPopup);
app.component("MultiSelect", MultiSelect);

const customComponents = ["Toast", "Tree"];
app.config.compilerOptions.isCustomElement = (tag) => {
  return customComponents.includes(tag);
};

app.mount("#app");
