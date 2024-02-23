<script setup lang="ts">
import { AdminULogin } from "@/api/modules/login";
import { adminLogin } from "@/pages/login/interface";
import { initDynamicRouter } from "@/router/modules/dynamicRouter";

const state = reactive<adminLogin>({
  isCurrent: "login",
  showPopup: false,
  loginForm: {
    phone: 22212,
    password: 666666,
  },
});
const { isCurrent, loginForm } = toRefs(state);

const modelValue = defineModel();
// 用户数据
const userStore = useUserStore();
// 路由
const router = useRouter();

function ckBtn(params: string) {
  state.isCurrent = params;
}

function validateForm(): boolean {
  if (!loginForm.value.phone) {
    messagePro(201, "hone number is not valid.");
    return false;
  }
  if (!loginForm.value.password) {
    messagePro(201, "Password must be at least 6 characters.");
    return false;
  }
  return true;
}

function handleSubmit(event: SubmitEvent, Flag: string) {
  console.log("=>(index.vue:39) Flag", Flag);
  event.preventDefault(); // 阻止默认提交行为
  if (!validateForm()) {
    return;
  }
  // 添加数据
  AdminULogin(state.loginForm).then(async ({ code, data, message }) => {
    if (code == 200) {
      messagePro(code, `欢迎您！${data.username}`);
      userStore.setUserInfo(data);
      await initDynamicRouter();
      router.push({ path: "home/index" });
    } else {
      messagePro(code, data, message);
    }
  });
  // 关闭登录窗口
  modelValue.value = false;
}

function loginBtn() {}
</script>
<template>
  <div class="login">
    <div
      class="popup-container"
      :class="{ visible: modelValue }"
      @click="modelValue = false"
    ></div>
    <div class="popup-content">
      <div
        id="container"
        class="container"
        :class="{ 'right-panel-active': isCurrent !== 'login' }"
      >
        <div class="form-container sign-up-container">
          <form @submit="handleSubmit($event, 'sign-up')">
            <h1 style="color: #000">注册账号</h1>
            <input
              v-model="loginForm.username"
              type="text"
              placeholder="Username"
            />
            <input v-model="loginForm.phone" placeholder="Phone" />
            <input v-model="loginForm.email" type="email" placeholder="Email" />
            <input
              v-model="loginForm.password"
              type="password"
              placeholder="Password"
            />
            <button type="submit" :disabled="true">暂不开放注册</button>
          </form>
        </div>
        <div class="form-container sign-in-container">
          <form @submit="handleSubmit($event, 'login')">
            <h1 style="color: #000">登录</h1>
            <input v-model="loginForm.phone" placeholder="Phone" />
            <input
              v-model="loginForm.password"
              type="password"
              placeholder="Password"
            />

            <div id="captcha-div"></div>

            <a href="#">忘记密码?</a>
            <button type="submit" @click="loginBtn">登录</button>
          </form>
        </div>
        <div class="overlay-container">
          <div class="overlay">
            <div class="overlay-panel overlay-left">
              <h1>欢迎回来!</h1>
              <p>为了与我们保持联系，请使用您的个人信息登录</p>
              <button id="signIn" class="ghost" @click="ckBtn('login')">
                登录
              </button>
            </div>
            <div class="overlay-panel overlay-right">
              <h1>欢迎!</h1>
              <p>输入您的个人资料，与我们一起开始旅程</p>
              <button id="signUp" class="ghost" @click="ckBtn('Sign')">
                注册
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
@use "./login.scss";
</style>
