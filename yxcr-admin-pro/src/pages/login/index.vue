<template>
  <div class="container">
    <div class="card rounded-xl overflow-hidden">
      <div
          :class="{
          'cover': true,
          'cover-right': isRegistering,
          'cover-left': !isRegistering
        }"
      >
        <!-- 封面内容 -->
        <div class="cover-content">
          <div class="toggle-area">
            <el-button type="primary" @click="toggle">
              {{ isRegistering ? '已有账号？去登录' : '新用户？创建账户' }}
            </el-button>
            <p class="toggle-text">
              {{ isRegistering ? '返回登录界面' : '加入我们，探索更多精彩' }}
            </p>
          </div>
        </div>
      </div>
      <div class="form-wrapper bg-white">
        <!-- 登录表单 -->
        <div class="login-form" v-show="isRegistering">
          <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="auto">
            <el-form-item prop="phone">
              <el-input v-model="loginForm.phone" placeholder="请输入电话号码"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="w-full" @click="handleLogin">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="register-form" v-show="!isRegistering">
          <!-- 注册表单 -->
          <el-form :model="registerForm" :rules="registerRules" ref="registerFormRef" label-width="auto">
            <el-form-item prop="phone">
              <el-input v-model="registerForm.phone" placeholder="请输入电话号码"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="name">
              <el-input v-model="registerForm.name" placeholder="请输入名称"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="w-full" @click="handleRegister">注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">

const isRegistering = ref(false); // 初始状态，false代表显示登录表单
// 切换状态
function toggle() {
  isRegistering.value = !isRegistering.value;
}

// 登录数据
const loginFormRef = ref(null);
// 注册数据
const registerFormRef = ref(null);


const loginForm = ref({
  phone: '',
  password: '',
});

const registerForm = ref({
  phone: '',
  password: '',
  email: '',
  name: '',
});

// 定义电话号码和邮箱的正则表达式
const phoneRegex = /^1[3-9]\d{9}$/;
const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;

const loginRules = {
  phone: [
    {required: true, message: '请输入电话号码', trigger: 'blur'},
    {pattern: phoneRegex, message: '请输入正确的电话号码', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
};

const registerRules = {
  phone: [
    {required: true, message: '请输入电话号码', trigger: 'blur'},
    {pattern: phoneRegex, message: '请输入正确的电话号码', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  email: [
    {required: true, message: '请输入邮箱地址', trigger: 'blur'},
    {pattern: emailRegex, message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
  ],
  name: [
    {required: true, message: '请输入名称', trigger: 'blur'}
  ],
};

const handleLogin = () => {
  loginFormRef.value.validate((valid) => {
    if (valid) {
      // 假设这里有一个登录函数你需要调用
      alert('登录成功');
    } else {
      alert('请检查你的表单项');
    }
  });
};

const handleRegister = () => {
  registerFormRef.value.validate((valid) => {
    if (valid) {
      // 假设这里有一个注册函数你需要调用
      alert('注册成功');
    } else {
      alert('请检查你的表单项');
    }
  });
};

</script>

<style scoped lang="scss">
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.card {
  position: relative;
  width: 60%;
  height: 50%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.cover {
  z-index: 1; /* 如果有必要可以更改这个值，但确保它适合你的层级结构 */
  position: absolute;
  width: 50%;
  height: 100%;
  transition: transform 0.5s ease-in-out;
}

/* 渐变背景，可根据喜好调整渐变颜色和方向 */
.cover::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: url(https://www.transparenttextures.com/patterns/cubes.png), linear-gradient(to right top, #cf4af3, #e73bd7, #f631bc, #fd31a2, #ff3a8b, #ff4b78, #ff5e68, #ff705c, #ff8c51, #ffaa49, #ffc848, #ffe652);
  opacity: 0.9; /* 渐变背景的透明度，根据需要调整 */
  border-radius: inherit;
  pointer-events: none;
}

.toggle-area {
  /* 将按钮和文字容器限制宽度，并居中显示 */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 10px; /* 间隙 */
  height: 100%;
}

.toggle-text {
  color: #ffffff; /* 文字颜色，可根据背景颜色更改 */
  font-size: 0.9rem; /* 文字大小，可根据需要调整 */
}

.cover-right {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  transform: translateX(100%);
}

.cover-left {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  transform: translateX(0%);
}

.cover-content {
  width: 100%;
  height: 100%;
  position: absolute;
}

.form-wrapper {
  display: flex;
  height: 100%;
}

.login-form,
.register-form {
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 登录表单样式 */
.login-form {
  transition: transform 0.5s ease-in-out;
}

/* 注册表单样式 */
.register-form {
  transition: transform 0.5s ease-in-out;
  transform: translateX(100%); /* 初始状态下隐藏在右侧 */
}
</style>
