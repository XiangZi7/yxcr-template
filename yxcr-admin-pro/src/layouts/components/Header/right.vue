<script setup lang="ts">
import mittBus from "@/utils/mittBus";
import { Avatar } from "@element-plus/icons";
import { useFullscreen } from "@vueuse/core";
const { toggle } = useFullscreen();

// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
const openDrawer = () => {
  mittBus.emit("openThemeDrawer");
};
</script>
<template>
  <div class="flex gap-3 items-center">
    <!-- 全屏-->
    <el-icon size="20" @click="toggle">
      <full-screen />
    </el-icon>
    <!--消息-->
    <el-icon size="20">
      <bell />
    </el-icon>
    <!--选项-->
    <el-icon size="20" @click="openDrawer">
      <operation />
    </el-icon>

    <!--    <i-icon-park-solid:clothes-turtleneck @click="openDrawer"/>-->
    <span>yxcr</span>
    <!--头像-->
    <el-dropdown>
      <el-avatar
        src="https://admin.spicyboy.cn/assets/gif/avatar-3G4evXKN.gif"
      />
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>
            <el-icon>
              <Avatar />
            </el-icon>
            个人中心
          </el-dropdown-item>
          <el-dropdown-item>
            <el-icon>
              <switch-button />
            </el-icon>
            退出
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style lang="scss" scoped>
.el-icon {
  cursor: pointer;
}
</style>
