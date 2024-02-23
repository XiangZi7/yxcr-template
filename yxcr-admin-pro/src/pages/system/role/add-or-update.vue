<script setup lang="ts">
import { useCloned } from "@vueuse/core";
import {
  acceptParamModel,
  AddOrUpdateState,
} from "@/pages/user-manage/interface";
import { getMenuTables, getRoleMenuByRoleId } from "@/api/modules/System";
import { FormInstance } from "element-plus";

const menuListTreeRef = ref(null);
const dataFormRef = ref();
const state = reactive<AddOrUpdateState>({
  dataRule: {
    name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
  },
  drawerProps: {},
  visible: false,
  menuList: [],
  menuListTreeProps: {
    label: "title",
    children: "children",
  },
});
const { visible, dataRule, drawerProps, menuList, menuListTreeProps } =
  toRefs(state);
const Emits = defineEmits(["ResultOk"]);

onMounted(() => {
  getMenuTables().then(({ data }) => {
    state.menuList = arrayToTree(data);
  });
});

// 接受父组件传递过来的参数
async function acceptParams(params: acceptParamModel) {
  state.drawerProps = params;
  const { cloned } = useCloned(params.dataForm);
  state.drawerProps.dataForm = cloned.value;
  state.visible = true;

  if (params.title == "新增") return;
  const { data } = await getRoleMenuByRoleId(params.dataForm.id);
  menuListTreeRef.value?.setCheckedKeys(data);
}

// 弹出对话框
function showDialog() {
  state.drawerProps.isView = false;
  state.visible = true;
  state.drawerProps.title = "新增";
}

// 关闭对话框执行结束回调
function formClose() {
  state.drawerProps.isView = false;
  state.drawerProps.title = "";
  menuListTreeRef.value?.setCheckedKeys([]);
}

const onSubmit = async (formEl: FormInstance | undefined) => {
  // 检查formEl是否存在，不存在则直接返回
  if (!formEl) return;
  // 尝试
  try {
    const isValid = await formEl.validate(); // 假设validate现在是返回promise的，直接等待它resolve
    if (isValid) {
      state.drawerProps
        .api({
          roles: state.drawerProps.dataForm,
          idList: menuListTreeRef.value?.getCheckedKeys(),
        })
        .then(({ code, data }) => {
          messagePro(code, data);
          if (code == 200) {
            state.visible = false;
            Emits("ResultOk");
          }
        });
    }
  } catch (error) {
    console.error("提交表单过程中出现错误：", error);
    // 在这里处理错误，例如显示错误消息等
  }
  menuListTreeRef.value?.getCheckedKeys();
};
defineExpose({ acceptParams, showDialog });
</script>
<template>
  <el-dialog
    v-model="visible"
    :title="drawerProps.title"
    :close-on-click-modal="false"
    style="border-radius: 10px"
    @close="formClose"
  >
    <el-form
      ref="dataFormRef"
      :disabled="drawerProps.isView"
      :model="drawerProps.dataForm"
      :rules="dataRule"
      label-width="auto"
    >
      <el-form-item label="角色名称" prop="name">
        <el-input
          v-model="drawerProps.dataForm.name"
          placeholder="请输入角色名称"
          clearable
        />
      </el-form-item>

      <el-form-item label="描述" prop="description">
        <el-input
          v-model="drawerProps.dataForm.description"
          type="textarea"
          placeholder="请输入描述"
          clearable
        />
      </el-form-item>
      <el-form-item label="授权">
        <el-tree
          ref="menuListTreeRef"
          :data="menuList"
          :props="menuListTreeProps"
          node-key="id"
          show-checkbox
        />
      </el-form-item>
    </el-form>
    <template v-if="!drawerProps.isView" #footer>
      <el-button type="primary" @click="onSubmit(dataFormRef)">确定</el-button>
      <el-button type="danger" @click="visible = false">取消</el-button>
    </template>
  </el-dialog>
</template>
<style lang="scss" scoped></style>
