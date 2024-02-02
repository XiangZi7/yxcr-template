<script setup lang="ts">
const state = reactive({
  options: [],
})
const {
  options,
} = toRefs(state)
const ModelValue = defineModel()
const props = defineProps({
  dict: {
    type: String,
    default: ""
  },
  isView: {
    type: Boolean,
    default: false
  }
})
const {dict,isView} = toRefs(props)
onMounted(() => {
  httpGet(`dict/${dict?.value}`).then(res => {
    state.options = res.data
  })
})
</script>
<template>

  <div>
    <div v-if="isView">
    <el-select v-model="ModelValue">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      >
      </el-option>
    </el-select>
    </div>
    <div v-else>
      <span>{{options[ModelValue]?.label}}</span>
    </div>
  </div>


</template>
<style lang='scss' scoped>
</style>
