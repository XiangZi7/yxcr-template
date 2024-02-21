<script setup lang="ts">
import { addMenu, editMenu, getMenuTable } from "@/api/modules/Menu";
import { FormInstance } from "element-plus";
import {
  acceptParamModel,
  AddOrUpdateState,
} from "@/pages/system/menu/interce";
import { useCloned } from "@vueuse/core";

const dataFormRef = ref();

const typeList = [
  {
    id: 0,
    title: "目录",
  },
  {
    id: 1,
    title: "菜单",
  },
  {
    id: 2,
    title: "按钮",
  },
];

const state = reactive<AddOrUpdateState>({
  visible: false,
  disabled: false,
  dataForm: {
    id: -1,
    parentId: null,
    type: 0,
    visible: 1,
    orderNum: 1,
    icon: "",
    path: "",
    permission: "",
    name: "",
    title: "",
    component: "",
    isKeepAlive: 0,
    redirect: "",
  },
  dataRule: {
    title: [{ required: true, message: "请输入名称", trigger: "blur" }],
    path: [{ required: true, message: "请输入路由菜单路径", trigger: "blur" }],
    component: [
      { required: true, message: "请输入前端组件路径", trigger: "blur" },
    ],
  },
  cascaderOptions: [],
  cascaderProps: {
    value: "id",
    label: "title",
    expandTrigger: "hover",
    checkStrictly: true,
  },
  //菜单栏ID
  selectedMenu: [],
  //标题
  title: "",
});
const {
  visible,
  dataForm,
  dataRule,
  cascaderOptions,
  cascaderProps,
  selectedMenu,
  disabled,
  title,
} = toRefs(state);
const Emits = defineEmits(["ResultOk"]);

// 接受父组件传递过来的菜单参数
function acceptParam(params: acceptParamModel) {
  const { cloned } = useCloned(params);
  // 获取菜单数据
  getMenuTable().then(({ data }) => {
    state.cascaderOptions = data;
  });
  if (
    cloned.value.dataForm.children &&
    cloned.value.dataForm.children.length > 0
  ) {
    // 如果 children 存在且长度大于0，执行删除操作
    delete cloned.value.dataForm.children;
  }
  state.dataForm = cloned.value.dataForm;
  state.selectedMenu = cloned.value.idList;
  state.disabled = cloned.value.disabled;
  state.visible = true;
  state.title = cloned.value.title;
}

const currentName = computed(() => typeList[state.dataForm.type].title);

// 弹出对话框
function showDialog() {
  // 获取菜单数据
  getMenuTable().then(({ data }) => {
    state.cascaderOptions = data;
  });
  state.disabled = false;
  state.visible = true;
  state.title = "新增";
}

// 关闭对话框执行结束回调
function formClose() {
  // 还原数据
  state.dataForm = {
    id: -1,
    parentId: null,
    type: 0,
    visible: 1,
    orderNum: 1,
    icon: "",
    path: "",
    permission: "",
    name: "",
    title: "",
    component: "",
    isKeepAlive: 0,
    redirect: "",
  };
  state.selectedMenu = [];
  state.disabled = false;
  state.title = "";
}

const onSubmit = async (formEl: FormInstance | undefined) => {
  // 检查formEl是否存在，不存在则直接返回
  if (!formEl) return;

  // 尝试
  try {
    const isValid = await formEl.validate(); // 假设validate现在是返回promise的，直接等待它resolve
    if (isValid) {
      const id = state.selectedMenu?.[state.selectedMenu.length - 1] ?? -1;
      let params;
      if (state.dataForm.type != 0) {
        params = {
          ...state.dataForm,
          ...(state.title === "新增"
            ? { id }
            : state.title === "编辑"
              ? { parentId: id }
              : ""),
        };
      } else {
        params = {
          ...state.dataForm,
          ...(state.title === "新增" ? { id } : ""),
        };
      }

      let result; // 声明一个结果变量来储存响应
      // 使用条件运算符来选择执行添加或修改操作
      state.title === "新增"
        ? (result = await addMenu(params))
        : (result = await editMenu(params));

      //消息提醒
      messagePro(result.code, result.data);

      // 如果操作成功，关闭窗口并发出事件
      if (result.code === 200) {
        visible.value = false;
        Emits("ResultOk");
      }
    }
  } catch (error) {
    console.error("提交表单过程中出现错误：", error);
    // 在这里处理错误，例如显示错误消息等
  }
};
defineExpose({ acceptParam, showDialog });
</script>
<template>
  <el-dialog
    v-model="visible"
    :title="title"
    :close-on-click-modal="false"
    @close="formClose"
  >
    <el-form
      ref="dataFormRef"
      :disabled="disabled"
      :model="dataForm"
      :rules="dataRule"
      label-width="110px"
    >
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio
            v-for="(type, index) in typeList"
            :key="index"
            :label="index"
          >
            {{ type.title }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item required :label="currentName + '名称'" prop="title">
        <el-input
          v-model="dataForm.title"
          :placeholder="currentName + '名称'"
        />
      </el-form-item>
      <el-form-item :label="currentName + '英文名称'">
        <el-input
          v-model="dataForm.name"
          :placeholder="currentName + '英文名称'"
        />
      </el-form-item>
      <el-form-item v-if="dataForm.type != 0" label="上级菜单">
        <el-cascader
          v-model="selectedMenu"
          class="w-full"
          :options="cascaderOptions"
          :props="cascaderProps"
          change-on-select
          clearable
          placeholder="请选择上级菜单"
        />
      </el-form-item>
      <el-form-item v-if="dataForm.type == 2" label="授权标识">
        <el-input
          v-model="dataForm.permission"
          placeholder="授权标识:user:list,user:create"
        />
      </el-form-item>
      <el-form-item label="菜单路径" prop="path" required>
        <el-input v-model="dataForm.path" placeholder="菜单路由" />
      </el-form-item>
      <el-form-item label="前端组件" prop="component" required>
        <el-input v-model="dataForm.component" placeholder="前端组件" />
      </el-form-item>
      <el-form-item
        v-if="dataForm.type == 0"
        label="默认跳转地址"
        prop="redirect"
      >
        <el-input v-model="dataForm.redirect" placeholder="默认跳转地址" />
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="排序号">
        <el-input-number v-model="dataForm.orderNum" />
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="图标">
        <select-icon v-model="dataForm.icon" />
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="是否隐藏">
        <el-switch
          v-model="dataForm.visible"
          inline-prompt
          active-text="是"
          inactive-text="否"
          :active-value="0"
          :inactive-value="1"
        />
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="是否缓存路由">
        <el-switch
          v-model="dataForm.isKeepAlive"
          inline-prompt
          active-text="是"
          inactive-text="否"
          :active-value="1"
          :inactive-value="0"
        />
      </el-form-item>
    </el-form>
    <template v-if="!disabled" #footer>
      <el-button type="primary" @click="onSubmit(dataFormRef)">确定</el-button>
      <el-button type="danger" @click="visible = false">取消</el-button>
    </template>
  </el-dialog>
</template>
<style lang="scss" scoped></style>
