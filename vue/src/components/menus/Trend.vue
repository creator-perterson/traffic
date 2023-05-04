<!--
针对sellerpage 这条路径而显示出来的
在这个组件中，通过组件注册的方式，要显示出Seller.vue这个组件
-->
<template>
  <div class="com-container">
    <div class="com-chart" ref="gender_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      station: this.$store.state.single_sta,
      newData: this.$store.state.query_age,
      allData:null,
      titleFontSize:0,
    };
  },
  mounted() {
    this.initChart();
    // if (this.$store.state.query_age !== null) {
    //       this.allData = this.$store.state.query_age;
    //        this.updateChart()
         
    // }else{
    //   
      
    // }
    this.getData()
    this.updateChart()
    window.addEventListener("resize", this.screenAdapter);
    // 在页面加载完成的时候，主动进行屏幕的适配
    this.screenAdapter();
    // this.newData = this.$store.state.query_age;
    //     if (this.newData !== null) {
    //       this.allData = this.newData;
    //       this.updateChart();
    //     }
    
  },
  destroyed() {
    window.removeEventListener("resize", this.screenAdapter);
  },
  computed:{
    
  },
  methods: {
    // 初始化echartInstance对象
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.gender_ref);
       const that = this.$store.state.single_sta;
      // 对图表初始化配置的控制
      const initOption = {
        color: ["#F4E5BA", "#F4E5BA", "#56A3F1", "#F4E5BA"],
        title: {
          text: "‖  Sta"+that+"站点年龄分布情况  ‖",
          left:"center",
           textStyle: {
            color: "#fff",
          },
        },
        tooltip: {
          trigger: "item",
          formatter: "{c}",
        },
        // legend: {
        //   data: ["工作日", "节假日"],
        // },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        toolbox: {
          orient:'vertical',
          top:'60%',
          feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {},
          },
        },
        // legend: {
        //   data: ["<20", "20~30", "30~40", "40~50", ">50"],
        //   show:true
        // },
        radar: [
          {
            indicator: [
              { text: "<20" },
              { text: "20~30" },
              { text: "30~40" },
              { text: "40~50" },
              { text: ">50" },
            ],
            center: ["50%", "55%"],
            radius: 80,
            startAngle: 90,
            splitNumber: 4,
            shape: "circle",
            axisName: {
              formatter: "【{value}】",
              color: "#FBF4C6",
            },
            splitArea: {
              areaStyle: {
                color: "transparent",
                shadowColor: "#D7C0C8",
                shadowBlur: 10,
              },
            },
            axisLine: {
              lineStyle: {
                color: "rgba(211, 253, 250, 0.8)",
              },
            },
            splitLine: {
              lineStyle: {
                color: "rgba(211, 253, 250, 0.8)",
              },
            },
          },
        ],
        // xAxis: {
        //   type: "category",
        //   data: [ "<20", "20~30", "30~40", "40~50", ">50"],
        // },
        // yAxis: {
        //   type: "value",
        // },
      };
      this.chartInstance.setOption(initOption);
    },
    // 获取服务器的数据
    async getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this;
      await this.$http.get("age/" + that.$store.state.single_sta).then(
        function (response) {
          // console.log(response.data,"that.$store.state.single_sta is:",that.$store.state.single_sta);
          that.allData = response.data;
         that.$store.state.male_number = response.data.male;
          that.$store.state.female_number = response.data.female;
        },
        function (err) {
          console.log(err);
        }
      );
      // console.log("AgeChart's data :", this.allData, "this.$store:", this.$store);
      this.updateChart();
    },
    // 更新图表
    updateChart() {
      // console.log("Before update age chart:",this.allData)
      const ageArr = [
        this.allData.age_20,
        this.allData.age_20_30,
        this.allData.age_30_40,
        this.allData.age_40_50,
        this.allData.age_50,
      ];
      // console.log(ageArr);
      // const genderArr = [
      //   this.allData.male,
      //    this.allData.female,
      // ]
      // 更新性别数据
      this.$store.state.male_number = this.allData.male;
       this.$store.state.female_number = this.allData.female;

      const dataOption = {
        series: [
          {
            type: "radar",
            emphasis: {
              lineStyle: {
                width: 4,
              },
            },
            data: [
              {
                value: ageArr,
                name: "年龄结构",
                areaStyle: {
                  color: "rgba(255, 228, 52, 0.6)",
                },
              },
              // {
              //   value:genderArr,
              //   name:'性别比例'
              // }
            ],
          }
          
        ],
      };
      this.chartInstance.setOption(dataOption);
    },
    // startInterval () {},
    screenAdapter() {
       this.titleFontSize = (this.$refs.gender_ref.offsetWidth / 100) *2.3;
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: this.titleFontSize*1.5,
          },
        },
        legend: {
          itemWidth: this.titleFontSize / 2,
          itemHeight: this.titleFontSize / 2,
          itemGap: this.titleFontSize / 2,
          textStyle: {
            fontSize: this.titleFontSize / 2,
          },
        },
        calendar: [
          {
            cellSize: [this.titleFontSize*1.2 , this.titleFontSize*1.1 ],
          },
        ],
      };
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
  },
  watch: {
    '$store.state.query_age': {
      deep:true,
      handler() {
        // console.log("Age chart's station has been changed,now this.$store.state.query_age is:",this.$store.state.query_age)
        this.allData = this.$store.state.query_age
        //  console.log("Age chart's station has been changed,now new allData is:",this.allData)
        this.initChart()
        this.updateChart()
      },
    },
  },
};
</script>

<style lang="less" scoped>
</style>
