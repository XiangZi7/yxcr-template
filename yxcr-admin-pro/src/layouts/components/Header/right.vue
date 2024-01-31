<script setup lang="ts">
import mittBus from "@/utils/mittBus";
// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
const openDrawer = () => {
  mittBus.emit("openThemeDrawer");
};
</script>
<template>
  <div class="flex gap-2 items-center">
    <i-icon-park-solid:clothes-turtleneck @click="openDrawer"/>
    <span>yxcr</span>
    <el-avatar src="https://admin.spicyboy.cn/assets/gif/avatar-3G4evXKN.gif"/>
  </div>
</template>
