<template>
  <div ref="echartRef" :style="{ width: width, height: height }"></div>
</template>

<script setup lang="ts">
import * as echarts from "echarts";

// Props
const props = defineProps({
  option: Object,
  width: {
    type: String,
    default: "600px",
  },
  height: {
    type: String,
    default: "400px",
  },
});

// EChart 的 DOM 引用
const echartRef = ref(null);
let chartInstance = null;

// 在组件挂载时初始化 ECharts 实例
onMounted(() => {
  if (echartRef.value) {
    chartInstance = echarts.init(echartRef.value);
    if (props.option) {
      chartInstance.setOption(props.option);
    }
  }
});

// 监听 option 变化重新设置 option
watch(
  () => props.option,
  (newOption) => {
    if (chartInstance && newOption) {
      chartInstance.setOption(newOption);
    }
  },
  { deep: true },
);

// 组件卸载时释放 ECharts 实例
onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>
