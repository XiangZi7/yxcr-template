<script setup>
import {Gdata} from './data.ts'
</script>
<template>
  <div>
    <div class="bgs rounded-lg shadow-lg mb-6">
      <div class="text-center p-2 mt-2">
        <img
            class="w-24 h-24 rounded-full mx-auto"
            src="@/assets/2.png"
            alt="Profile"
        />
        <h1 class="text-lg text-gray-900">张三</h1>
        <p class="text-gray-600">前端开发工程师 | 爱好者</p>
      </div>
    </div>
    <div class="container">
      <a :href="item.url" target="_blank" class="flex justify-center items-center" v-for="item in Gdata" :key="item.cover">
        <div class="card max-w-sm mx-auto bg-white rounded-lg shadow-md">
          <img
              :src="item.cover"
              :alt="item.title" class="w-full h-64 object-cover rounded-t-lg">
          <div class="px-4">
            <h5 class="text-lg font-semibold my-4">{{ item.title }}</h5>
            <p class="text-sm text-gray-600 mt-1 line-clamp">{{ item.describe }}</p>
          </div>
          <div class="card-overlay hidden">
            <div class="text-center cursor-pointer">
              <p class="text-xl font-semibold flex items-center gap-2">
                <component :is="item.icon"/>
                {{ item.overlayTitle }}
              </p>
              <p class="text-md">
                {{ item.overlayDescribe }}
              </p>
            </div>
          </div>
        </div>
      </a>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.line-clamp {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  text-overflow: ellipsis;
}
.bgs {
  background-position: 50%;
  background-size: cover;
  background-repeat: no-repeat;
  box-sizing: border-box;
  background-image: url("@/assets/eyes1.jpg");
}

.container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 15px;
}

.card {
  position: relative;
  overflow: hidden;
}

.card img {
  width: 100%;
  height: 200px;
  transition: transform 0.5s ease;
}

.card:hover img {
  transform: scale(1.05);
}

.card-overlay {
  transition: opacity 0.5s ease, visibility 0.5s ease;
  opacity: 0;
  visibility: hidden;
  position: absolute;
  inset: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  padding: 1rem;
  box-sizing: border-box;
}

.card:hover .card-overlay {
  opacity: 1;
  visibility: visible;
}
</style>
