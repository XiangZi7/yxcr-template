<script setup lang="ts">
import AddOrUpdate from "./add-or-update.vue";
import {getMenuTable, systemMenuDelete} from "@/api";
import {MenuData, MenuDataState} from "@/api/interface";
import {Delete, View, Edit} from "@element-plus/icons";

const addOrUpdateRef = ref();
const state = reactive<MenuDataState>({
  list: [],
  column: [
    {prop: "title", label: "名称", align: "left"},
    {prop: "icon", label: "图标", slot: "icon"},
    {prop: "type", label: "类型", slot: "type"},
    // {prop: "name", label: "菜单英文名称"},
    {prop: "orderNum", label: "菜单排序"},
    {prop: "visible", label: "是否隐藏", slot: "visible"},
    {prop: "path", label: "菜单路由"},
    {prop: "permission", label: "授权表示"},
    {
      prop: "operation",
      slot: "operation",
      label: "操作",
      fixed: "right",
      width: 250,
    },
    // {prop: "isKeepAlive", label: "是否缓存", slot: 'isKeepAlive'},
  ],
});
const {list, column} = toRefs(state);

onMounted(() => {
  getMenuTable().then(({data}) => {
    state.list = data;
  });
});

// 查看
function openDrawer(title: string, row: MenuData) {
  const idList = findParentIds(state.list, row.id);
  if (idList && idList.length > 0) {
    if (typeof row.id === "number") {
      idList.push(row.id);
    }
  }
  addOrUpdateRef.value.acceptParam({
    title,
    dataForm: row,
    idList,
    disabled: title == "查看" ? true : false,
  });
}

// 删除目录
function deleteData(id: number) {
  systemMenuDelete(id).then(({data, code}) => {
    messagePro(code, data)
    ResultOk()
  })
}

// 更新新增或修改数据
function ResultOk() {
  getMenuTable().then(({data}) => {
    state.list = data;
  });
}
</script>

<template>
  <div class="w-full">
    <div class="mb-3">
      <el-button type="primary" @click="addOrUpdateRef.showDialog()"
      >新增
      </el-button
      >
    </div>
    <table-pro :data="list" :columns="column">
      <!--icon-->
      <template #icon="{ row }">
        <el-icon v-if="row.icon">
          <component :is="row.icon"/>
        </el-icon>
      </template>
      <!--类型-->
      <template #type="{ row }">
        <yx-select-pro v-model="row.type" :is-view="false" dict="menu_type"/>
      </template>
      <!-- 是否隐藏-->
      <template #visible="{ row }">
        <yx-select-pro v-model="row.visible" :is-view="false" dict="y_n"/>
      </template>
      <template #operation="{ row }">
        <el-button
            type="primary"
            link
            :icon="View"
            @click="openDrawer('查看', row)"
        >
          查看
        </el-button>
        <el-button
            type="primary"
            link
            :icon="Edit"
            @click="openDrawer('编辑', row)"
        >编辑
        </el-button>
        <el-button
            type="primary"
            link
            :icon="Delete"
            @click="deleteData(row.id)"
        >删除
        </el-button>
      </template>
    </table-pro>
    <add-or-update ref="addOrUpdateRef" @result-ok="ResultOk"/>
  </div>
</template>
<style lang="scss" scoped></style>
