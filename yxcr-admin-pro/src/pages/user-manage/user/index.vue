<script setup lang="ts">
import AddOrUpdate from "./add-or-update.vue";
import {Delete, EditPen, View} from "@element-plus/icons-vue";
import {userList} from "@/api";

const addOrUpdateRef = ref();
const userListTableRef = ref();
const formInline = reactive({});
//
const tableData = ref([]);
const onSubmit = () => {
  userList(formInline).then(({data}) => {
    tableData.value = data.records;
    pageTotal.value = data.total;
  });
};

function onSelectSome(e: string) {
  console.log(e);
}

onMounted(() => {
  let params = {
    pageNum:currentPage.value,
    pageSize:pageSize.value
  }
  userList(params).then(({data}) => {
    tableData.value = data.records;
    pageTotal.value = data.total;
  });
});

function openDrawer(title: string, row) {
  const params = {
    title,
    isView: title === "查看",
    dataForm: {...row},
    // api: title === "新增" ? addUser : title === "编辑" ? editUser : undefined,
  };
  addOrUpdateRef.value?.acceptParams(params);
}

function deleteAccount(row) {
  console.log(row);
}

/**
 * @description 分页
 */
const currentPage = ref(1);
const pageSize = ref(10);
const pageTotal = ref(0);
const disabledPage = false;

// 改变分页大小
function handleSizeChange() {
}

// 改变当前页数
function handleCurrentChange() {
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

    <div>
      <!-- 主表 -->
      <el-table
          ref="userListTableRef"
          :data="tableData"
          style="width: 100%"
          @selection-change="onSelectSome"
      >
        <el-table-column type="selection" width="55"/>
        <el-table-column prop="name" label="用户名称" width="180">
          <template #default="{ row }">
            <div class="flex items-center gap-2">
              <el-avatar :src="row.avatar"/>
              <div>{{ row.username }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="roleName" label="角色"/>
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="row.status==1? 'success' :'danger'">{{ row.status == 1 ? '活动' : '禁用' }}</el-tag>
            <!--            <el-switch-->
            <!--              v-model="row.status"-->
            <!--              :active-value="1"-->
            <!--              :inactive-value="0"-->
            <!--            />-->
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号码">
          <template #default="scope">
            {{ scope.row.phone }}
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间"/>
        <el-table-column label="操作" fixed="right" align="center" width="220">
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
          :disabled="disabledPage"
          :total="pageTotal"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
    <add-or-update ref="addOrUpdateRef"/>
  </div>
</template>
<style lang="scss" scoped></style>
