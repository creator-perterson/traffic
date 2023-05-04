<!--
在这个组件中，原本为单周客流分析，现改为各线路客流总量变化分析
  组件名称为 SingleWeek.vue  路径为 /home/singleweek
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="linedate1_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allData: [],
    };
  },
  mounted() {
    this.initChart();
    this.getData();
    window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  methods: {
    // 初始化echartInstance对象
    initChart() {},
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      // this.allData = ret
      console.log(this.allData);
      this.updateChart();
    },
    // 更新图表
    updateChart() {
    },
    // startInterval () {},
    screenAdapter() {
      const adapterOption = {};
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
};
</script>

<style lang="less" scoped>
</style>
