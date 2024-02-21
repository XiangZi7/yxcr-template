<script setup lang="ts">
import "vue-cropper/dist/index.css";
import { VueCropper } from "vue-cropper";
import { ElMessage } from "element-plus";
// 资源链接
const fileUrl = import.meta.env.VITE_API_MINIO;
// 头像裁剪器ref
const cropper = ref();
// 双向绑定数据
const ModelValue = defineModel();
const state = reactive({
  options: {
    img: ModelValue.value,
    autoCropWidth: 300, // 默认生成截图框宽度 默认容器的 80%
    autoCropHeight: 200, // 默认生成截图框高度 默认容器的 80%
    outputType: "png", // 裁剪生成图片的格式 jpeg, png, webp
    autoCrop: true, // 是否默认生成截图框
    fixedBox: true, // 固定截图框大小
  },
  dialogVisible: false,
  previews: {}, //预览图片
});
const { options, dialogVisible, previews } = toRefs(state);

// 实时裁剪回调函数
function realTime(data) {
  state.previews = data;
}

// 上传图片处理
const beforeUpload = (rawFile) => {
  if (rawFile.type.indexOf("image/") == -1) {
    ElMessage.error("请上传图片类型文件!");
    return false;
  }
  if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("文件大小不能超过2MB!");
    return false;
  }
  const reader = new FileReader();
  reader.readAsDataURL(rawFile);
  reader.onload = () => {
    state.options.img = reader.result;
  };
};

//图片提交
function uploadImg() {
  cropper.value.getCropBlob((blobData) => {
    let contentType = blobData.type; // 获取blob的类型
    let file = new File([blobData], "filename.jpg", { type: contentType }); // 创建File对象
    let formData = new FormData();
    formData.append("file", file);
    httpPost("/upload", formData, {
      "Content-Type": "multipart/form-data",
    }).then(({ data, code }) => {
      if (code != 200) return;
      ModelValue.value = data;
      state.dialogVisible = false;
    });
  });
}

// 修改图片大小 正数为变大 负数变小
const changeScale = (num) => {
  num = num || 1;
  cropper.value.changeScale(num);
};
// 向左边旋转90度
const rotateLeft = () => {
  cropper.value.rotateLeft();
};
// 向右边旋转90度
const rotateRight = () => {
  cropper.value.rotateRight();
};
</script>
<template>
  <div
    class="avatar-container"
    style="cursor: pointer"
    @click="dialogVisible = true"
  >
    <!--  默认显示-->
    <img v-if="ModelValue" :src="fileUrl + ModelValue" alt="" />
    <div
      v-else
      style="
        display: flex;
        flex-direction: column;
        align-items: center;
        border: 1px dashed var(--el-border-color-darker);
      "
      class="bg-[var(--el-fill-color-lighter)] p-3 rounded"
    >
      <el-icon class="upload-icon el-icon-plus avatar-uploader-icon">
        <Plus />
      </el-icon>
      请上传封面
    </div>
    <el-dialog
      v-model="dialogVisible"
      title="裁剪图片"
      width="800px"
      append-to-body
    >
      <el-row>
        <el-col :span="12" style="height: 300px">
          <vue-cropper
            ref="cropper"
            :img="options.img"
            :info="true"
            :auto-crop="options.autoCrop"
            :auto-crop-width="options.autoCropWidth"
            :auto-crop-height="options.autoCropHeight"
            :fixed-box="options.fixedBox"
            :output-type="options.outputType"
            @real-time="realTime"
          />
        </el-col>
        <el-col :span="12" style="height: 300px">
          <div class="preview-box">
            <img :src="previews.url" :style="previews.img" />
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 12px">
        <el-col :span="12">
          <el-row>
            <el-col :span="8">
              <el-upload
                action="#"
                :http-request="() => {}"
                :before-upload="beforeUpload"
                :show-file-list="false"
              >
                <el-button>选择</el-button>
              </el-upload>
            </el-col>
            <el-col :span="4">
              <el-button icon="Plus" @click="changeScale(1)"></el-button>
            </el-col>
            <el-col :span="4">
              <el-button icon="Minus" @click="changeScale(-1)"></el-button>
            </el-col>
            <el-col :span="4">
              <el-button icon="RefreshLeft" @click="rotateLeft"></el-button>
            </el-col>
            <el-col :span="4">
              <el-button icon="RefreshRight" @click="rotateRight"></el-button>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="4" :offset="8">
          <el-button type="primary" @click="uploadImg">提 交</el-button>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>
<style lang="scss" scoped>
.cover {
  width: 100%;
  border-radius: 10px;
}

.avatar-container {
  position: relative;
  display: flex;
  justify-content: center;
}

.preview-box {
  position: absolute;
  top: 50%;
  transform: translate(20%, -50%);
  width: 300px;
  height: 200px;
  border: 1px solid #ccc;
  overflow: hidden;
}
</style>
