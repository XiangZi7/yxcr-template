<script setup lang="ts">
const state = reactive({
  list: [],
  column: [
    {prop: "title", label: "名称"},
    {prop: "icon", label: "图标", slot: "icon"},
    {prop: "path", label: "菜单路由"},
    {prop: "name", label: "菜单英文名称"},
    {prop: "type", label: ""},
    {prop: "orderNum", label: "序列号"},
    {prop: "visible", label: "是否隐藏", slot: "visible"},
    {prop: "permission", label: "授权表示"},
    {prop: "isKeepAlive", label: "是否缓存"},
  ],
});
const {list, column} = toRefs(state);

onMounted(() => {
  httpGet("system/table").then((res) => {
    state.list = res.data;
  });
});
</script>

<template>
  <div>
    <el-table :data="list" :tree-props="{ children: 'children' }" row-key="id">
      <template v-for="item in column" :key="item.id">
        <el-table-column
            v-if="!item.slot"
            :type="item.type"
            :prop="item.prop"
            :label="item.label"
            :fixed="item.fixed || false"
            :align="item.align ?? 'center'"
            :width="item.width ?? ''"
            :show-overflow-tooltip="true"
        >
        </el-table-column>

        <el-table-column
            v-else
            :prop="item.prop"
            :label="item.label"
            :fixed="item.fixed || false"
            :width="item.width || ''"
            :align="item.align || 'center'"
        >
          <template #default="{ row }">
            <template v-if="item.slot=='icon'">
              <el-icon>
                <component :is="row.icon"/>
              </el-icon>
            </template>
            <template v-if="item.slot=='visible'">
              <yx-select-pro :is-view="false" dict="y_n" v-model="row.visible"/>
            </template>
          </template>
        </el-table-column>
      </template>
    </el-table>
  </div>
</template>
<style lang="scss" scoped></style>
