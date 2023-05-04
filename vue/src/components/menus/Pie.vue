<template>
  <div class="com-container">
    <div
      class="com-chart"
      ref="pie_ref"
      style="height: 500px; width: 100%"
    ></div>
  </div>
</template>

<script>
// import { number } from 'echarts'
import { mapActions } from "vuex";
export default {
  name: "Pie",
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
      timerId: null,
      station: this.$store.state.single_sta,
    };
  },
  mounted() {
    this.initChart();
    this.getData();
    this.highLight();
    window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  methods: {
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.pie_ref);

      const initOption = {
        title: {
          text: "‖  客流对比与站点分区情况  ‖",
          left: "center",
          top: "5%",
          textStyle: {
            color: "white",
            fontFamily: "sans-serif",
          },
        },

        grid: {
          left: "2%",
          top: "2%",
          // bottom: "12%",
        },
        // tooltip: {
        //     trigger: 'item',
        //     formatter: '{a} <br/>{b}: {c} ({d}%)'
        // },
        // backgroundColor: "transparent",
        legend: {
          type: "scroll", // 滚动图例
          orient: "horizontal", // 垂直布局
          top: "15%",
          right: "2%",
          icon: "circle", //图例样式
          textStyle: {
            color: "#", //图例文字颜色,与图例相同
          },
        },

        series: [
          {
            name: "站点聚类结果",
            type: "pie",
            selectedMode: "single",
            radius: [0, "70%"],
            label: {
              position: "inner",
              fontSize: 14,
            },
            labelLine: {
              show: false,
            },
            label: {
              position: "inner",
              fontSize: 14,
            },
          },
        ],
      };

      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据

    async getData() {
      // this.$store.dispatch("getCategory")
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("station").then(
        function (response) {
          // console.log(response.data);
          // that.query_age = response.data;
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );

      // console.log("this is Pie chart's data:",this.allData)
      this.updateChart();
    },
    /**
     *
     *  类别属性个数审查
     *
     */
    getRepeatNum(arr) {
      var newArr = [];
      for (var i = 0; i < arr.length; i++) {
        var item = arr[i];
        newArr[item] = newArr[item + 1] || 1;
      }
      return newArr;
    },
    // 更新图表
    updateChart() {
      /**
       *
       * 拿到接口后此段代码挪到vuex的mutations中去进行处理
       */
      const kindArr = [
        "Dist1",
        "Dist2",
        "Dist3",
        "Dist4",
        "Dist5",
        "Dist6",
        "Dist7",
      ];

      // 数据对象，用于外圈站点客流情况统计
      const dataObj = this.allData.map((item) => {
        return {
          name: item.station,
          value: item.enter_station + item.out_station,
        };
      });
      const dataArray = this.allData.map((item) => {
        return [item.station, item.enter_station + item.out_station];
      });
      // 数据对象，内圈统计属性数量
      const arr = this.allData.map((item) => {
        return [item.administrative_region.slice(4)];
      });
      // console.log(arr)
      const arr2 = [];
      for (var i = 0; i < arr.length; i++) {
        var item = arr[i];
        arr2[item - 1] = arr2[item] + 1 || 1;
      }
      console.log(arr2);
      const dataArray2 = [];
      for (let index in kindArr) {
        dataArray2.push({ name: kindArr[index], value: arr2[index] });
      }
      // console.log(dataArray2)

      // 标签处理--包括站点和属性
      const legendArr = [];
      for (var i = 1; i < 169; i++) {
        legendArr.push("Sta" + i);
      }
      for (var i = 0; i < kindArr.length; i++) {
        legendArr.push(kindArr[i]);
      }
      console.log(legendArr);
      // 半透明的颜色值
      const colorArr1 = [
        "rgba(237,211,237)",
        "rgba(218,170,219)",
        "rgba(199,133,200)",
        "rgba(255,104,169)",
        "rgba(150,53,151)",
        "rgba(122,41,123)",
      ];
      // 全透明的颜色
      var color = [
        "#8d7fec",
        "#5085f2",
        "#e75fc3",
        "#f87be2",
        "#f2719a",
        "#fca4bb",
        "#f59a8f",
        "#fdb301",
        "#57e7ec",
        "#cf9ef1",
      ];

      const dataOption = {
        // color: {
        //   type: "radial",
        //   x: 0.5,
        //   y: 0.5,
        //   r: 0.5,
        //   colorStops: [
        //     {
        //       offset: 0,
        //       color: colorArr1, // 0% 处的颜色
        //     },
        //     {
        //       offset: 1,
        //       color: colorArr2, // 100% 处的颜色
        //     },
        //   ],
        //   global: false, // 缺省为 false
        // },
        tooltip: {
          show: true,
          textStyle: "oblique",
          triggerOn: "mousemove",
          formatter: function (params) {
            console.log(params);
            return (
              "站点：" + params.data.name + "<br>乘客量：" + params.data.value
            );
          },
        },
        legend: {
          data: legendArr,
        },
        series: [
          {
            name: "站点客流量对比",
            itemStyle: {
              //图形样式
              normal: {
                borderColor: "#ffffff",
                borderWidth: 1,
              },
            },
            type: "pie",
            colorBy: "data",
            color: dataArray[0].map((item) => {
              return color[item % 10];
            }),
            roseType: "radius",

            // emptyCircleStyle:{
            //   borderColor:colorArr1,
            // color:colorArr2,
            // },
            // aviodLabeOverlap: "true", // 默认开启防止标签重叠
            // selectedMode: 'single',
            radius: ["55%", "100%"],
            label: {
              position: "inner",
              // fontSize: 14,
            },
            labelLine: {
              show: false,
            },
            label: {
              show: false,
            },
            data: dataObj,
            // itemStyle:{
            //  normal:{
            //    label:{show:true},
            //    color:function(params){
            //      console.log(params)
            //      // 通过选中的站点序号改变颜色
            //      if(station == params.)
            //    }
            //  }
            // }
          },
          {
            name: "地区聚类结果",
            itemStyle: {
              //图形样式
              normal: {
                borderColor: "#ffffff",
                borderWidth: 1,
              },
            },
            type: "pie",
            color: colorArr1,
            radius: ["15%", "40%"],
            // labelLine: {
            //   show:false,
            //   length: 30,
            // },
            label: { show: false },
            data: dataArray2,
            // roseType: "radius",
            labelLine: {
              show: false,
              // length: 2,
              // length2: 3,
            },
            tooltip: {
              show: true,
              textStyle: "oblique",
              triggerOn: "mousemove",
              formatter: function (params) {
                console.log(params);
                return (
                  "地区：" +
                  params.data.name +
                  "<br>站点数：" +
                  params.data.value
                );
              },
            },
          },
        ],
      };
      this.chartInstance.setOption(dataOption);
      /**
       * 饼图高亮 HighLight()
       */
       setTimeout(function() {
            myChart.on('mouseover', function(params) {
                if (params.name == dataObj.name) {
                    myChart.dispatchAction({
                        type: 'highlight',
                        seriesIndex: 0,
                        dataIndex: 0
                    });
                } else {
                    myChart.dispatchAction({
                        type: 'downplay',
                        seriesIndex: 0,
                        dataIndex: 0
                    });
                }
            });

            myChart.on('mouseout', function(params) {
                myChart.dispatchAction({
                    type: 'highlight',
                    seriesIndex: 0,
                    dataIndex: 0
                });
            });
            myChart.dispatchAction({
                type: 'highlight',
                seriesIndex: 0,
                dataIndex: 0
            });
        }, 1000);

        // 使用刚指定的配置项和数据显示图表。
        /*myChart.setOption(option);*/
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    },

    highLight() {
      let that = this;
      this.station = this.$store.state.single_sta - 1;
      // this.chartInstance.on("click", function (params) {
      //   console.log("Pie's click被触发了");
      //   console.log(params);
      //   //点击高亮

      //   that.chartInstance.dispatchAction({
      //     type: "highlight",
      //     name:"Sta126"
      //   });

      // });
      this.chartInstance.dispatchAction({
        type: "highlight",
        dataIndex: that.station,
      });
    },
    screenAdapter() {
      const adapterOption = {};
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
  },
  watch: {
    "$store.state.single_sta": {
      deep: true,
      handler() {
        this.highLight();
        this.updateChart();
      },
    },
  },
};
</script>

<style lang="less" scoped>
.com-container {
  height: 400px;
  width: 100%;
}
</style>

