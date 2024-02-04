<script setup lang="ts">
import { getDict } from "@/api";

interface dictModel {
  value: string;
  label: string;
}

const state = reactive({
  options: [] as dictModel[],
});

const { options } = toRefs(state);
const ModelValue = defineModel<string | number>();

const props = defineProps({
  dict: {
    type: String,
    default: "",
  },
  isView: {
    type: Boolean,
    default: false,
  },
});
const { dict, isView } = toRefs(props);
onMounted(() => {
  getDict(dict?.value).then(({ data }) => {
    state.options = data;
  });
});
</script>
<template>
  <div>
    <div v-if="isView">
      <el-select v-model="ModelValue">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <div v-else>
      <span>{{ options.find((o) => o.value === ModelValue)?.label }}</span>
    </div>
  </div>
</template>
<style lang="scss" scoped></style>
