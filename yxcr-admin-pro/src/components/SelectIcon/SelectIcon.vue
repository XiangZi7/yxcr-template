<template>
  <el-input v-model="ModelValue" @click="dialogTableVisible = true"/>
  <el-dialog v-model="dialogTableVisible" title="请选择图标" width="80%">
    <el-input
      v-model="searchIcon"
      class="w-50 m-2"
      placeholder="请输入Icon名称"
      :prefix-icon="Search"
    />
    <el-scrollbar height="400px">
      <div style="display: flex; flex-wrap: wrap">
        <div
          v-for="(name, index) in filteredIcons"
          :key="index"
          :index="index"
          class="iconType"
          :class="currentIconName === name.name ? 'colorPrimary' : ''"
          @click="currentIconName = name.name"
        >
          <component :is="name" style="width: 2rem; height: 2rem"></component>
          <span style="margin-top: 10px">{{ name.name }}</span>
        </div>
      </div>
    </el-scrollbar>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogOk">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import {Search} from "@element-plus/icons";

const searchIcon = ref<string>("")
// const Emits = defineEmits(["dialogOk"]);
const ModelValue = defineModel<string | number>();
// dialog Icon选项
const dialogTableVisible = ref(false);
const currentIconName = ref(ModelValue.value);
const dialogOk = () => {
  ModelValue.value = currentIconName.value; // 更新模型值
  // Emits("dialogOk", currentIconName.value);  // 触发事件
  dialogTableVisible.value = false;
};

// 类型定义
interface IconEntry {
  [key: string]: any;
}

// 计算属性，用于根据搜索条件过滤图标列表
const filteredIcons = computed(() => {
  const icons = Object.entries(ElementPlusIconsVue).reduce<IconEntry>((acc, [key, value]) => {
    if (!searchIcon.value || key.toLowerCase().includes(searchIcon.value.toLowerCase())) {
      acc[key] = value;
    }
    return acc;
  }, {});

  return icons;
});
defineExpose({
  dialogTableVisible,
  // currentIconName,
});
</script>
<style lang="scss">
.colorPrimary {
  background-color: var(--el-color-primary);
  color: white;
}

.iconType {
  width: 100px;
  cursor: pointer;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
