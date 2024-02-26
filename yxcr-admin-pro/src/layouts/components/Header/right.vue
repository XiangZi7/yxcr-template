<script setup lang="ts">
import mittBus from "@/utils/mittBus";
import { adminLogout } from "@/api/modules/login";
import { Avatar } from "@element-plus/icons";
import { useFullscreen } from "@vueuse/core";

const { toggle } = useFullscreen();
const router = useRouter();
const userStore = useUserStore();
// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
const openDrawer = () => {
  mittBus.emit("openThemeDrawer");
};

function logout() {
  adminLogout().then(({ code }) => {
    if (code == 200) {
      userStore.setUserInfo({});
      router.replace({ path: "/login" });
    }
  });
}
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
    <span>{{ userStore.userInfo.username }}</span>
    <!--头像-->
    <el-dropdown>
      <el-avatar
        src="https://admin.spicyboy.cn/assets/gif/avatar-3G4evXKN.gif"
      />
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="router.push({ path: '/account/center' })">
            <el-icon>
              <Avatar />
            </el-icon>
            个人中心
          </el-dropdown-item>
          <el-dropdown-item @click="logout">
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
