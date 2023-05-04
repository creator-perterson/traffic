<!--templat  标签只起到包裹的作用，不会被渲染成任何元素-->
<template>
  <div class="map-container" :style="{ height: clientHeight + 'px' }">
    <header class="header">
      <!-- header左侧区域 -->
      <div class="header-left">
        <!-- 搜索栏区域 -->
        <div class="search_bar">
          <img src="" alt="" />
          <!-- 搜索站点 -->
          <input
            class="search"
            type="text"
            autocomplete="off"
            v-model="station"
            @keyup.enter="searchSta(station)"
          />
        </div>
        <div id="time"></div>
      </div>

      <!-- header中间区域  -->
      <div class="header-center">
        <!-- 标题 -->
        <div class="header-title">智慧交通分析系统</div>
        <div class="header-img"></div>
      </div>
      <!--  header右侧按钮区域 -->
      <div class="header-right">
        <div class="header-button">
          <button type="button" class="gohome_bt" @click="goHome">
            分析预测系统
          </button>
        </div>
      </div>
      <!-- header下方隔开横线 -->
      <div class="header-bottom"></div>
    </header>

    <!-- 左边盒子 -->
    <div class="left-box">
      <!-- 中间的橙色区域 -->
      <div class="explain">
        <div class="explain-inner">
          <div class="explain-text">
            {{ this.$store.state.single_sta }} 站进站客流
          </div>
          <div class="explain-number">
            {{ this.$store.state.enter_station }}
          </div>
        </div>
        <div class="explain-inner">
          <div class="explain-text">
            {{ this.$store.state.single_sta }} 站出站客流
          </div>
          <div class="explain-number">
            {{ this.$store.state.out_station }}
          </div>
        </div>
      </div>
      <div class="trend">
        <div class="resize">
          <span @click="changeSize('trend')"></span>
        </div>
        <Trend></Trend>
      </div>
      <div id="pie">
        <Pie></Pie>
      </div>
    </div>
    <!-- 中间盒子 -->
    <div class="middle">
      <div id="map">
        <MyMap :staFromMap="station" @staChange="searchSta"></MyMap>
        <div class="resize">
          <span @click="changeSize('map')"></span>
        </div>
      </div>

      <div class="hour_number">
        <Calendar></Calendar>
      </div>
    </div>
    <!-- 右边盒子 -->
    <div class="right-box">
      <div class="text-pie">
        <!-- <hr
          style="filter: alpha(opacity=100, finishopacity=0, style=3)"
          width="80%"
          color="#987cb9"
          SIZE="3"
        /> -->
        <div class="line-radar">
          <Radar></Radar>
        </div>

        <!-- <hr
          style="filter: alpha(opacity=100, finishopacity=0, style=3)"
          width="80%"
          color="#987cb9"
          SIZE="3"
        /> -->
        <div class="gender">
          <Gender></Gender>
        </div>
      </div>
      <!-- 站点高峰列表 -->
      <div class="list_wrapper">
        <div class="list-title">‖ 早晚高峰客流站点排名 ‖</div>
        <!-- <ul class="list_morning">
          <span class="list_title">早高峰排名</span>
          <li v-for="item in this.$store.state.query_morning" class="menu-item">
            <b>{{ item[1] }}：{{ item[2] }}</b>
          </li>
        </ul>
        <ul class="list_night">
          <span class="list_title">晚高峰排名</span>
          <li v-for="item in this.$store.state.query_night" class="menu-item">
            <b>{{ item[1] }}：{{ item[2] }}</b>
          </li>
        </ul> -->
        <div class="list-wenzi">
          <div class="gun">
            <span>排名</span>
            <span>早高峰</span>
            <span>数量</span>
            <!-- <span>排名</span>
            <span>晚高峰</span>
            <span>数量</span> -->
          </div>
          <div id="FontScroll" class="myscroll">
            <ul>
              <li v-for="item in this.$store.state.query_morning">
                <div class="fontInner clearfix">
                  <span>
                    <b>{{ item[0] }}</b>
                  </span>
                  <span>{{ item[1] }}</span>
                  <span>{{ item[2] }}</span>
                </div>
              </li>
            </ul>
            <!-- <ul>
              
              <li v-for="item in this.$store.state.query_night">
                <div class="fontInner clearfix">
                  <span>
                    <b>1</b>
                  </span>
                  <span>{{ item[1] }}</span>
                  <span>{{ item[2] }}</span>
                </div>
              </li>
            </ul> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>




<script>
import MyHeader from "@/components/subcomponents/MyHeader.vue";
import MyMap from "@/components/menus/MyMap.vue";
import Calendar from "@/components/menus/Calendar.vue";
import Trend from "@/components/menus/Trend.vue";
import Pie from "@/components/menus/Pie.vue";
import Gender from "@/components/menus/Gender.vue";
import Weekend from "@/components/menus/Weekend.vue";
import Radar from "@/components/menus/Radar.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "Map",
  components: {
    MyHeader,
    MyMap,
    Calendar,
    Pie,
    Trend,
    Gender,
    Weekend,
    Radar,
  },
  data() {
    return {
      // 各组件是否全屏状态
      fullScreenStatus: {
        trend: false,
        // seller: false,
        map: false,
        rank: false,
        zhuzhuang: false,
        stock: false,
        screenHeight: document.body.clientHeight - 110,
      },
      line: null,
      station: "sta1",
      isShow: false, // 组件是否直接显示，默认false
      n: 1,
      // staListMorning: this.$store.state.query_morning,
      // staListNight: this.$store.state.query_night,
    };
  },
  props: ["staFromMap"],
  computed: {
    // 借助 mapState 生成计算属性，从state中读取数据(数组写法，简写形式)
    ...mapState(["single_sta", "query_sta"]),
  },
  mounted() {
    this.getTime();
    this.getList();
    this.searchSta();

    // this.getList()
    const that = this;
    window.onresize = () => {
      return (() => {
        // 可以限制最小高度
        // if (document.body.clientHeight - 240 < 450) {
        //   return
        // }
        that.window.screenHeight = document.body.clientHeight - 110;
        that.screenHeight = window.screenHeight;
      })();
    };
  },
  methods: {
    // 头部时间
    getTime() {
      var myDate = new Date();
      var myYear = myDate.getFullYear(); //获取完整的年份(4位,1970-????)
      var myMonth = myDate.getMonth() + 1; //获取当前月份(0-11,0代表1月)
      var myToday = myDate.getDate(); //获取当前日(1-31)
      var myDay = myDate.getDay(); //获取当前星期X(0-6,0代表星期天)
      var myHour = myDate.getHours(); //获取当前小时数(0-23)
      var myMinute = myDate.getMinutes(); //获取当前分钟数(0-59)
      var mySecond = myDate.getSeconds(); //获取当前秒数(0-59)
      var week = [
        "星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ];
      var nowTime;

      nowTime =
        myYear +
        "-" +
        this.fillZero(myMonth) +
        "-" +
        this.fillZero(myToday) +
        "&nbsp;&nbsp;" +
        this.fillZero(myHour) +
        ":" +
        this.fillZero(myMinute) +
        ":" +
        this.fillZero(mySecond) +
        "&nbsp;&nbsp;" +
        week[myDay] +
        "&nbsp;&nbsp;";
      //console.log(nowTime);
      this.$("#time").html(nowTime);
    },
    fillZero(str) {
      var realNum;
      if (str < 10) {
        realNum = "0" + str;
      } else {
        realNum = str;
      }
      return realNum;
    },
    // 监听全屏事件
    changeSize(chartName) {
      // 1.改变fullScreenStatus
      this.fullScreenStatus[chartName] = !this.fullScreenStatus[chartName];
      // 2.手动调用每个图表中的 screenAdapter 触发响应式
      this.$nextTick(() => {
        this.$refs[chartName].screenAdapter();
      });
    },
    // 切换显示状态
    changeIsShow() {},
    goHome() {
      this.$router.push("/home");
    },
    searchLine() {},
    // // 转发给vuex处理
    searchSta(newSta) {
      console.log("***********");

      this.station = newSta;
      console.log(this.station);
      const sta_number = this.station.slice(3);
      this.$store.dispatch("getStation", {
        sta_number,
        _this: this,
      });
      this.line = "";
    },
    // ***************************************
    // mapActions 用法：{生成函数方法名：'xxx'} 第二个参数意义：this.$store.dispatch.xxx
    //  mapActions与 mapMutations使用时，若需要传递参数需要：在模板中绑定事件时传递好参数，否则参数为事件对象
    ...mapActions({ searchLine: "getLine" }),
    screenAdapter() {
      // console.log(this.$refs.zhuzhuang_ref.offsetWidth)
      // const titleFontSize = this.$refs.zhuzhuang_ref.offsetWidth / 100 * 3.6
      // console.log(titleFontSize)
      // // 和分辨率大小相关的配置项
      // const adapterOption = {
      //   title: {
      //     textStyle: {
      //       fontSize: titleFontSize
      //     }
      //   },
      //   tooltip: {
      //     axisPointer: {
      //       lineStyle: {
      //         width: titleFontSize
      //       }
      //     }
      //   },
      //   series: [
      //     {
      //       barwidth: titleFontSize,
      //       itemStyle: {
      //         barBorderRadius: [0, titleFontSize / 2, titleFontSize / 2, 0]
      //       }
      //     }
      //   ]
      // }
      this.chartInstance.setOption(adapterOption);
      this.chartInstance.resize();
    },
    getList() {
      this.$store.dispatch("getStaList", {
        _this: this,
      });
    },
    // 定时器累加特效
    startInterval() {
      let num = 0;
      let set = null;
      let speed = 30; // 速度
    },
  },

  // 属性为多键值对时需要深度监听

  watch: {
    screenHeight(val) {
      // 为了避免频繁触发resize函数导致页面卡顿，使用定时器
      if (!this.timer) {
        // 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
        this.screenHeight = val;
        this.timer = true;
        let that = this;
        setTimeout(function () {
          // 打印screenWidth变化的值
          console.log(that.screenHeight);
          that.timer = false;
        }, 400);
      }
    },
    "this.$store.state.enter_station": {
      deep: true,
      handler() {},
    },
  },
};
</script>

<style lang="less" scoped>
@font-face {
  font-family: electronicFont;
  src: url(../assets/font/DS-DIGIT.TTF); // 声明字体
}
html {
  overflow: -moz-hidden-unscrollable;
  height: 100%;
}
body::-webkit-scrollbar {
  display: none;
}

body {
  font-size: 0.16rem;
  color: #ffffff;
  position: relative;
  width: 100%;
  height: 100%;
  .map-container {
    background: url("../assets/img/bg01.png") no-repeat;
    background-size: 100%;
    height: 100%;
    width: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    .header {
      // background-color: #780303;
      width: 100%;
      height: 5rem;
      display: block;
      margin-bottom: 3rem;
      .header-left {
        float: left;
        width: 25%;
        height: 5rem;
        color: white;
        /*    border: 1px solid green;*/
        text-align: center;
        line-height: 1.05rem;
        .search_bar {
          float: left;
          height: 100%;
          width: 40%;

          .search {
            height: 100%;
            width: 100%;
            background: url(../assets/img/infoBg.png) no-repeat center;
            background-size: 100%;
            border-color: transparent;
            color: #ffffff;
            text-align: center;
            font-size: 1.6rem;
          }
        }
        #time {
          padding-top: 5%;
          font-size: 1.5rem;
        }
      }
      .header-center {
        width: 50%;
        height: 5rem;
        float: left;
        .header-title {
          padding-top: 2%;
          text-align: center;
          color: #ffffff;
          font-size: 2rem;
          text-shadow: 0 0 0.3rem #00d8ff;
          line-height: 1.05rem;
        }
        .header-img {
          background: url(../assets/img/head.gif) no-repeat center;
          background-size: 100%;
          height: 12%;
          width: 50%;
          position: absolute;
          top: 0.4rem;
        }
      }
      .header-right {
        float: right;
        width: 25%;
        height: 5rem;
        .header-button {
          height: 100%;
          width: 40%;
          float: right;
          .gohome_bt {
            height: 100%;
            width: 100%;
            background: url(../assets/img/infoBg.png) no-repeat center;
            background-size: 100%;
            border-color: transparent;
            color: #ffffff;
            font-size: 1.6rem;
          }
        }
      }
      .header-bottom {
        float: left;
        width: 100%;
        height: 0.25rem;
        background: url("../assets/img/header.png") no-repeat;
        // background-size: auto;
        background-size: 100%;
        // padding: 2 2rem;
        margin-left: 0.3rem;
        padding-top: 1.56rem;
      }
    }
    .left-box {
      // background: linear-gradient(#191838, #130f18);
      // background-color: aqua;
      // margin-left: 0px;
      // margin-top: 0px;
      // background-color: yellow;
      float: left;
      height: 930px;
      width: 15%;
      // background: url(../assets/img/line\(1\).png);
      //  border: 0.01rem solid #217093;
      margin-right: 0.4rem;
      .explain {
        height: 200px;
        width: 100%;
        margin-bottom: 0.4rem;
        .explain-inner {
          width: 50%;
          height: 100%;
          // border: 0.01rem solid #217093;
          float: left;
          line-height: 1rem;
          color: #ffff;
          // background-color: red;

          text-align: center;
          font-family: "electronicFont"; //字体
          .explain-text {
            background: url(../assets/img/titBg.png) no-repeat center;
            padding-top: 25%;
            font-size: 20px;
            text-align: left;
            height: 40%;
          }
          .explain-number {
            background: url(../assets/img/titBg.png) no-repeat center;
            padding-top: 40%;
            color: #ffeb7b;
            font-size: 60px;
            text-align: center;
            width: 100%;
            height: 60%;
          }
        }
      }

      .trend {
        height: 300px;
        width: 100%;
        border: 0.01rem solid #217093;
        margin-bottom: 0.2rem;
      }
      #pie {
        // background-color: rgb(143, 12, 110);
        border: 0.01rem solid #217093;
        height: 396px;
        width: 100%;
      }
    }
    .middle {
      width: 71%;
      height: 930px;
      float: left;
      margin-right: 0.4rem;
      #map {
        // margin-left: 0px;
        // margin-bottom: 0px;
        // background: linear-gradient(#686091, #191838);
        // background-color: purple;
        // background: url(../assets/img/line\(1\).png);
        // float: left;
        width: 100%;
        height: 600px;
        border: 0.01rem solid #217093;
        margin-bottom: 0.4rem;
        background: url(../assets/img/mapBg.png) no-repeat center;
        background-size: 250%;
      }

      .hour_number {
        // background: linear-gradient(#191838, #130f18);
        border: 0.01rem solid #217093;
        // border-radius: 20px;
        // background-color: green;
        height: 300px;
        width: 100%;

        //  background: rgba(255, 255, 255, 0.04) url(../assets/img/line\(1\).png);
      }

      // .month_number {
      //   // background: linear-gradient(#686091, #191838);
      //   background-color: red;
      //   border: 2px solid #780303;
      //   border-radius: 20px;
      //   height: 500px;
      //   width: 80%;
      // }
    }
    .right-box {
      width: 13%;
      height: 930px;
      float: right;
      .text-pie {
        height: 620px;
        width: 100%;
        // margin-bottom: 0.2rem;
        // background-color: aqua;
        // border: 2px solid #780303;
        // border-radius: 20px;
        // float: right;
        h5 {
          padding-top: 10px;
          text-align: left;
        }
        .line-radar {
          height: 400px;
          width: 100%;
          border: 0.01rem solid #217093;
          background: url(../assets/img/line01.png) no-repeat center;
        }
        .gender {
          margin-top: 0.5rem;
          height: 200px;
          width: 100%;
          // background-color: aliceblue;
          border: 0.01rem solid #217093;
          background: url(../assets/img/line01.png) no-repeat center;
        }
      }
      .list_wrapper {
        margin-top: 0.1rem;
        height: 285px;
        width: 100%;
        // color: rgb(184, 140, 18);
        border: 0.01rem solid #217093;
        background: url(../assets/img/line01.png) no-repeat center;

        .list-title {
          margin-top: 5px;
          text-align: center;
          font-size: 18px;
          font-weight: bold;
          color: white;
        }
        // .list_morning {
        //   // background-color: red;
        //   height: 250px;
        //   width: 50%;
        //   float: left;
        //   // margin: 0%;
        // }
        // .list_night {
        //   height: 250px;
        //   width: 50%;
        //   float: right;
        //   // background-color: green;
        // }
        .list_title {
          color: aliceblue;
        }
        .list-wenzi {
          height: 100%;
          width: 100%;
          .gun {
            height: 8%;
            width: 100%;
            span {
              display: block;
              float: left;
              height: 2rem;
              width: 33%;
              /*background: red;*/
              /*margin: 15px 10px 10px 10px;*/
              /*padding: 20px;*/
              text-align: center;
              font-size: 0.14rem;
              font-weight: 600;
              color: #61d2f7;
              text-align: center;
              margin-bottom: 0.1rem;
            }
          }
          .myscroll {
            width: 100%;
            height: 65%;
            overflow: hidden;
            /*float: left;*/
            margin-top: 0.1rem;
            ul {
              padding-left: 0;
              li {
                padding-top: 1.5%;
                height: 2rem;
                width: 100%;
                color: #ffffff;
                text-align: center;
                line-height: 0.32rem;
                /*border-bottom: 1px dashed #404040;*/
                /*width:33.3%;*/
                overflow: hidden;
                font-size: 0.14rem;
                .fontInner span {
                  display: inline-block;
                  /*float: left;*/
                  /*height: 20px;*/
                  width: 31%;
                  color: #ffffff;
                  b {
                    padding-top: 7%;
                    display: inline-block;
                    width: 1rem;
                    height: 1rem;
                    border-radius: 0.3rem;
                    color: #ffffff;
                    line-height: 0.2rem;
                  }
                  /*background: red;*/
                }
              }
            }

            ul li:hover {
              box-shadow: -10px 0px 15px #034c6a inset,
                /*左边阴影*/ 10px 0px 15px #034c6a inset;
              cursor: pointer;
            }
          }
          #FontScroll ul li:nth-child(8n-4) .fontInner span b {
            background: #20a8fe;
          }

          #FontScroll ul li:nth-child(8n-3) .fontInner span b {
            background: #eb6841;
          }

          #FontScroll ul li:nth-child(8n-2) .fontInner span b {
            background: #3fb8af;
          }

          #FontScroll ul li:nth-child(8n-1) .fontInner span b {
            background: #fe4365;
          }

          #FontScroll ul li:nth-child(8n-5) .fontInner span b {
            background: #fc9d9a;
          }

          #FontScroll ul li:nth-child(8n-6) .fontInner span b {
            background: #edc951;
          }

          #FontScroll ul li:nth-child(8n-7) .fontInner span b {
            background: #c8c8a9;
          }

          #FontScroll ul li:nth-child(8n) .fontInner span b {
            background: #83af9b;
          }

          #FontScroll ul li:first-child .fontInner span b {
            background: #036564;
          }

          #FontScroll ul li:last-child .fontInner span b {
            background: #3299bb;
          }
        }
      }
    }
  }
}

</style>