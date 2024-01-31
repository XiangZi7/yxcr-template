<script setup lang="ts">
import ToolBarLeft from "@/layouts/components/Header/left.vue";
import ToolBarRight from "@/layouts/components/Header/right.vue";

const route = useRoute();
const themeStore = useThemeStore();

// 折叠菜单
const isCollapse = computed(() => themeStore.isCollapse);
// 当前菜单
const activeMenu = computed(
  () => (route.meta.activeMenu ? route.meta.activeMenu : route.path) as string,
);
// 手风琴模式
const accordion = computed(() => themeStore.accordion);
</script>
<template>
  <el-container class="min-w-[600px] w-full h-full">
    <el-header class="bg-[var(--el-bg-color)] shadow" style="border-bottom: 1px solid var(--el-border-color-light);">
      <div
        class="flex justify-between items-center h-full text-[var(--el-header-text-color)]"
      >
        <ToolBarLeft/>
        <ToolBarRight/>
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
              <el-sub-menu index="1">
                <template #title>
                  <el-icon>
                    <Message/>
                  </el-icon>
                  <span class="overflow-hidden overflow-ellipsis text-nowrap">
                    导航一
                  </span>
                </template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
              </el-sub-menu>
            </el-menu>
          </el-scrollbar>
        </div>
      </el-aside>
      <el-main class="bg-[var(--el-bg-color)] shadow rounded-xl ml-3 mr-3 mb-2 theme-border">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.theme-border {
  border: 1px solid var(--el-border-color-light);
}
</style>
