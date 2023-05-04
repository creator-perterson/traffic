<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="staChange_ref" ></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
      dataIndex: null,
       // 滚动条的高度
      scrollTop: 0
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
    // 离开该页面需要移除这个监听的事件，不然会报错
  },
  methods: {     
    // 初始化echartInstance对象
    initChart() {
      this.chartInstance = this.$echarts.init(
        this.$refs.staChange_ref
      );
      // 对图表初始化配置的控制
      const initOption = {
        title: {
          text: "‖   线路换乘情况分析",
          top:"1%",
          // left: "center",
          textStyle:{
            color:"white",
            
          }
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {
              pixelRatio: 3, // 数值越高下载图片内存越大越清晰，建议范围（3-10）
            },
          },
        },

        tooltip: {},
        emphasis: {
          label: {
            show: true,
            textStyle: null,
          },
        },
        lineStyle: {
          normal: {
            width: 10,
            curveness: 0.2, // 线条弯曲曲度
            color: "#F07C82",
          },
        },
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      //  await this.$http.get('stock')
      // this.allData = ret
      // console.log(this.allData)
      this.updateChart();
    },
    // 更新图表
    updateChart() {
      const dataOption = {
        series: [
          {
            type: "graph",
            layout: "circular",
            roam: true,
            focusNodeAdjacency: true,
            circular: {
              rotateLabel: true,
            },
            label: {
              normal: {
                formatter: function (params, ticket, callback) {
                  params.name = params.data.name;
                  return params.name;
                },
                show: true,
              },
            },
            edgeSymbol: ["circle"],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
              normal: {
                textStyle: {
                  fontSize: 17,
                  fontWeight: "bold",
                  fontFamily: "宋体",
                },
              },
            },
            label: {
              position: "right",
              formatter: "{b}",
              show: true,
              textStyle: null,
            },
            lineStyle: {
              width: 10,
              curveness: 0.2, // 线条弯曲曲度
              color: "#F07C82",
            },

            // 节点数据格式
            data: [
              {
                name: "line1",
                symbolSize: 35.6, // 设置节点大小
                value: 71203,
              },
              {
                name: "line2",
                symbolSize: 31.1, // 设置节点大小
                value: 62073,
              },
              {
                name: "line3",
                symbolSize: 53.6,
                value: 107332,
              },
              {
                name: "line4",
                symbolSize: 4,
                value: 6294,
              },
              {
                name: "line5",
                symbolSize: 8,
                value: 15867,
              },
              {
                name: "line10",
                symbolSize: 26,
                value: 51984,
              },
              {
                name: "line11",
                symbolSize: 49,
                value: 98730,
              },
              {
                name: "line12",
                symbolSize: 21,
                value: 40946,
              },
              // ......
            ],

            // 线条数据格式
            links: [
              {
                source: "line1",
                target: "line2",
                lineStyle: {
                  color: "#3ac0b7",
                },
              },
              {
                source: "line1",
                target: "line3",
                lineStyle: {
                  color: "#3ac0b7",
                },
              },
              {
                source: "line1",
                target: "line5",
                lineStyle: {
                  color: "#3ac0b7",
                },
              },
              {
                source: "line1",
                target: "line10",
                lineStyle: {
                  color: "#3ac0b7",
                },
              },
              {
                source: "line1",
                target: "line12",
                lineStyle: {
                  color: "#3ac0b7",
                },
              },
              {
                source: "line2",
                target: "line12",
                lineStyle: {
                  color: "#1dbbd4",
                },
              },
              {
                source: "line2",
                target: "line3",
                lineStyle: {
                  color: "#1dbbd4",
                },
              },
              {
                source: "line2",
                target: "line10",
                lineStyle: {
                  color: "#1dbbd4",
                },
              },
              {
                source: "line3",
                target: "line4",
                lineStyle: {
                  color: "#3d8ad4",
                },
              },
              {
                source: "line3",
                target: "line10",
                lineStyle: {
                  color: "#3d8ad4",
                },
              },
              {
                source: "line3",
                target: "line11",
                lineStyle: {
                  color: "#3d8ad4",
                },
              },
              {
                source: "line3",
                target: "line12",
                lineStyle: {
                  color: "#3d8ad4",
                },
              },
              {
                source: "line5",
                target: "line12",
              },
              {
                source: "line10",
                target: "line4",
                lineStyle: {
                  color: "#647ff4",
                },
              },
              {
                source: "line10",
                target: "line11",
                lineStyle: {
                  color: "#647ff4",
                },
              },
              {
                source: "line10",
                target: "line12",
                lineStyle: {
                  color: "#647ff4",
                },
              },
              {
                source: "line11",
                target: "line12",
              },
            ],
          },
        ],
      };
      this.chartInstance.setOption(dataOption);
      
    },
    // startInterval () {},
    // resizeHandler() {
    //   this.chart.resize()
    // },
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
