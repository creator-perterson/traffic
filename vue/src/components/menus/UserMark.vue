<template>
  <div class="com-container">
    <div class="com-chart" ref="UserMark_ref" id="UserMark_ref">
    </div>
  </div>
</template>

<script>
export default {
  name: "UserMark_ref",
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
      dataIndex: null,
      naArr: [],
      // userNumber:this.$store.state.query_user,
    };
  },
  mounted() {
    this.initChart(),
      this.getData(),
      window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  methods: {
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.UserMark_ref);
      const initOption = {
        title: {
          text: "‖   用户特征情况分析",
          top:"1%",
          // left: "center",
          textStyle:{
            color:"white",
            
          }
        },
        // colors : ['#FFAE57', '#FF7853', '#EA5151', '#CC3F57', '#9A2555'],
        //bgColor = '#2E2733'
        //  itemStyle = {
        //               star5: {
        //                   color: colors[0]
        //                      },
        //               star4: {
        //                   color: colors[1]
        //                       },
        //               star3: {
        //                   color: colors[2]
        //                       },
        //               star2: {
        //                   color: colors[3]
        //                     }
        //               },
        visualMap: {
          type: "continuous",
          min: 0,
          max: 350,
          inRange: {
            color: ["#2F93C8","#AEC48F","#058ba0", "#FFDB5C", "#F98862"],
          },
        },
        //    radius: [0, '90%'],
        // label: {
        //     rotate: 'radial'
        //   }
      };

      this.chartInstance.setOption(initOption);
    },
    async getData() {
      const that = this;
      await this.$http.get(that.$store.state.query_user).then(
        function (response) {
          console.log(response.data,"that.$store.state.query_user is:",that.$store.state.query_user);
          console.log(response.data);
          that.allData = response.data;
          //const Arr1 = that.allData.id;
        },
        function (err) {
          console.log(err);
        }
      );

      this.updateChart();
    },

    updateChart() {
      const data1 = [
        {
          name: "工作日",
          children: [
            {
              name: "all_dist",
              value: 222 ,
              children: [
                {
                  name: "Dist5",
                  value: 113,
                },
                 {
                  name: "Dist2",
                  value: 2,
                },
                {
                  name: "Dist1",
                  value: 11,
                },
                {
                  name: "Dist8",
                  value: 45,
                },
                {
                  name: "Dist10",
                  value: 24,
                },
              ],
            },
            {
              name: "enter_line",
              children: [
                {
                  name: "line3",
                  value: 98,
                },
                {
                  name: "line10",
                  value: 89,
                },
                {
                  name: "line11",
                  value: 50,
                },
                {
                  name: "line12",
                  value: 26,
                },
              ],
            },
            {
              name: "out_line",
              value: 10,
              children: [
                {
                  name: "line11",
                  value: 76,
                  itemStyle: {
                    color: "red",
                  },
                },
                {
                  name: "line12",
                  value: 59,
                },
                {
                  name: "line10",
                  value: 17,
                },
              ],
            },
          ],
        },
        {
          name: "节假日",
          children: [
            {
              name: "all_dist",
              children: [
                {
                  name: "Dist1",
                  value: 3,
                },
                {
                  name: "Dist5",
                  value: 4,
                },
                {
                  name: "Dist8",
                  value: 5,
                },
              ],
            },
          ],
        },
        {
          name: "正常周末",
          children: [
            {
              name: "all_dist",
              children: [
                {
                  name: "Dist5",
                  value: 17,
                },
                {
                  name: "Dist6",
                  value: 3,
                },
                {
                  name: "Dist8",
                  value: 14,
                },
                {
                  name: "Dist10",
                  value: 7,
                },
              ],
            },
          ],
        },
      ];
      const dataOption = {
        series: {
          type: "sunburst",
          data: data1,
          radius: [0, "100%"],
          label: {
            rotate: "radial",
          },
        },
      };
      this.chartInstance.setOption(dataOption);
    },
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