<!--

在这个组件中，原本为单周客流分析，现改为各线路客流总量变化分析
  组件名称为 SingleWeek.vue  路径为 /home/singleweek
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="SingleWeek_ref"></div>
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
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.SingleWeek_ref);
      const upColor = "#00da3c";
      const downColor = "#ec0000";
      // 对图表初始化配置的控制
      const initOption = {
        // color:'transparent',
        title: {
          text: "‖ 单线与总线客流情况分析",
          top: "1%",
          // left: "center",
          textStyle: {
            color: "white",
          },
        },
        dataZoom: [
          {
            type: "slider",
            start: 25,
            end: 100,
          },
        ],

        animation: false,
        legend: {
          bottom: 10,
          left: "center",
          data: [
            "line1",
            "line2",
            "line3",
            "line4",
            "line5",
            "line10",
            "line11",
            "line12",
            "total",
          ],
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
          },
          borderWidth: 1,
          borderColor: "#ccc",
          padding: 10,
          textStyle: {
            color: "#000",
          },
          // position: function (pos, params, el, elRect, size) {
          //   const obj = {
          //     top: 10
          //   };
          //   obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
          //   return obj;
          // }
          // extraCssText: 'width: 170px'
        },
        axisPointer: {
          link: [
            {
              xAxisIndex: "all",
            },
          ],
          label: {
            backgroundColor: "#777",
          },
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: false,
            },
            brush: {
              type: ["lineX", "clear"],
            },
          },
        },
        brush: {
          xAxisIndex: "all",
          brushLink: "all",
          outOfBrush: {
            colorAlpha: 0.1,
          },
        },
        visualMap: {
          show: false,
          seriesIndex: 5,
          dimension: 2,
          pieces: [
            {
              value: 1,
              color: downColor,
            },
            {
              value: -1,
              color: upColor,
            },
          ],
        },
        grid: [
          {
            left: "10%",
            right: "8%",
            height: "50%",
          },
          {
            left: "10%",
            right: "8%",
            top: "63%",
            height: "16%",
          },
        ],
        xAxis: [
          {
            type: "category",
            // data: data.categoryData,
            boundaryGap: false,
            axisLine: { onZero: false },
            splitLine: { show: false },
            // min: "dataMin",
            // max: "dataMax",
            axisPointer: {
              z: 100,
            },
          },
          {
            type: "category",
            gridIndex: 1,
            // data: data.categoryData,
            boundaryGap: false,
            axisLine: { onZero: false },
            axisTick: { show: false },
            splitLine: { show: false },
            axisLabel: { show: false },
            // min: "dataMin",
            // max: "dataMax",
          },
        ],
        yAxis: [
          {
            scale: true,
            splitArea: {
              show: true,
            },
          },
          {
            scale: true,
            gridIndex: 1,
            splitNumber: 2,
            axisLabel: { show: false },
            axisLine: { show: false },
            axisTick: { show: false },
            splitLine: { show: false },
          },
        ],
        dataZoom: [
          {
            type: "inside",
            xAxisIndex: [0, 1],
            start: 98,
            end: 100,
          },
          {
            show: true,
            xAxisIndex: [0, 1],
            type: "slider",
            top: "85%",
            start: 98,
            end: 100,
          },
        ],
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("dayline").then(
        function (response) {
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
      // this.allData = ret
      console.log(this.allData);
      this.updateChart();
    },
    // 更新图表
    updateChart() {
      const upColor = "#00da3c";
      const downColor = "#ec0000";
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
      /**
       * bar图的数据数组--每日全部线路总客流规律
       *
       */
      const barDataArr = [];
      this.allData.map((item) => {
        barDataArr.push(
          item.line1 +
            item.line2 +
            item.line3 +
            item.line4 +
            item.line5 +
            item.line10 +
            item.line11 +
            item.line12
        );
      });
      /**
       * 类目轴X  每日数据字符串数组
       */
      const xArr = this.allData.map((item) => {
        return item.timedate.slice(0, 10);
      });

      /**
       * 八条线路的每日客流量
       */
      const dataArr = this.allData.map((item) => {
        return [
          item.line1,
          item.line2,
          item.line3,
          item.line4,
          item.line5,
          item.line10,
          item.line11,
          item.line12,
        ];
      });
      console.log("This is bar arrData:", barDataArr);
      const dataOption = {
        xAxis: [
          {
            data: xArr,
          },
          {
            gridIndex: 1,
            data: xArr,
          },
        ],

        series: [
          {
            name: "total",
            type: "bar",
            xAxisIndex: 1,
            yAxisIndex: 1,
            data: barDataArr,
            showBackground: "true",
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(250,105,0,0.5)", // 0% 处的颜色
                },
                {
                  offset: 1,
                  color: "rgba(250,105,0,0)", // 100% 处的颜色
                },
              ],
              global: false, // 缺省为 false
            },
            backgroundStyle: {
              // color:'transparent'
              borderRadius: 10, // 统一设置四个角的圆角大小
              borderRadius: [10, 10, 0, 0], //（顺时针左上，右上，右下，左下）
              shadowColor: "rgba(0, 0, 0, 0.5)",
              shadowBlur: 10,
            },
            itemStyle: {
              borderRadius: 10, // 统一设置四个角的圆角大小
              borderRadius: [10, 10, 0, 0], //（顺时针左上，右上，右下，左下）
            },
            emphasis: {
              focus: "series",
              blurScope: "coordinateSystem",
            },
          },
          {
            name: "line1",

            type: "line",
            stack: "Total",
            // areaStyle:{      // 改为河流图
            //   // color:{
            //   //    colorStops: [
            //   //   {
            //   //     offset: 0,
            //   //     color: "rgba(250,105,0,0.5)", // 0% 处的颜色
            //   //   },
            //   //   {
            //   //     offset: 1,
            //   //     color: "rgba(250,105,0,0)", // 100% 处的颜色
            //   //   },
            //   // ],
            //   // }
            // },
            symbolSize: 5,
            data: dataArr.map((item) => {
              return item[0];
            }),
            color: "#F4E925",
          },
          {
            name: "line2",

            type: "line",
            symbolSize: 5,
            stack: "Total",
            data: dataArr.map((item) => {
              return item[1];
            }),
          },
          {
            name: "line3",
            stack: "Total",
            type: "line",
            symbolSize: 5,

            data: dataArr.map((item) => {
              return item[2];
            }),
          },
          {
            stack: "Total",
            name: "line4",
            type: "line",
            symbolSize: 5,
            data: dataArr.map((item) => {
              return item[3];
            }),
            // color: "#28526A",
          },
          {
            stack: "Total",
            name: "line5",
            type: "line",
            symbolSize: 5,
            data: dataArr.map((item) => {
              return item[4];
            }),
            //  color:'#A43347'
          },
          {
            stack: "Total",
            name: "line10",
            type: "line",
            data: dataArr.map((item) => {
              return item[5];
            }),
            //  color:'#999999'
          },
          {
            stack: "Total",
            name: "line11",
            type: "line",
            data: dataArr.map((item) => {
              return item[6];
            }),
            //  color:'#999999'
          },
          {
            stack: "Total",
            name: "line12",
            type: "line",
            data: dataArr.map((item) => {
              return item[7];
            }),
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
