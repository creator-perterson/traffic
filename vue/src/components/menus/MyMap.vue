<template>
  <div class="com-container" >
    <div
      class="com-chart"
      ref="map_ref"
      style="width: 100%; height: 590px"
    ></div>
  </div>
</template>

<script>
import vuex from "vuex";
import "../../assets/chalk";
export default {
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      showChoice: false, // 是否显示可选项
      choiceType: "line1", // 显示的数据类型
      titleFontSize: 0, // 知名标题的字体大小
      timerId: null,
      currentPage: 1,
      map_pic: null, // map_pic 即为 seriesArr
      station: 'sta1',
    };
  },
  // props:['staFromMap'],
  mounted() {
    this.initChart();
    this.getData();
    window.addEventListener("resize", this.screenAdapter);
    this.screenAdapter();
  },
  destroyed() {
    clearInterval(this.timerId);
    window.removeEventListener("resize", this.screenAdapter);
  },
  computed: {
    selectTypes() {
      if (!this.allData) {
        return [];
      } else {
        return this.allData.type.filter((item) => {
          return item.key !== this.choiceType;
        });
      }
    },
    showTitle() {
      if (!this.allData) {
        return "";
      } else {
        return this.allData[this.choiceType].title;
      }
    },
    // 设置给标题的样式
    comStyle() {
      return {
        fontSize: this.titleFontSize + "px",
      };
    },
  },
  methods: {
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.map_ref);
      const initOption = {
        // backgroundColor: '#404a59',
        title: {
          text: "‖    铁  路  矢  量  图    ‖",
          top:"5%",
          left: "center",
          textStyle:{
            color:"white",
            
          }
        },

        grid: {
          left:"2%",
          top: "10%",
          bottom: "1%",
          right:"2%",
        },

        // tooltip: {
        //   triggerOn: 'name',
        //   formmater: function (params) {
        //     return '线路：' + params.id.toFixed(2) + '<br>站点：' + params.name.toFixed(2)
        //   }
        // },
        legend: {
          show:false,
          bottom: 2,
          left: "20%",
          icon: "path://M67.335,33.596L67.335,33.596c-0.002-1.39-1.153-3.183-3.328-4.218h-9.096v-2.07h5.371 c-4.939-2.07-11.199-4.141-14.89-4.141H19.72v12.421v5.176h38.373c4.033,0,8.457-1.035,9.142-5.176h-0.027 c0.076-0.367,0.129-0.751,0.129-1.165L67.335,33.596L67.335,33.596z M27.999,30.413h-3.105v-4.141h3.105V30.413z M35.245,30.413 h-3.104v-4.141h3.104V30.413z M42.491,30.413h-3.104v-4.141h3.104V30.413z M49.736,30.413h-3.104v-4.141h3.104V30.413z  M14.544,40.764c1.143,0,2.07-0.927,2.07-2.07V35.59V25.237c0-1.145-0.928-2.07-2.07-2.07H-9.265c-1.143,0-2.068,0.926-2.068,2.07 v10.351v3.105c0,1.144,0.926,2.07,2.068,2.07H14.544L14.544,40.764z M8.333,26.272h3.105v4.141H8.333V26.272z M1.087,26.272h3.105 v4.141H1.087V26.272z M-6.159,26.272h3.105v4.141h-3.105V26.272z M-9.265,41.798h69.352v1.035H-9.265V41.798z",
          itemWidth: 20,
          itemHeight: 20,
        },
        dataZoom: [
          {
            type: "slider",
            xAxisIndex: 0,
            show: false,
            filterMode: "none",
          },
          {
            type: "slider",
            yAxisIndex: 0,
            filterMode: "none",
            show: false,
          },
          {
            type: "inside",
            xAxisIndex: 0,
            filterMode: "none",
            show: false,
          },
          {
            type: "inside",
            yAxisIndex: 0,
            filterMode: "none",
            show: false,
          },
        ],
        xAxis: {
          min: -100,
          max: 70,
          type: "value",
          show: false,
          axisLine: { onZero: false },
        },
        yAxis: {
          min: -30,
          max: 60,
          type: "value",
          show: false,
          axisLine: { onZero: false },
        },
        series: {
          type: "line",
          smooth: false,
        },

        hoverLayerThreshold: 167,
      };
      this.chartInstance.setOption(initOption);

      /*******************************鼠标点击事件****************************/

      //******************************** ************************************/
      /*******************************鼠标hover事件****************************/
      // this.chartInstance.on('mouseover',function(params) {
      //   console.log("hover被触发了");
      //   console.log(params)
      //   // console.log(params.data.id)
      //   // console.log(params.seriesId)
      // });
      //******************************** 行!!!!************************************/
    },
    getData() {
      // await this.$http.get()
      // 对allData进行赋值
      const ret = require("../map.json");
      this.allData = ret;
     
      
      this.updateChart();
      // 启动定时器
      // this.startInterval()
    },
    updateChart() {
      // 处理线路-8条名称
      const nameArr = this.allData.data.map((item) => {
        // console.log(item.id);
        return item.id;
      });
      const childrenArr = this.allData.data.map((item) => {
        // console.log("-----", item.children);
        return item.children;
      });

      // 类目轴的数据
      const seriesArr = [
        {
          name: nameArr[0],
          id: "line1",
          type: "line",
          smooth: false,
          colorBy: "data",
          symbolSize: 5,
          data: childrenArr[0],
          color:"#1CD5E7"
        },
        {
          name: nameArr[1],
          id: "line2",
          type: "line",
          symbolSize: 5,
          smooth: false,
          data: childrenArr[1],
          color: "#1CD5E7",
        },
        {
          name: nameArr[2],
          id: "line3",
          type: "line",
          symbolSize: 5,
          smooth: false,
          data: childrenArr[2],
          color: "#1CD5E7",
        },
        {
          name: nameArr[3],
          id: "line4",
          type: "line",
          symbolSize: 5,
          smooth: false,
          data: childrenArr[3],
          color: "#1CD5E7",
        },
        {
          name: nameArr[4],
          id: "line5",
          type: "line",
          symbolSize: 5,
          smooth: true,
          data: childrenArr[4],
           color:'#1CD5E7'
        },
        {
          name: nameArr[5],
          id: "line10",
          type: "line",
          smooth: false,
          symbolSize: 5,
          data: childrenArr[5],
           color:'#1CD5E7'
        },
        {
          name: nameArr[6],
          id: "line11",
          type: "line",
          smooth: false,
          symbolSize: 5,
          data: childrenArr[6],
           color:'#1CD5E7'
        },
        {
          name: nameArr[7],
          id: "line12",
          type: "line",
          smooth: false,
          symbolSize: 5,
          data: childrenArr[7],
           color:'#1CD5E7'
        },
      ];
      this.map_pic = seriesArr;
      // y轴的数据 series下的数据
      // const series = seriesArr.map((item, index) => {
      //   return {
      //     id: this.id,
      //     name: nameArr,
      //     type: 'line',
      //     data: valueArr
      //   }
      // })
      // 图例的数据
      // const legendArr = seriesArr.map(item => {
      //   return item.name
      // })
      const dataOption = {
        series: seriesArr,
        toolbox: {
          show: true,
          orient: "vertical",
          top: "center",
          feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {},
            myBubble: {
              show: true,
              title: "自定义扩展方法",
              icon: "path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891",
              onclick: this.makeBubble,
            },
          },
        },
        tooltip: {
          show: true,
          textStyle: "oblique",
          triggerOn: "mousemove",
          formatter: function (params) {
            console.log(params);
            return "线路：" + params.seriesId + "<br>站点：" + params.data.id;
          },
        },
      };
      this.chartInstance.setOption(dataOption);
    },
    // startInterval () {
    //   if (this.timerId) {
    //     clearInterval(this.timerId)
    //   }
    //   this.timerId = setInterval(() => {
    //     this.currentPage++
    //     if (this.currentPage > this.totalPage) {
    //       this.currentPage = 1
    //     }
    //     this.updateChart()
    //     console.log(this.currentPage)
    //     // console.log(this.totalPage)
    //     // console.log(this.allData.length)
    //   }, 3000)
    // },
    screenAdapter() {
      this.titleFontSize = (this.$refs.map_ref.offsetWidth / 100) * 4; // 得到显示图表容器 得到显示图表宽度
      const adapterOption = {
        legend: {
          itemWidth: this.titleFontSize,
        },
      };
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
    handleSelect(currentType) {
      this.choiceType = currentType;
      this.updateChart();
      this.showChoice = false;
    },
    // 地铁矢量图上显示气泡图
    makeBubble() {
      /**
       *      模拟数据
       *
       *
       *
      //  */
      const that = this;
      this.chartInstance.on("click", function (params) {
        console.log("点被触发了");
        // console.log(params)  // object
        console.log(params.data.id); // station number
        // console.log(params.seriesId)  // line number

        // console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")
        that.station = params.data.id;
      });

      console.log(this);
      console.log("makeBubble is working......");
    },
    showSearchBar() {},
    // 站点变化自动调用此函数更换信息
    changeSta(newVal) {
      console.log("station had been changed,now staChange is working...");
      console.log(newVal)
      this.$emit("staChange", newVal);
    },
  },
  // 监听站点变化
  watch: {
    station: {
      handler(newVal, oldVal) {
        // console.log(
        //   "The original station is " + oldVal + ",and the new one is " + newVal
        // );
        this.changeSta(newVal);
      },
    },
  },
};
</script>

<style lang="less" scoped>
.title {
  position: absolute;
  left: 20px;
  top: 20px;
  z-index: 10;
  color: white;
  .title-icon {
    margin-left: 10px;
    cursor: pointer;
  }
}
</style>
