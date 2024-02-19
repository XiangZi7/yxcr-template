<template>
  <div class="w-full">
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="flex flex-wrap justify-center gap-4 px-4 mb-4">
          <!-- Card 1 -->
          <div class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-blue-500">
                  <svg class="h-10 w-10" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <!-- 使用您选择的icon SVG图标 -->
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">Card 1 Title</div>
                <div class="text-sm text-gray-600">Subtitle 1</div>
              </div>
            </div>
            <div class="text-xl text-gray-900 mt-2">58</div>
          </div>

          <!-- Card 2 -->
          <div class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-green-500">
                  <svg class="h-10 w-10" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <!-- 使用您选择的icon SVG图标 -->
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">Card 2 Title</div>
                <div class="text-sm text-gray-600">Subtitle 2</div>
              </div>
            </div>
            <div class="text-xl text-gray-900 mt-2">123</div>
          </div>

          <!-- Card 3 -->
          <div class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-red-500">
                  <svg class="h-10 w-10" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <!-- 使用您选择的icon SVG图标 -->
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">Card 3 Title</div>
                <div class="text-sm text-gray-600">Subtitle 3</div>
              </div>
            </div>
            <div class="text-xl text-gray-900 mt-2">256</div>
          </div>

          <!-- Card 4 -->
          <div class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-purple-500">
                  <svg class="h-10 w-10" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <!-- 使用您选择的icon SVG图标 -->
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
                  </svg>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">Card 4 Title</div>
                <div class="text-sm text-gray-600">Subtitle 4</div>
              </div>
            </div>
            <div class="text-xl text-gray-900 mt-2">89</div>
          </div>
        </div>

        <div>
          <label class="text-xl font-semibold text-[#424542]">收入</label>
          <yx-echarts-pro :option="chartOption" width="100%" height="500px"></yx-echarts-pro>
        </div>

        <el-row :gutter="20">
          <el-col :span="16">
            <div>
              <label class="ml-[3%] text-xl font-semibold text-[#424542]">订单状态</label>
              <div class="flex gap-2 bg-white rounded-xl shadow-xl">
                <!-- 待发货 -->
                <div class="flex flex-col items-center justify-between p-4  flex-1">
                  <div class="mb-4">
                    <div class="text-gray-500">
                      <!-- 将此处的图标按需要替换 -->
                      <el-icon size="50">
                        <Box/>
                      </el-icon>
                    </div>
                  </div>
                  <div class="text-lg text-xs font-semibold mb-2 text-[#abb4be]">
                    待发货
                  </div>
                  <div class="mb-4 text-gray-600 text-2xl">
                    20
                  </div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 已发货 -->
                <div class="flex flex-col items-center justify-between p-4  flex-1">
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Van/>
                      </el-icon>
                    </div>
                  </div>
                  <div class="text-lg font-semibold mb-2 text-[#abb4be] text-xs">
                    已发货
                  </div>
                  <div class="mb-4 text-gray-600 text-2xl">
                    15
                  </div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 售后 -->
                <div class="flex flex-col items-center justify-between p-4 flex-1">
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Service/>
                      </el-icon>
                    </div>
                  </div>
                  <div class="text-lg font-semibold mb-2 text-[#abb4be] text-xs">
                    售后
                  </div>
                  <div class="mb-4 text-gray-600 text-2xl">
                    5
                  </div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 已完成 -->
                <div class="flex flex-col items-center justify-between p-4 flex-1">
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Wallet/>
                      </el-icon>
                    </div>
                  </div>
                  <div class="text-lg font-semibold mb-2 text-[#abb4be] text-xs">
                    已完成
                  </div>
                  <div class="mb-4 text-gray-600 text-2xl">
                    30
                  </div>
                  <el-button type="primary">查看</el-button>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div>
              <label class=" text-xl font-semibold text-[#424542]">支付方式</label>
              <yx-echarts-pro :option="paymentMethodOpt" width="100%" height="200px"></yx-echarts-pro>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
const daysOfWeek = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
const dataOrderAmount = [150, 230, 224, 218, 135, 147, 260]; // 订单金额数据
const dataOrderCount = [10, 15, 12, 18, 8, 14, 20]; // 订单数量数据

const chartOption = ref({
  xAxis: {
    type: 'category',
    data: daysOfWeek,
    boundaryGap: false,
  },
  yAxis: [{
    type: 'value',
    // name: '订单金额',
    // 可以设置 axisLabel 根据需要格式化标签
    axisLabel: {
      formatter: '{value} 元'
    }
  }, {
    type: 'value',
    // name: '订单数量',
    // 可以设置 axisLabel 根据需要格式化标签
    axisLabel: {
      formatter: '{value} 单'
    }
  }],
  tooltip: {
    trigger: 'axis'
  },
  legend: { // 图例
    data: ['订单金额', '订单数量'],
    bottom: 0,
    left: 'center' // 图例居中显示
  },
  series: [
    {
      name: '订单金额',
      data: dataOrderAmount,
      type: 'line',
      smooth: true,
      color: '#55a9ff', // 设置系列颜色
      yAxisIndex: 0, // 此系列使用第一个 y 轴
    },
    {
      name: '订单数量',
      data: dataOrderCount,
      type: 'line',
      smooth: true,
      color: '#6fe4c6', // 设置系列颜色
      yAxisIndex: 1, // 此系列使用第二个 y 轴
    }
  ]
});
// 支付方式
// 饼图的配置项
const paymentMethodOpt = {
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b}: {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'center',
    data: ['支付宝', '微信支付'],
    bottom: 0
  },
  series: [
    {
      name: '支付方式',
      type: 'pie',
      radius: '55%',
      center: ['50%', '40%'], // 调整图的竖直位置留出底部文字显示空间
      data: [
        {value: 1048, name: '支付宝'},
        {value: 735, name: '微信支付'}
      ],
      label: {
        show: true,
        formatter: function (params: { percent: number }) {
          // 使用 Math.round 来四舍五入到最接近的整数
          return `${Math.round(params.percent)}%`;
        },
        position: 'inside', // 标签的位置设置为 'inside'，在扇区内部显示
        padding: 2 // 标签的内边距设为2
      },
      emphasis: {
        label: {
          show: true,
        }
      }
    }
  ]
};
</script>

<style lang="scss" scoped>

</style>
