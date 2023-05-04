<template>
  <div class="com-container">
    <h4 class="text-center">预测结果</h4>
    <div class="list" id="weekend1" ref="weeKend1_ref"></div>
  </div>
</template>

<script >
// import { number } from 'echarts'
export default {
  name: "weeKend1",
  data() {
    return {
      chartInstance: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 0, // 知名标题的字体大小
      dataIndex: null ,
     data1:null
     
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
    initChart() {
      // 初始化echartInstance对象
      this.chartInstance = this.$echarts.init(this.$refs.weeKend1_ref);
     
      const initOption = {
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
                        textStyle: null
                    }
                },
         lineStyle: {
                        normal: {
                            width: 10,
                            curveness: 0.2,// 线条弯曲曲度
                            color: "#F07C82"
                        }
                    },      
            
      };
      
      this.chartInstance.setOption(initOption);
    },

  async  getData() {
      // 获取服务器的数据，对this.allData进行赋值之后，调用updateChart方法更新图表
      const that = this   // that 为 this的别名,与this指向同一实例， 目的是拿到 vue对象实例
       await this.$http.get("transferline").then(function(response){
          console.log(response.data)
         that.allData = response.data // that.allData 与 this.allData 是同一个东西 相当于改了this.allData数据
        },function(err){ // 请求数据出错时调用此函数，成功不调用这个，可以忽略
          console.log(err)
        })
        console.log("OD' data :",this.allData); // 看this.allData是否拿到新数据
        
      this.updateChart();
    },

    updateChart() {
       
      const dataOption = {
        
        series: [{
            type: 'graph',
            layout: 'circular',
            roam: true,
            focusNodeAdjacency: true,
            circular: {
                rotateLabel: true
            },
             label: {
                        normal: {
                            formatter: function (params, ticket, callback) {
                                params.name = params.data.name;
                                return params.name;
                            },
                            show: true
                        }
                    },
            edgeSymbol: ['circle'],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
                normal: {
                    textStyle: {
                        fontSize: 17,
                        fontWeight: 'bold',
                        fontFamily: '宋体'
                    }
                }
            },
                    label: {
                         position: 'right',
                         formatter: '{b}',
                        show: true,
                        textStyle: null
                },
              lineStyle: {
                            width: 10,
                            curveness: 0.2,// 线条弯曲曲度
                            color: "#F07C82"
                    },  


            // 节点数据格式
            data: [
               {
                    name: 'line1',
                    symbolSize:20, // 设置节点大小
                    value:62073
                },
                {
                    name: 'line2',
                    symbolSize:20, // 设置节点大小
                    value:62073
                }, {
                    name: 'line3',
                    symbolSize: 10,
                    value:107332
               
                },
                {
                    name: 'line4',
                    symbolSize: 10,
                    value:6294
                
                },
                {
                    name: 'line5',
                    symbolSize: 10,
                    value:15867
                 
                },
                {
                    name: 'line10',
                    symbolSize: 10,
                    value:51984
                
                },
                {
                    name: 'line11',
                    symbolSize: 10,
                    value:98730
               
                },
                {
                    name: 'line12',
                    symbolSize: 10,
                    value:40946
                 
                },
                // ......
            ],
            
            // 线条数据格式
            links: [
                {
                    source: "line1",
                    target: "line2",
                },
                {
                    source: "line1",
                    target: "line3",
                    
                },
                {
                    source: "line1",
                    target: "line5",
                    
                },
                {
                    source: "line1",
                    target: "line10",
                    
                },
                {
                    source: "line1",
                    target: "line12",
                    
                },
                {
                    source: "line2",
                    target: "line12",
                    
                },
                {
                    source: "line2",
                    target: "line3",
                },
                 {
                    source: "line2",
                    target: "line10",
                },
                {
                    source: "line3",
                    target: "line4",
                },
                {
                    source: "line3",
                    target: "line10",
                },
                {
                    source: "line3",
                    target: "line11",
                
                },
                {
                    source: "line3",
                    target: "line12",
                
                }, {
                    source: "line5",
                    target: "line12",
                
                }, {
                    source: "line10",
                    target: "line4",
                
                }, {
                    source: "line10",
                    target: "line11",
                
                }, {
                    source: "line10",
                    target: "line12",
                
                },
                {
                    source: "line11",
                    target: "line12",
                
                },{
                    source: "line12",
                    target: "line11",
                
                }
               ],
                }],
    }
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

