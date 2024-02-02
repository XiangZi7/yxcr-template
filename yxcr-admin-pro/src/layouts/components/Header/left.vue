<script setup lang="ts">
import Breadcrumb from "./components/Breadcrumb.vue";

const themeStore = useThemeStore();
// 折叠侧边菜单栏
const changeCollapse = () =>
  themeStore.setThemeState("isCollapse", !themeStore.isCollapse);
</script>
<template>
  <div class="flex items-center gap-2">
    <!--红绿灯-->
    <div class="menu-circle" />
    <!--折叠菜单-->
    <div class="flex items-center" @click="changeCollapse">
      <component
        :is="themeStore.isCollapse ? 'expand' : 'fold'"
        class="w-[1.4em] cursor-pointer"
      />
    </div>

    <!--左右返回路由-->
    <div class="flex gap-2 items-center">
      <ArrowLeft class="w-[1.2em] cursor-pointer" />
      <ArrowLeft
        class="w-[1.2em] cursor-pointer"
        style="transform: scaleX(-1)"
      />
    </div>
    <!--面包屑-->
    <div>
      <Breadcrumb v-show="themeStore.breadcrumb" />
    </div>
  </div>
</template>
<style lang="scss" scoped>
.menu-circle {
  width: 15px;
  height: 15px;
  background-color: #f96057;
  border-radius: 50%;
  box-shadow:
    24px 0 #f8ce52,
    48px 0 #5fcf65;
  margin-right: 195px;
  flex-shrink: 0;
}

.el-breadcrumb {
  font-size: 12px;
}
</style>
