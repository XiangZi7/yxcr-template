<template>
  <el-switch v-model="themeStore.isDark" inline-prompt :active-icon="Sunny" :inactive-icon="Moon" @change="switchDark" />
</template>

<script setup lang="ts" name="SwitchDark">
import { Sunny, Moon } from "@element-plus/icons-vue";
const { switchDark } = useTheme();
const themeStore = useThemeStore();
</script>
