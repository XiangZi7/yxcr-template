<template>
  <el-dropdown trigger="click" :teleported="false">
    <div class="more-button">
      <el-icon><ArrowDownBold /></el-icon>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>
          <el-icon>
            <Refresh />
          </el-icon>
          刷新
        </el-dropdown-item>
        <el-dropdown-item>
          <el-icon>
            <FullScreen />
          </el-icon>
          最大化
        </el-dropdown-item>
        <el-dropdown-item divided>
          <el-icon>
            <Remove />
          </el-icon>
          关闭当前
        </el-dropdown-item>
        <el-dropdown-item>
          <el-icon>
            <DArrowLeft />
          </el-icon>
          关闭左边
        </el-dropdown-item>
        <el-dropdown-item>
          <el-icon>
            <DArrowRight />
          </el-icon>
          关闭右边
        </el-dropdown-item>
        <el-dropdown-item divided>
          <el-icon>
            <CircleClose />
          </el-icon>
          关闭其他
        </el-dropdown-item>
        <el-dropdown-item>
          <el-icon>
            <FolderDelete />
          </el-icon>
          关闭全部
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
@import "../index.scss";
</style>
