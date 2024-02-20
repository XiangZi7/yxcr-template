<script setup lang="ts">
import {FormInstance} from "element-plus";
import {useCloned} from "@vueuse/core";
import {
  acceptParamModel,
  AddOrUpdateState,
} from "@/pages/user-manage/interface";

const dataFormRef = ref();
const state = reactive<AddOrUpdateState>({
  dataRule: {
    name: [{required: true, message: "请输入名称", trigger: "blur"}],
  },
  drawerProps: {},
  visible: false,
});
const {visible, dataRule, drawerProps} = toRefs(state);
// const Emits = defineEmits(["ResultOk"]);

// 接受父组件传递过来的参数
function acceptParams(params: acceptParamModel) {
  const {cloned} = useCloned(params);
  state.drawerProps = cloned.value;
  state.visible = true;
}

// 弹出对话框
function showDialog() {
  state.disabled = false;
  state.visible = true;
  state.title = "新增";
}

// 关闭对话框执行结束回调
function formClose() {
  state.disabled = false;
  state.title = "";
}

const onSubmit = async (formEl: FormInstance | undefined) => {
  // 检查formEl是否存在，不存在则直接返回
  if (!formEl) return;

  // 尝试
  try {
    const isValid = await formEl.validate(); // 假设validate现在是返回promise的，直接等待它resolve
    console.log("=>(add-or-update.vue:140) isValid", isValid);
  } catch (error) {
    console.error("提交表单过程中出现错误：", error);
    // 在这里处理错误，例如显示错误消息等
  }
};
defineExpose({acceptParams, showDialog});
</script>
<template>
  <el-dialog
      v-model="visible"
      :title="drawerProps.title"
      :close-on-click-modal="false"
      @close="formClose"
      style="border-radius: 10px"
  >
    <el-form
        ref="dataFormRef"
        :disabled="drawerProps.isView"
        :model="drawerProps.dataForm"
        :rules="dataRule"
        label-width="auto"
    >
      <el-form-item required label="角色" prop="roleName">
        <el-input
            v-model="drawerProps.dataForm.roleName"
            placeholder="请选择角色"
            clearable
        />
      </el-form-item>
      <el-form-item required label="用户名称" prop="username">
        <el-input
            v-model="drawerProps.dataForm.username"
            placeholder="请输入用户名称"
            clearable
        />
      </el-form-item>
      <el-form-item required label="电话号码" prop="phone">
        <el-input
            v-model="drawerProps.dataForm.phone"
            placeholder="请输入电话号码"
            clearable
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
            v-model="drawerProps.dataForm.email"
            placeholder="请输入邮箱"
            clearable
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
