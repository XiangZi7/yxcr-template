<template>
  <div class="w-full">
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="flex flex-wrap justify-center gap-4 px-4 mb-4">
          <!-- Card 1 -->
          <div
            class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs"
          >
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-blue-500">
                  <el-icon size="50">
                    <MostlyCloudy />
                  </el-icon>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">
                  日活跃用户数量
                </div>
                <div class="text-xs text-gray-600">daily active users</div>
              </div>
            </div>
            <div class="flex justify-between mt-4">
              <div class="text-xl text-gray-900 bt-4">58</div>
              <div class="text-red-500 flex items-end">
                +25
                <el-icon>
                  <CaretTop />
                </el-icon>
              </div>
            </div>
          </div>

          <!-- Card 2 -->
          <div
            class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs"
          >
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-green-500">
                  <el-icon size="50">
                    <PartlyCloudy />
                  </el-icon>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">
                  月活跃用户数量
                </div>
                <div class="text-xs text-gray-600">monthly active users</div>
              </div>
            </div>
            <div class="flex justify-between mt-4">
              <div class="text-xl text-gray-900 bt-4">458</div>
              <div class="text-red-500 flex items-end gap-1">
                +25
                <el-icon>
                  <CaretTop />
                </el-icon>
              </div>
            </div>
          </div>

          <!-- Card 3 -->
          <div
            class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs"
          >
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-red-500">
                  <el-icon size="50">
                    <User />
                  </el-icon>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">用户数量</div>
                <div class="text-xs text-gray-600">Number of users</div>
              </div>
            </div>
            <div class="flex justify-between mt-4">
              <div class="text-xl text-gray-900 bt-4">256</div>
              <div class="text-green-500 flex items-end gap-1 gap-1">
                -25
                <el-icon>
                  <CaretBottom />
                </el-icon>
              </div>
            </div>
          </div>

          <!-- Card 4 -->
          <div
            class="bg-white shadow-lg rounded-lg p-6 flex-1 min-w-0 max-w-xs"
          >
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="text-purple-500">
                  <el-icon size="50">
                    <Wallet />
                  </el-icon>
                </div>
              </div>
              <div>
                <div class="text-lg font-semibold text-gray-800">总收入</div>
                <div class="text-xs text-gray-600">Gross income</div>
              </div>
            </div>
            <div class="flex justify-between mt-4">
              <div class="text-xl text-gray-900 bt-4">961</div>
              <div class="text-red-500 flex items-end gap-1">
                +25
                <el-icon>
                  <CaretTop />
                </el-icon>
              </div>
            </div>
          </div>
        </div>

        <div>
          <h2 class="text-xl font-semibold text-[#424542]">收入</h2>
          <yx-echarts-pro
            :option="chartOption"
            width="100%"
            height="400px"
          ></yx-echarts-pro>
        </div>

        <el-row :gutter="20">
          <el-col :span="16">
            <div>
              <h2 class="ml-[3%] text-xl font-semibold text-[#424542]">
                订单状态
              </h2>
              <div class="flex gap-2 bg-white rounded-xl">
                <!-- 待发货 -->
                <div
                  class="flex flex-col items-center justify-between p-4 flex-1"
                >
                  <div class="mb-4">
                    <div class="text-gray-500">
                      <!-- 在此添加实际的图标 -->
                      <el-icon size="50">
                        <Box />
                      </el-icon>
                    </div>
                  </div>
                  <div
                    class="text-lg text-xs font-semibold mb-2 text-[#abb4be]"
                  >
                    待发货
                  </div>
                  <div class="mb-4 text-gray-600 text-2xl">20</div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 已发货 -->
                <div
                  class="flex flex-col items-center justify-between p-4 flex-1"
                >
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Van />
                      </el-icon>
                    </div>
                  </div>
                  <div
                    class="text-lg font-semibold mb-2 text-[#abb4be] text-xs"
                  >
                    已发货
                  </div>
                  <div class="mb-4 mb-4 text-gray-600 text-2xl">15</div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 售后 -->
                <div
                  class="flex flex-col items-center justify-between p-4 flex-1"
                >
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Service />
                      </el-icon>
                    </div>
                  </div>
                  <div
                    class="text-lg font-semibold mb-2 text-[#abb4be] text-xs"
                  >
                    售后
                  </div>
                  <div class="mb-4 mb-4 text-gray-600 text-2xl">5</div>
                  <el-button type="primary">查看</el-button>
                </div>

                <!-- 已完成 -->
                <div
                  class="flex flex-col items-center justify-between p-4 flex-1"
                >
                  <div class="mb-4">
                    <!-- 示例图标，在此处替换您自己的SVG -->
                    <div class="text-gray-500">
                      <el-icon size="50">
                        <Wallet />
                      </el-icon>
                    </div>
                  </div>
                  <div
                    class="text-lg font-semibold mb-2 text-[#abb4be] text-xs"
                  >
                    已完成
                  </div>
                  <div class="mb-4 mb-4 text-gray-600 text-2xl">30</div>
                  <el-button type="primary">查看</el-button>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div>
              <h2 class="text-xl font-semibold text-[#424542]">支付方式</h2>
              <yx-echarts-pro
                :option="paymentMethodOpt"
                width="100%"
                height="250px"
              ></yx-echarts-pro>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="8">
        <div>
          <yx-echarts-pro
            :option="regionalClimateChartOption"
            width="100%"
            height="400px"
          ></yx-echarts-pro>
        </div>
        <div>
          <h2 class="mt-4 mb-4 text-xl font-semibold text-[#424542]">
            动漫电影观看热度排行
          </h2>
          <el-scrollbar height="400px">
            <ul class="space-y-1">
              <li
                v-for="(anime, index) in animeViewRanking"
                :key="anime.id"
                class="flex items-center justify-between p-2 hover:bg-gray-100"
              >
                <div class="flex items-center">
                  <span class="font-bold text-indigo-600 mr-2"
                    >{{ index + 1 }}.</span
                  >
                  <el-image
                    :src="anime.src"
                    class="rounded-xl mr-2"
                    style="width: 50px; height: 50px"
                  />
                  <span class="text-gray-800">{{ anime.name }}</span>
                </div>
                <span class="text-sm text-gray-600"
                  >{{ anime.views }} 次观看</span
                >
              </li>
            </ul>
          </el-scrollbar>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
const daysOfWeek = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
const dataOrderAmount = [150, 230, 224, 218, 135, 147, 260]; // 订单金额数据
const dataOrderCount = [10, 15, 12, 18, 8, 14, 20]; // 订单数量数据
const animeViewRanking = [
  {
    id: 1,
    name: "千与千寻",
    views: 9800,
    src: "https://img2.baidu.com/it/u=2365581712,848303171&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  },
  {
    id: 2,
    name: "我的邻居山田君",
    views: 8700,
    src: "https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%93%88%E5%B0%94%E7%9A%84%E7%A7%BB%E5%8A%A8%E5%9F%8E%E5%A0%A1&step_word=&lid=11153410708977179097&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=257439822,1072371859&os=369326874,1122726567&simid=257439822,1072371859&pn=10&rn=1&di=46137345&ln=970&fr=&fmq=1708391353697_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=11&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fww1.sinaimg.cn%2Fmw690%2F006vWzpWly1hmis94qy7nj30j60ad3zo.jpg&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwxLDMsMiw2LDQsNSw4LDcsOQ%3D%3D",
  },
  {
    id: 3,
    name: "龙猫",
    views: 8500,
    src: "https://img2.baidu.com/it/u=3764354389,4234489592&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500",
  },
  {
    id: 4,
    name: "哈尔的移动城堡",
    views: 8300,
    src: "https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%93%88%E5%B0%94%E7%9A%84%E7%A7%BB%E5%8A%A8%E5%9F%8E%E5%A0%A1&step_word=&lid=11153410708977179097&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=257439822,1072371859&os=369326874,1122726567&simid=257439822,1072371859&pn=10&rn=1&di=46137345&ln=970&fr=&fmq=1708391353697_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=11&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fww1.sinaimg.cn%2Fmw690%2F006vWzpWly1hmis94qy7nj30j60ad3zo.jpg&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwxLDMsMiw2LDQsNSw4LDcsOQ%3D%3D",
  },
  {
    id: 5,
    name: "风之谷",
    views: 8100,
    src: "https://img2.baidu.com/it/u=2365581712,848303171&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  },
];
// 收入
const chartOption = ref({
  xAxis: {
    type: "category",
    data: daysOfWeek,
    boundaryGap: false,
  },
  yAxis: [
    {
      type: "value",
      // name: '订单金额',
      // 可以设置 axisLabel 根据需要格式化标签
      axisLabel: {
        formatter: "{value} 元",
      },
    },
    {
      type: "value",
      // name: '订单数量',
      // 可以设置 axisLabel 根据需要格式化标签
      axisLabel: {
        formatter: "{value} 单",
      },
    },
  ],
  tooltip: {
    trigger: "axis",
  },
  legend: {
    // 图例
    data: ["订单金额", "订单数量"],
    bottom: 0,
    left: "center", // 图例居中显示
  },
  series: [
    {
      name: "订单金额",
      data: dataOrderAmount,
      type: "line",
      smooth: true,
      color: "#55a9ff", // 设置系列颜色
      yAxisIndex: 0, // 此系列使用第一个 y 轴
    },
    {
      name: "订单数量",
      data: dataOrderCount,
      type: "line",
      smooth: true,
      color: "#6fe4c6", // 设置系列颜色
      yAxisIndex: 1, // 此系列使用第二个 y 轴
    },
  ],
});
// 支付方式
const paymentMethodOpt = {
  tooltip: {
    trigger: "item",
    formatter: "{a} <br/>{b}: {c} ({d}%)",
  },
  legend: {
    orient: "vertical",
    left: "center",
    data: ["支付宝", "微信支付"],
    bottom: 0,
  },
  series: [
    {
      name: "支付方式",
      type: "pie",
      radius: "55%",
      center: ["50%", "40%"], // 调整图的竖直位置留出底部文字显示空间
      data: [
        { value: 1048, name: "支付宝" },
        { value: 735, name: "微信支付" },
      ],
      label: {
        show: true,
        formatter: function (params: { percent: number }) {
          // 使用 Math.round 来四舍五入到最接近的整数
          return `${Math.round(params.percent)}%`;
        },
        position: "inside", // 标签的位置设置为 'inside'，在扇区内部显示
      },
      emphasis: {
        label: {
          show: true,
        },
      },
    },
  ],
};
// 图表配置
const regionalClimateChartOption = ref({
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["蒸发量", "降水量"],
    bottom: 0,
  },
  xAxis: {
    type: "category",
    data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月"],
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      name: "蒸发量",
      type: "bar",
      data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6],
      markPoint: {
        data: [
          { type: "max", name: "最大值" },
          { type: "min", name: "最小值" },
        ],
      },
    },
    {
      name: "降水量",
      type: "bar",
      data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6],
      markPoint: {
        data: [
          { name: "年最高", value: 182.2, xAxis: 7, yAxis: 183 },
          { name: "年最低", value: 2.3, xAxis: 11, yAxis: 3 },
        ],
      },
    },
  ],
});
</script>

<style lang="scss" scoped></style>
