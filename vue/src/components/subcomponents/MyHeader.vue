<template>
  <div class="layout-header-container">
    <header class="header">
      <!-- header左侧区域 -->
      <div class="header-left">
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
          <button type="button" class="goMap-bt" @click="gotoMap">
            主页面
          </button>
        </div>
      </div>
      <!-- header下方隔开横线 -->
      <div class="header-bottom"></div>
    </header>
    <!-- 左侧 logo 和 标题区域 -->

    <!--  右侧按钮区域 -->
    <!-- <div class="layout-header-right">
      <button type="button" class="btn btn-light" @click="gotoMap">
        铁路图
      </button>
    </div> -->
  </div>
</template>

<script>
export default {
  name: "MyHeader",
  methods: {
    gotoMap() {
      // // 1. 清空 token
      // localStorage.removeItem('token')
      // 2. 跳转到登录页面
      this.$router.push("/map");
    },
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
  },
  mounted() {
    this.getTime();
  },
};
</script>

<style lang="less" scoped>
.layout-header-container {
  // background-size: 100%;
  height: 8.6rem;
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

      #time {
        // background: url(./infoBg.png) no-repeat center;
        background-size: 100%;
        border-color: transparent;
        color: #ffffff;
        text-align: center;
        font-size: 1.6rem;
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
        background: url(./head.gif) no-repeat center;
        background-size: 100%;
        height: 11%;
        width: 50%;
        position: absolute;
        top: 0.1rem;
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
        .goMap-bt {
          height: 100%;
          width: 100%;
          background: url(./infoBg.png) no-repeat center;
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
      height:3rem;
      background: url(./header.png) no-repeat;
      // background-size: auto;
      background-size: 100%;
      // padding: 2 2rem;
      margin-left: 0.3rem;
      padding-bottom: 1%;
    }
  }
}
</style>
