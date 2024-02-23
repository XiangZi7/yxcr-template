<script setup lang="ts">
import AddOrUpdate from "./add-or-update.vue";
import { Delete, EditPen, View, Refresh } from "@element-plus/icons-vue";
import { addRole, editRole, deleteRole } from "@/api/modules/System";
import { RoleList } from "@/api/modules/System";

const addOrUpdateRef = ref();
const userListTableRef = ref();
const formInline = reactive({});
// 数据获取与分页hook
const {
  tableData,
  currentPage,
  pageSize,
  pageTotal,
  fetchData,
  handleSizeChange,
  handleCurrentChange,
} = useUserListPagination(RoleList);

// 操作确认hook
const { confirmAndExecute } = useConfirmAction();

onMounted(() => {
  fetchData();
});

/**
 * @description 查找功能
 */
const onSubmit = () => {
  fetchData();
};

/**
 * 抽屉
 * @param title 标题
 * @param row 当前行数据
 */
function openDrawer(title: string, row) {
  const params = {
    title,
    isView: title === "查看",
    dataForm: { ...row },
    api: title === "新增" ? addRole : title === "编辑" ? editRole : undefined,
  };
  addOrUpdateRef.value?.acceptParams(params);
}

/**
 * 删除
 * @param ids
 */
// 调用操作确认hook来删除
async function deleteAccount(row) {
  const action = () => deleteRole([row.id]);
  await confirmAndExecute(`确定要删除【${row.name}】嘛？`, action);
  fetchData();
}

/**
 * 批量删除
 * @param ids
 */
const ids = ref([]);
const batchDelete = ref(true);

// 多选
function onSelectSome(e: []) {
  ids.value = e.map((item) => item.id);
  if (ids.value.length > 0) {
    batchDelete.value = false;
  } else {
    batchDelete.value = true;
  }
}

async function handleBatchDelete() {
  const action = () => deleteRole(ids.value);
  await confirmAndExecute(`确定要删除嘛？`, action);
  fetchData();
}

/**
 * 刷新数据
 */
function getTableList() {
  fetchData();
}

function ResultOk() {
  fetchData();
}
</script>
<template>
  <div>
    <!--搜索-->
    <el-form
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
      label-width="auto"
    >
      <el-form-item label="角色名称">
        <el-input
          v-model="formInline.name"
          placeholder="请输入角色名称"
          clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查找</el-button>
      </el-form-item>
    </el-form>

    <!--  添加 -->
    <div class="flex justify-between m-2">
      <div>
        <el-button type="primary" @click="openDrawer('新增')">新增</el-button>
        <el-button
          type="primary"
          :disabled="batchDelete"
          @click="handleBatchDelete"
          >批量删除
        </el-button>
      </div>
      <div>
        <el-button :icon="Refresh" circle @click="getTableList" />
      </div>
    </div>
    <div>
      <!-- 主表 -->
      <el-table
        ref="userListTableRef"
        row-key="id"
        :data="tableData"
        @selection-change="onSelectSome"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="角色名称" width="180" />
        <el-table-column prop="description" label="描述" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column label="操作" fixed="right" align="center" width="310">
          <template #default="{ row }">
            <el-button
              type="primary"
              link
              :icon="View"
              @click="openDrawer('查看', row)"
              >查看
            </el-button>
            <el-button
              type="primary"
              link
              :icon="EditPen"
              @click="openDrawer('编辑', row)"
              >编辑
            </el-button>
            <el-button
              type="primary"
              link
              :icon="Delete"
              @click="deleteAccount(row)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="justify-end mt-3"
        :current-page="currentPage"
        :page-size="pageSize"
        :page-sizes="[10, 25, 50, 100]"
        :total="pageTotal"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <add-or-update ref="addOrUpdateRef" @result-ok="ResultOk" />
  </div>
</template>
