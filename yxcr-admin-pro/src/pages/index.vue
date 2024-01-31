<script setup lang="ts">
// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
const themeStore = useThemeStore();

const { switchDark } = useTheme();

const isDarkRef = ref(themeStore.isDark);

function onAddDarkChange() {
  themeStore.setThemeState("isDark", !themeStore.isDark);
  switchDark();
}
</script>
<template>
  <div class="flex justify-between">
    <div>test22</div>
    <div>
      <el-button type="primary">默认</el-button>
      <el-switch v-model="isDarkRef" @change="onAddDarkChange" />
    </div>
  </div>
</template>
<style lang="scss" scoped></style>
