<script setup lang="ts">
import MoreButton from "./components/MoreButton.vue";

import { TabsPaneContext, TabPaneName } from "element-plus";

const route = useRoute();
const router = useRouter();
const tabsStore = useTabsStore();
const themeStore = useThemeStore();

const tabsIcon = computed(() => themeStore.tabsIcon);
const tabsMenuList = computed(() => tabsStore.tabsMenuList);

const tabsMenuValue = ref(route.fullPath);

// 监听路由的变化（防止浏览器后退/前进不变化 tabsMenuValue）
watch(
  () => route.fullPath,
  () => {
    if (route.meta.isFull) return;
    tabsMenuValue.value = route.fullPath;
    const tabsParams = {
      icon: route.meta.icon,
      title: route.meta.title,
      path: route.fullPath,
      name: route.name,
    };
    tabsStore.addTabs(tabsParams);
  },
  { immediate: true },
);

function tabClick(tabItem: TabsPaneContext) {
  const fullPath = tabItem.props.name;
  router.push(fullPath);
}

function tabRemove(fullPath: TabPaneName) {
  console.log(fullPath);
  // tabStore.removeTabs(fullPath as string, fullPath == route.fullPath);
}
</script>
<template>
  <div class="tabs-box mb-2 rounded-xl">
    <div class="tabs-menu">
      <el-tabs
        v-model="tabsMenuValue"
        type="card"
        @tab-click="tabClick"
        @tab-remove="tabRemove"
      >
        <el-tab-pane
          v-for="item in tabsMenuList"
          :key="item.path"
          :label="item.title"
          :name="item.path"
          :closable="item.title != '首页'"
        >
          <template #label>
            <el-icon v-show="item.icon && tabsIcon" class="tabs-icon">
              <component :is="item.icon" />
            </el-icon>
            {{ item.title }}
          </template>
        </el-tab-pane>
      </el-tabs>
      <MoreButton />
    </div>
  </div>
</template>
<style lang="scss" scoped>
@use "index";
</style>
