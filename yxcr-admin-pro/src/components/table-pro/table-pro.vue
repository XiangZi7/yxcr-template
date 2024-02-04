<script setup lang="ts">
import { TableColumn } from "@/api/interface";

const tableRef = ref();

export interface ProTableProps {
  columns: TableColumn[]; // 列配置项  ==> 必传
  data?: any[]; // 静态 table data 数据，若存在则不会使用 requestApi 返回的 data ==> 非必传
  pagination?: boolean; // 是否需要分页组件 ==> 非必传（默认为true）
  initParam?: any; // 初始化请求参数 ==> 非必传（默认为{}）
  rowKey?: string; // 行数据的 Key，用来优化 Table 的渲染，当表格数据多选时，所指定的 id ==> 非必传（默认为 id）
  border?: boolean; // 是否带有纵向边框 ==> 非必传（默认为true）
}

// 接受父组件参数，配置默认值
const props = withDefaults(defineProps<ProTableProps>(), {
  columns: () => [],
  requestAuto: true,
  pagination: true,
  initParam: {},
  border: true,
  toolButton: true,
  rowKey: "id",
});

function selectionChange(e) {
  console.log(e);
}
</script>
<template>
  <el-table
    ref="tableRef"
    v-bind="$attrs"
    :data="props.data"
    :border="props.border"
    :row-key="props.rowKey"
    @selection-change="selectionChange"
  >
    <template v-for="item in props.columns" :key="item.id">
      <el-table-column
        v-if="!item.slot"
        :type="item.type"
        :prop="item.prop"
        :label="item.label"
        :fixed="item.fixed || false"
        :align="item.align ?? 'center'"
        :width="item.width ?? ''"
        :show-overflow-tooltip="true"
      ></el-table-column>
      <el-table-column
        v-else
        :type="item.type"
        :prop="item.prop"
        :label="item.label"
        :fixed="item.fixed || false"
        :align="item.align ?? 'center'"
        :width="item.width ?? ''"
      >
        <template #default="scope">
          <slot :name="item.slot" v-bind="scope" />
        </template>
      </el-table-column>
    </template>
  </el-table>
</template>
<style lang="scss" scoped></style>
