<script setup lang="ts">
import { ArrowRight } from "@element-plus/icons-vue";
import { Menu } from "@/utils/interface";

const themeStore = useThemeStore();
const authStore = useAuthStore();
const route = useRoute();
const router = useRouter();
// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
const breadcrumbList = computed(() => {
  let breadcrumbData =
    authStore.breadcrumbListGet[route.matched[route.matched.length - 1].path] ??
    [];
  // 🙅‍♀️不需要首页面包屑可删除以下判断
  if (breadcrumbData[0].path !== "/home/index") {
    breadcrumbData = [
      { path: "/home/index", meta: { icon: "HomeFilled", title: "首页" } },
      ...breadcrumbData,
    ];
  }
  return breadcrumbData;
});
// Click Breadcrumb
const onBreadcrumbClick = (item: Menu.MenuOptions, index: number) => {
  if (index !== breadcrumbList.value.length - 1) router.push(item.path);
};
</script>
<template>
  <div
    :class="[
      'breadcrumb-box mask-image',
      !themeStore.breadcrumbIcon && 'no-icon',
    ]"
  >
    <el-breadcrumb :separator-icon="ArrowRight">
      <transition-group name="breadcrumb">
        <el-breadcrumb-item
          v-for="(item, index) in breadcrumbList"
          :key="item.path"
        >
          <div
            class="el-breadcrumb__inner is-link flex items-center gap-1"
            :class="{ 'item-no-icon': !item.meta.icon }"
            @click="onBreadcrumbClick(item, index)"
          >
            <el-icon
              v-if="item.meta.icon && themeStore.breadcrumbIcon"
              class="breadcrumb-icon"
            >
              <component :is="item.meta.icon"></component>
            </el-icon>
            <span class="breadcrumb-title">{{ item.meta.title }}</span>
          </div>
        </el-breadcrumb-item>
      </transition-group>
    </el-breadcrumb>
  </div>
</template>
<style lang="scss" scoped></style>
