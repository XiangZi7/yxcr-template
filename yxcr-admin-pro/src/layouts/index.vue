<script setup lang="ts">
import ThemeDrawer from "./components/ThemeDrawer/index.vue";
import LayoutVertical from "./LayoutVertical/index.vue";
import LayoutClassic from "./LayoutClassic/index.vue";
import LayoutTransverse from "./LayoutTransverse/index.vue";
import LayoutColumns from "./LayoutColumns/index.vue";
import { LayoutType } from "@/stores/interface";

const LayoutComponents: Record<LayoutType, Component> = {
  vertical: LayoutVertical,
  classic: LayoutClassic,
  transverse: LayoutTransverse,
  columns: LayoutColumns,
};
const themeStore = useThemeStore();
const layout = computed(() => themeStore.layout);
</script>
<template>
  <component :is="LayoutComponents[layout]" />
  <ThemeDrawer />
</template>
