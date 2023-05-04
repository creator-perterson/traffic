<template>
  <div class="com-container">
    <div
      class="com-chart"
      ref="calendar_ref"
      :style="{ height: clientHeight - 194 + 'px' }"
    ></div>
  </div>
</template>

<script>
// import { number } from 'echarts'
import vuex from "vuex";
export default {
  name: "Calendar",
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
      timerId: null,
      borderSize: 0,
    };
  },
  mounted() {
    this.initChart();
    this.getData();
    window.addEventListener("resize", this.screenAdapter);
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  methods: {
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.calendar_ref);
      const that = this.$store.state.single_sta;
      // 对图表初始化配置的控制
      const initOption = {
        backgroundColor: "transparent",
        legend: {
          left: "1%",
          top: "5%",
          orient: "horizontal",
          data: ["high", "Top10"],
          textStyle: {
            color: "#fff",
          },
        },
        // backgroundColor: '#fff',
        title: {
          top: 20,
          text: "‖  Sta" + that + "站点日客流量情况  ‖",
          top: "5%",
          left: "center",
          textStyle: {
            color: "#fff",
          },
        },

        tooltip: {
          trigger: "item",
        },
        visualMap: [
          {
            show: false,
            orient: "vertical",
            // min: 10,
            // max: 42,
            seriesIndex: [0],
            // 表示 目标系列 的视觉样式。
            target: {
              inRange: {
                // color: ["#ddb926"],
              },
            },
            // 表示 visualMap-continuous 本身的视觉样式。
            controller: {
              inRange: {
                // color: ["#F4E925"],
              },
            },
            right: 10,
          },
        ],
        calendar: [
          {
            left: "5%",
            bottom: "0.1%",
            cellSize: [36.8, 30],
            yearLabel: {
              show: false,
              color: "white",
            },
            orient: "horizontal",
            dayLabel: {
              firstDay: 1,
              nameMap: "cn",
              color: "white",
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#fff",
                width: 1,
                type: "solid",
                cap: "round",
              },
            },
            itemStyle: {
              color: "transparent",
              borderWidth: 1,
              borderColor: "#3C3C3C",
            },
            monthLabel: {
              show: true,
              color: "white",
            },

            range: ["2019-12-2", "2020-7-26"],
          },
        ],
      };

      this.chartInstance.setOption(initOption);
    },

    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("stationday/" + that.$store.state.single_sta).then(
        function (response) {
          // console.log(response.data,"that.$store.state.single_sta is:",that.$store.state.single_sta);
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
      // console.log("Calendar's original data :", this.allData, "this.$store:", this.$store);
      this.updateChart();
    },
    updateChart() {
      /**
       * 处理数据
       */

      let total_numberArr = this.allData.map((item) => {
        return [item.timedate.slice(0, 10), item.day_num];
      });
      // console.log(total_numberArr)

      const dataOption = {
        brush: {
          toolbox: ["rect", "polygon"],
          throttleType: "denounce",
          inBrush: {
            color: "#ACF0F6",
          },
        },
        series: [
          {
            name: "number",
            type: "scatter",
            symbol: "circle",
           
            itemStyle: {
             normal:{
                color: "#13658F",
             }
            },
            coordinateSystem: "calendar",

            data: total_numberArr,
            symbolSize: function (item) {
              if (item[1] < 40) {
                return item[1] / 2;
              } else {
                return item[1] / 15;
              }
            },
          },
          {
            name: "Top30",
            type: "effectScatter",
            coordinateSystem: "calendar",
            // calendarIndex: 1,
            data: total_numberArr
              .sort(function (a, b) {
                return b[1] - a[1];
              })
              .slice(0, 30),
            symbolSize: function (item) {
              if (item[1] < 40) {
                return item[1] / 2;
              } else {
                return item[1] / 15;
              }
            },
            itemStyle: {
              color: "#68E3EF",
            },
          },
        ],
      };
      this.chartInstance.setOption(dataOption);
    },
    // startInterval () {},
    screenAdapter() {
      // 获取容器宽高
      this.titleFontSize = (this.$refs.calendar_ref.offsetWidth / 100) * 1.6;
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: this.titleFontSize * 0.8,
          },
        },
        // legend: {
        //   itemWidth: this.titleFontSize / 2,
        //   itemHeight: this.titleFontSize / 2,
        //   itemGap: this.titleFontSize / 2,
        //   textStyle: {
        //     fontSize: this.titleFontSize / 2,
        //   },
        //},
        // calendar: [
        //   {
        //     cellSize: [this.titleFontSize * 1.2, this.titleFontSize * 1.1],
        //   },
        // ],
      };
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
  },
  watch: {
    "$store.state.query_calendar": {
      deep: true,
      handler() {
        this.allData = this.$store.state.query_calendar;
        //  console.log("Calenda chart's station has been changed,now new allData is:",this.allData)
        this.initChart();
        this.updateChart();
      },
    },
  },
};
</script>

<style lang="less" scoped>
.com-chart {
  padding: 0px;
  margin-top: 0px;
  padding-bottom: 2%;
  background-color: transparent;
}
.com-container {
  height: 300px;
  width: 100%;
  background-color: transparent;
}
</style>

