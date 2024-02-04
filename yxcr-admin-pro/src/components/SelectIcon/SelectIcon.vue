<template>
  <el-input v-model="ModelValue" @click="dialogTableVisible = true" />
  <el-dialog v-model="dialogTableVisible" title="请选择图标" width="80%">
    <el-scrollbar height="400px">
      <div style="display: flex; flex-wrap: wrap">
        <div
          v-for="(name, index) in ElementPlusIconsVue"
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

const Emits = defineEmits(["dialogOk"]);
const ModelValue = defineModel<string | number>();
// dialog Icon选项
const dialogTableVisible = ref(false);
const currentIconName = ref();
const dialogOk = () => {
  // console.log(currentIconName.value);
  ModelValue.value = currentIconName.value;
  Emits("dialogOk", currentIconName.value);
  dialogTableVisible.value = false;
};

defineExpose({
  dialogTableVisible,
  currentIconName,
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
