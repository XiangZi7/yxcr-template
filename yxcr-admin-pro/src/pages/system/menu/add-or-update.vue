<script setup lang="ts">
import {getMenuTable} from "@/api";
import {FormInstance} from "element-plus";
import {AddOrUpdateState, FormSchema} from "@/pages/system/menu/interce";

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
  dataForm: {
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
    redirect: ""
  },
  dataRule: {
    title: [{required: true, message: "请输入名称", trigger: "blur"}],
    path: [{required: true, message: "请输入路由菜单路径", trigger: "blur"}],
    component: [{required: true, message: "请输入前端组件路径", trigger: "blur"}],
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
});
const {
  visible,
  dataForm,
  dataRule,
  cascaderOptions,
  cascaderProps,
  selectedMenu,
} = toRefs(state);
const props = defineProps({
  title: {
    type: String,
    default: "",
  },
});
onMounted(() => {
  getMenuTable().then(({data}) => {
    state.cascaderOptions = data;
  });
});

function acceptParam(params:FormSchema) {
  state.dataForm = params
  state.selectedMenu = params.idList
  state.visible = true;
}

function showDialog() {
  state.visible = true
}

const onSubmit = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid) => {
    if (valid) {
      httpPost("system/menu", {...state.dataForm, id: state.selectedMenu[state.selectedMenu.length - 1]}).then(({data}) => {
        console.log(data)
      })
    }
  });
};
defineExpose({acceptParam, showDialog});
</script>
<template>
  <el-dialog
      v-model="visible"
      class="component-add-or-update"
      :title="props.title"
      :close-on-click-modal="false"
  >
    <el-form
        ref="dataFormRef"
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
      <el-form-item required label="目录名称" prop="title">
        <el-input v-model="dataForm.title" placeholder="目录名称"/>
      </el-form-item>
      <el-form-item label="目录英文名称">
        <el-input v-model="dataForm.name" placeholder="目录英文名称"/>
      </el-form-item>
      <el-form-item label="上级菜单" v-if="dataForm.type!=0">
        <el-cascader
            v-model="selectedMenu"
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
      <el-form-item
          label="菜单路径"
          prop="path"
          required
      >
        <el-input v-model="dataForm.path" placeholder="菜单路由"/>
      </el-form-item>
      <el-form-item
          label="前端组件"
          prop="component"
          required
      >
        <el-input v-model="dataForm.component" placeholder="前端组件"/>
      </el-form-item>
      <el-form-item
          v-if="dataForm.type==0"
          label="默认跳转地址"
          prop="redirect"
      >
        <el-input v-model="dataForm.redirect" placeholder="默认跳转地址"/>
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="排序号">
        <el-input-number v-model="dataForm.orderNum"/>
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="图标">
        <select-icon v-model="dataForm.icon"/>
      </el-form-item>
      <el-form-item v-if="dataForm.type != 2" label="是否隐藏">
        <el-switch
            v-model="dataForm.visible"
            inline-prompt
            active-text="是"
            inactive-text="否"
            :active-value="1"
            :inactive-value="0"
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
    <template #footer>
      <el-button type="primary" @click="onSubmit(dataFormRef)">确定</el-button>
      <el-button type="danger" @click="visible=false">取消</el-button>
    </template>
  </el-dialog>
</template>
<style lang="scss" scoped></style>
