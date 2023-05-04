<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
     <div class="com-chart" ref="kind_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter()
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    // 初始化echartInstance对象
    initChart () {
      this.chartInstance = this.$echarts.init(this.$refs.kind_ref, 'chalk')
      // 对图表初始化配置的控制
      const initOption = {}
      this.chartInstance.setOption(initOption)
    },
    // 获取服务器的数据
    async getData () {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const { data: ret } = await this.$http.get('stock')
      this.allData = ret
      console.log(this.allData)
      this.updateChart()
    },
    // 更新图表
    updateChart () {
      const dataOption = {}
      this.chartInstance.setOption(dataOption)
    },
    // startInterval () {},
    screenAdapter () {
      const adapterOption = {}
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    }
  }
}
</script>

<style lang="less" scoped>
</style>
