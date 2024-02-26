<script setup lang="ts">
import AddOrUpdate from "./add-or-update.vue";
import { Delete, EditPen, View, Refresh } from "@element-plus/icons-vue";
import {
  userList,
  addUser,
  editUser,
  deleteUser,
  RefreshUserPwd,
} from "@/api/modules/User";
import { userFormInline } from "@/pages/user-manage/user/interface";

const fileUrl = import.meta.env.VITE_API_MINIO;
const addOrUpdateRef = ref();
const userListTableRef = ref();
const formInline = reactive<userFormInline>({});
// 数据获取与分页hook
const {
  tableData,
  currentPage,
  pageSize,
  pageTotal,
  fetchData,
  handleSizeChange,
  handleCurrentChange,
} = useUserListPagination(userList);

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
function openDrawer(title: string, row?: userFormInline) {
  const params = {
    title,
    isView: title === "查看",
    dataForm: { ...row },
    api: title === "新增" ? addUser : title === "编辑" ? editUser : undefined,
  };
  addOrUpdateRef.value?.acceptParams(params);
}

/**
 * 删除
 * @param ids
 */
// 调用操作确认hook来删除用户
async function deleteAccount(row: userFormInline) {
  const action = () => deleteUser([row.id as number]);
  await confirmAndExecute(`确定要删除【${row.username}】嘛？`, action);
  await fetchData();
}

/**
 * 批量删除
 * @param ids
 */
const ids = ref<number[]>([]);
const batchDelete = ref(true);

// 多选
function onSelectSome(e: [{ id: number }]) {
  ids.value = e.map((item) => item.id);
  if (ids.value.length > 0) {
    batchDelete.value = false;
  } else {
    batchDelete.value = true;
  }
}

async function handleBatchDelete() {
  const action = () => deleteUser(ids.value);
  await confirmAndExecute(`确定要删除嘛？`, action);
  await fetchData();
}

/**
 * 重置密码
 * @param id
 * @constructor
 */
async function RefreshPwd(row: userFormInline) {
  const action = () => RefreshUserPwd(row.id as number);
  await confirmAndExecute(`确定要重置【${row.username}】的密码吗？`, action);
  await fetchData();
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
      <el-form-item label="用户名称">
        <el-input
          v-model="formInline.username"
          placeholder="请输入用户名称"
          clearable
        />
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input
          v-model="formInline.phone"
          placeholder="请输入手机号码"
          clearable
        />
      </el-form-item>
      <el-form-item label="状态">
        <yx-select-pro
          v-model="formInline.status"
          is-view
          dict="user_status"
          placeholder="请选择状态"
        />
      </el-form-item>
      <el-form-item label="角色">
        <yx-select-pro
          v-model="formInline.roleName"
          is-view
          dict="user_role"
          placeholder="请选择角色"
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
        <el-table-column prop="name" label="用户名称" width="180">
          <template #default="{ row }">
            <div class="flex items-center gap-2">
              <el-avatar :src="fileUrl + row.avatar" />
              <div>{{ row.username }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="roleName" label="角色" />
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="row.status == 1 ? 'success' : 'danger'"
              >{{ row.status == 1 ? "活动" : "禁用" }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号码">
          <template #default="scope">
            {{ scope.row.phone }}
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" />
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
              :icon="Refresh"
              @click="RefreshPwd(row)"
              >重置密码
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
