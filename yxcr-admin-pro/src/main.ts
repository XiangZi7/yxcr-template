import { createApp } from "vue";
import "./style/index.scss";
import App from "./App.vue";
import router from "@/router";
import store from "@/stores";
// vue i18n
import I18n from "@/languages/index";
// element plus
import "element-plus/dist/index.css";
import ElementPlus from "element-plus";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";

const app = createApp(App);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
app.use(router);
app.use(store);
app.use(ElementPlus);
app.use(I18n);
app.mount("#app");
