<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="weekend_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted() {
    this.initChart();
    this.getData();
    window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter, false);
  },
  methods: {
    // 初始化echartInstance对象
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.weekend_ref);
      // 对图表初始化配置的控制
      const initOption = {
        title: {
          text: "‖  工作日，节假日客流量对比",
          textStyle:{
             color: "#ccc",
          }
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["工作日", "节假日", "周末"],
          //backgroundColor: "#ccc",
          textStyle: {
            color: "#ccc",
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Feb", "Mar", "Apr", "May", "Jun"],
        },
        yAxis: {
          type: "value",
        },
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this; // that 为 this的别名,与this指向同一实例， 目的是拿到 vue对象实例
      await this.$http.get("monthweekday").then(
        function (response) {
          console.log(response.data);
          that.allData = response.data; // that.allData 与 this.allData 是同一个东西 相当于改了this.allData数据
        },
        function (err) {
          // 请求数据出错时调用此函数，成功不调用这个，可以忽略
          console.log(err);
        }
      );
      console.log("Weekday and Workdays' data :", this.allData); // 看this.allData是否拿到新数据

      this.updateChart();
    },
    // 更新图表
    updateChart() {
      const colorArr1 = [
        "rgba(11,168,44,0.5)",
        "rgba(44,110,225,0.5)",
        "rgba(22,242,217,0.5)",
        // 'rgba(254,33,30,0.5)',
        // 'rgba(250,105,0,0.5)'
      ];
      // 全透明的颜色
      const colorArr2 = [
        "rgba(11,168,44,0)",
        "rgba(44,110,255,0)",
        "rgba(22,242,217,0)",
        // 'rgba(254,33,30,0)'
        // 'rgba(250,105,0,0)'
      ];
      const workdayArr = [];
      this.allData.map((item) => {
        workdayArr.push(item.sta_workday);
      });
      const weekendArr = [];
      this.allData.map((item) => {
        weekendArr.push(item.sta_weekend);
      });
      const holidayArr = [];
      this.allData.map((item) => {
        holidayArr.push(item.sta_holiday);
      });
      const dataOption = {
        series: [
          {
            name: "工作日",
            type: "line",
            // step: "start",
            // type: "line",
            stack: "Total",
            data: workdayArr,
            areaStyle: {
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: colorArr1[0],
                }, // 0%的颜色
                {
                  offset: 1,
                  color: colorArr2[0],
                }, // 100%的颜色
              ]),
            },
          },
          {
            name: "节假日",
            type: "line",
            // step: "middle",
            stack: "Total",
            areaStyle: {
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: colorArr1[1],
                }, // 0%的颜色
                {
                  offset: 1,
                  color: colorArr2[1],
                }, // 100%的颜色
              ]),
            },
            data: holidayArr,
          },
          {
            name: "周末",
            type: "line",
            // smooth:'true',
            // step: "middle",
            stack: "Total",
            areaStyle: {
              color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: colorArr1[2],
                }, // 0%的颜色
                {
                  offset: 1,
                  color: colorArr2[2],
                }, // 100%的颜色
              ]),
            },
            data: weekendArr,
          },
        ],
      };
      this.chartInstance.setOption(dataOption);
    },
    // startInterval () {},
    screenAdapter() {
      const adapterOption = {};
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
  },
};
</script>

<style lang="less" scoped>
</style>
