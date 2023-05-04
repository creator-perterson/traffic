<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="od_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      allData: [],
      line11: [],
      dateArr: [],
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
      this.chartInstance = this.$echarts.init(this.$refs.od_ref);
      // 对图表初始化配置的控制
      const initOption = {
        title: {
          text: "‖    客流预测情况对比分析",
          top:"1%",
          // left: "center",
          textStyle:{
            color:"white",
            
          }
        },
        legend: {
          show: true,
          data: [
            "line11原始数据",
            "线性回归",
            "二次多项式回归",
            "三次多项式回归",
            "岭回归",
            "随机森林",
            "lightGBM",
            "极致梯度提升",
            "长短期神经网络",
          ],
          bottom:"0.5%",
          selected:{
            "线性回归":false,
            "二次多项式回归":false,
            "三次多项式回归":false,
            "岭回归":false,
            "随机森林":false,
            "lightGBM":false,
            "极致梯度提升":false,
            "长短期神经网络":false,
          }
          
        },
        xAxis: {
          type: "category",
        },
        yAxis: {
          type: "value",
        },
        series:[
          {
            type:'line',
            lineStyle:{
              width:5,
              // shadowColor:'white',
            },
            
          },
          {
            type:'line',
            lineStyle:{
              width:5,
            }
          },
          {
            type:'line',
            lineStyle:{
              width:5,
            }
          },
          {
            type:'line',
            lineStyle:{
              width:5,
            }
          },
          
        ]
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("model").then(
        function (response) {
          // console.log(response.data);
          // that.query_age = response.data;
          that.allData = response.data;
        },
        function (err) {
          console.log(err);
        }
      );
      this.dateArr = this.allData.map((item) => {
        return item.timedate.slice(0, 10);
      });
    //   console.log(this.dateArr);
      this.line11 = this.allData.map((item) => {
        return item.line11;
      });
      //   console.log(this.line11);
      //   console.log(this.allData);
      this.updateChart();
    },
    // 更新图表
    updateChart() {
      // 处理数据
      const linearRegression = [];
      const quadraticPolynomial = [];
      const cubicPolynomial = [];
      const ridgeRegression = [];
      const randomForest = [];
      const lightGBM = [];
      const Xgboost = [];
      const Lstm = [];
      this.allData.forEach((item) => {
        linearRegression.push(item.linearRegression);
        quadraticPolynomial.push(item.quadraticPolynomial);
        cubicPolynomial.push(item.cubicPolynomial);
        ridgeRegression.push(item.ridgeRegression);
        randomForest.push(item.randomForest);
        lightGBM.push(item.lightGBM);
        Xgboost.push(item.xgboost);
        Lstm.push(item.lstm);
      });

      const dataOption = {
        xAxis: {
          data: this.dateArr,
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
        },
        series: [
          {
            type: "line",
            name: "line11原始数据",
            smooth: true,
            data: this.line11,
          },
          {
            type: "line",
            name: "线性回归",
            smooth: true,
            data: linearRegression,
          },
          {
            type: "line",
            name: "二次多项式回归",
            smooth: true,
            data: quadraticPolynomial,
          },
          {
            type: "line",
            name: "三次多项式回归",
            smooth: true,
            data: cubicPolynomial,
          },
          {
            type: "line",
            name: "岭回归",
            smooth: true,
            data: ridgeRegression,
          },
          {
            type: "line",
            name: "随机森林",
            smooth: true,
            data: randomForest,
          },
          {
            type: "line",
            name: "lightGBM",
            smooth: true,
            data: lightGBM,
          },
          {
            type: "line",
            name: "极致梯度提升",
            smooth: true,
            data: Xgboost,
          },
          {
            type: "line",
            name: "长短期神经网络",
            smooth: true,
            data: Lstm,
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
