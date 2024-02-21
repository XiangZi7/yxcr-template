<script setup>
// const state = reactive({
//   list: [],
// })
// const {
// list,
// }= toRefs(state)
</script>
<template>
  <div class="overflow-hidden">
    <div class="bgs shadow-lg rounded-lg p-5">
      <div class="text-center mt-2">
        <img class="w-24 h-24 rounded-full mx-auto" src="https://admin.spicyboy.cn/assets/gif/avatar-3G4evXKN.gif"
             alt="Profile">
        <h1 class="text-lg text-gray-900">张三</h1>
        <p class="text-gray-600">前端开发工程师 | 爱好者</p>
      </div>
    </div>
    <div class="container mx-auto p-4">
      <div class="card-grid">
        <!-- 单个卡片 -->
        <div class="bg-white rounded overflow-hidden shadow-lg">
          <img class="w-full h-32 sm:h-48 object-cover" src="" alt="封面图">
          <div class="px-6 py-4">
            <h3 class="font-bold text-xl mb-2">卡片标题</h3>
            <p class="text-gray-700 text-base">
              描述文本，简短介绍卡片内容。
            </p>
          </div>
          <div class="px-6 py-4">
            <a class="text-blue-500 hover:text-blue-700" href="#">查看更多</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.bgs {
  transition: width 0.5s;
  background-position: 50%;
  background-size: cover;
  background-repeat: no-repeat;
  box-sizing: border-box;
  background-image: url("@/assets/eyes1.jpg");
}
</style>
