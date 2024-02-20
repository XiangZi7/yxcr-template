<script setup lang="ts">
import en from "element-plus/es/locale/lang/en";
import zhCn from "element-plus/es/locale/lang/zh-cn";

// init theme
const { initTheme } = useTheme();
initTheme();

const themeStore = useThemeStore();

// element language
const locale = computed(() => {
  if (themeStore.language == "zh") return zhCn;
  if (themeStore.language == "en") return en;
  return getBrowserLang() == "zh" ? zhCn : en;
});
// element assemblySize
const assemblySize = computed(() => themeStore.assemblySize);
</script>

<template>
  <el-config-provider :locale="locale" :size="assemblySize">
    <router-view />
  </el-config-provider>
</template>
