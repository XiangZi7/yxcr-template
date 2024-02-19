<script setup lang="ts">
import { MenuEntry } from "@/layouts/components/interface";

interface Props {
  menuData?: MenuEntry[]; // 假设 menuData 是 MenuEntry 类型的数组
}
const props = withDefaults(defineProps<Props>(), {
  menuData: () => [] as MenuEntry[], // 赋予默认值，并断言为空的 MenuEntry 类型数组
});
const { menuData } = toRefs(props);

const router = useRouter();
const tabsStore = useTabsStore();
const handleClickMenu = (subItem: MenuEntry) => {
  router.push(subItem.path);
  const { path, meta, name } = subItem;
  const { title, icon } = meta;
  tabsStore.addTabs({ icon, title, path, name });
};
</script>
<template>
  <template v-for="subItem in menuData" :key="subItem.path">
    <el-sub-menu v-if="subItem.children?.length" :index="subItem.path">
      <template #title>
        <el-icon>
          <component
            :is="subItem.meta.icon"
            v-if="subItem.meta.icon"
          ></component>
        </el-icon>
        <span class="sle">{{ subItem.meta.title }}</span>
      </template>
      <RecursiveMenu :menu-data="subItem.children" />
    </el-sub-menu>
    <el-menu-item
      v-else
      :index="subItem.path"
      @click="handleClickMenu(subItem)"
    >
      <el-icon>
        <component :is="subItem.meta.icon" v-if="subItem.meta.icon"></component>
      </el-icon>
      <template #title>
        <span class="sle">{{ subItem.meta.title }}</span>
      </template>
    </el-menu-item>
  </template>
</template>
