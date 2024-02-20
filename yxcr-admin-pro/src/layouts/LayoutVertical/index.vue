<script setup lang="ts">
import ToolBarLeft from "@/layouts/components/Header/left.vue";
import ToolBarRight from "@/layouts/components/Header/right.vue";
import RecursiveMenu from "@/layouts/components/Aside/RecursiveMenu.vue";
import Tabs from "@/layouts/components/Tabs/index.vue";
import { storeToRefs } from "pinia";

const route = useRoute();
const themeStore = useThemeStore();
const { tabs } = storeToRefs(themeStore);

const auth = useAuthStore();
// 折叠菜单
const isCollapse = computed(() => themeStore.isCollapse);
// 当前菜单
const activeMenu = computed(
  () => (route.meta.activeMenu ? route.meta.activeMenu : route.path) as string,
);
// 手风琴模式
const accordion = computed(() => themeStore.accordion);
// 菜单栏
const MenuBar = computed(() => auth.authMenuList);
</script>
<template>
  <el-container class="w-full h-full" style="min-width: 600px">
    <el-header
      class="bg-[var(--el-header-bg-color)] shadow"
      style="border-bottom: 1px solid var(--el-border-color-light)"
    >
      <div
        class="flex justify-between items-center h-full text-[var(--el-header-text-color)]"
      >
        <ToolBarLeft />
        <ToolBarRight />
      </div>
    </el-header>
    <el-container class="mt-2">
      <el-aside style="width: auto" class="ml-2">
        <div
          :style="{ width: isCollapse ? '65px' : '210px' }"
          class="theme-border shadow rounded-xl overflow-hidden transition-all"
        >
          <el-scrollbar>
            <el-menu
              :router="false"
              :default-active="activeMenu"
              :collapse="isCollapse"
              :unique-opened="accordion"
              :collapse-transition="false"
            >
              <recursive-menu :menu-data="MenuBar" />
            </el-menu>
          </el-scrollbar>
        </div>
      </el-aside>
      <div class="flex flex-col flex-1 ml-3 mr-3 mb-2 overflow-auto">
        <Tabs v-show="tabs" class="bg-[var(--el-bg-color)]" />
        <el-main class="bg-[var(--el-bg-color)] shadow rounded-xl theme-border">
          <router-view v-slot="{ Component, route }">
            <transition appear name="fade-transform" mode="out-in">
              <component :is="Component" :key="route.fullPath" />
            </transition>
          </router-view>
        </el-main>
      </div>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.theme-border {
  border: 1px solid var(--el-border-color-light);
}

.el-main {
  box-sizing: border-box;
  overflow-x: hidden;
}
/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.2s;
}
.fade-transform-enter-from {
  opacity: 0;
  transition: all 0.2s;
  transform: translateX(-30px);
}
.fade-transform-leave-to {
  opacity: 0;
  transition: all 0.2s;
  transform: translateX(30px);
}
</style>
