<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="time_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allData_morning: this.$store.state.query_time_morning,
      allData_night: this.$store.state.query_time_night,
      staArr: this.$store.state.query_time_sta,
    };
  },
  mounted() {
    this.initChart();
    // this.getData();
    this.updateChart();
    window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  methods: {
    // 初始化echartInstance对象
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.time_ref);
      // 对图表初始化配置的控制
      const initOption = {
        title: {
          text: "‖  早晚高峰站点统计分析",
          top:"1%",
          // left: "center",
          textStyle:{
            color:"white",
            
          }
        },
        aria: {
          // 开启无障碍访问
          enabled: "true",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        legend: {
          data: ["早高峰", "晚高峰"],
          textStyle: {
            color: "#ccc",
          },
          icon: "path://M67.335,33.596L67.335,33.596c-0.002-1.39-1.153-3.183-3.328-4.218h-9.096v-2.07h5.371 c-4.939-2.07-11.199-4.141-14.89-4.141H19.72v12.421v5.176h38.373c4.033,0,8.457-1.035,9.142-5.176h-0.027 c0.076-0.367,0.129-0.751,0.129-1.165L67.335,33.596L67.335,33.596z M27.999,30.413h-3.105v-4.141h3.105V30.413z M35.245,30.413 h-3.104v-4.141h3.104V30.413z M42.491,30.413h-3.104v-4.141h3.104V30.413z M49.736,30.413h-3.104v-4.141h3.104V30.413z  M14.544,40.764c1.143,0,2.07-0.927,2.07-2.07V35.59V25.237c0-1.145-0.928-2.07-2.07-2.07H-9.265c-1.143,0-2.068,0.926-2.068,2.07 v10.351v3.105c0,1.144,0.926,2.07,2.068,2.07H14.544L14.544,40.764z M8.333,26.272h3.105v4.141H8.333V26.272z M1.087,26.272h3.105 v4.141H1.087V26.272z M-6.159,26.272h3.105v4.141h-3.105V26.272z M-9.265,41.798h69.352v1.035H-9.265V41.798z",
          itemWidth: 100,
          itemHeight: 100,
          left: "30%",

          itemStyle: {
            //   color:'#EFEFEF'
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          //   show:'false',
          type: "value",

          min: "dataMin",
          max: "dataMax",
        },

        yAxis: [
          {
            type: "category",
            axisTick: {
              show: false,
            },
            // data: this.staData,
          },
        ],
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
          const that = this
        await this.$http("morningpeek").then(function (response) {
          console.log("早高峰", response.data)
          // context.state.query_morning = response.data
          // context.state.query_time_sta = response.data.map(item=>{
          //   return item.sta
          // })
       that.allData_morning  =  response.data.map(item=>{
            return[
             item.num_avg,
              item.sta
            ]
          })
          // context.state.query_morning=context.state.query_morning.sort(function(a,b){
          //   return b[1]-a[1]
          // }).slice(0,10)
          console.log(that.allData_morning)
        }, function (err) {
          console.log(err)
        })
        await this.$http("nightpeek").then(function (response) {
          console.log("晚高峰", response.data)
          that.allData_night  =  response.data.map(item=>{
            return[
             item.num_avg,
              item.sta
            ]
          })
          // context.state.query_night=context.state.query_night.sort(function(a,b){
          //   return b[1]-a[1]
          // }).slice(0,10)
          console.log(that.allData_night)
        }, function (err) {
          console.log(err)
        })
    },
    // 更新图表
    updateChart() {
      const dataOption = {
        yAxis: [
          {
            data: this.staArr,
          },
        ],
        series: [
          {
            name: "早高峰",
            type: "pictorialBar",
            stack: "Total",
            label: {
              show: false,
            },
            emphasis: {
              focus: "series",
            },
            data: this.allData_morning.map((item) => {
              return [-item[2], item[1]];
            }),
            itemStyle: {
              color: "#F1DFED",
            },
          },
          {
            name: "晚高峰",
            type: "pictorialBar",
            stack: "Total",
            label: {
              show: false,
              position: "left",
            },
            emphasis: {
              focus: "series",
            },
            data: this.allData_night.map((item) => {
              return [item[2], item[1]];
            }),
            itemStyle: {
              color: "#FBD88B",
            },
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
