<!--
        TimePage.vue组件对应客流高峰分析
-->
<template>
  <div class="com-page">
    <div class="chart" style="height: 700px; width: 50%">
      <Time></Time>
    </div>
    <div class="list" style="height: 700px; width: 50%">
      <!-- 站点高峰列表 -->
      <div class="list_wrapper">
        <h3>│早晚高峰客流站点排名│</h3>
        <ul class="list_morning">
          <span class="list_title">早高峰排名</span>
          <li v-for="item in new_data_morning" class="menu-item">
            <b>{{ item[0] }}：{{ item[1] }}</b>
          </li>
        </ul>
        <ul class="list_night">
          <span class="list_title">晚高峰排名</span>
          <li v-for="item in new_data_night" class="menu-item">
            <b>{{ item[0] }}：{{ item[1] }}</b>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import Time from "@/components/menus/Time.vue";
export default {
  name: "TrafficTimepage",
  components: {
    Time,
  },
  data() {
    return {
      data_morning: [],
      data_night: [],
      new_data_morning:[],
      new_data_night:[],
      currentPage: 1, // 当前显示的页数
      totalPage: 0, // 一共有多少页
      timerId: null, // 定时器的标识
    };
  },
  destroyed() {
    clearInterval(this.timerId);
    // 在组建销毁的时候，需要将监听器取消掉
    // window.removeEventListener('resize', this.screenAdapter)
  },
  mounted() {
    this.getListData();
    
  },

  methods: {
    updataList() {
     this.new_data_morning = this.data_morning.slice((this.currentPage-1)*25,this.currentPage*25)
      this.new_data_night = this.data_night.slice((this.currentPage-1)*25,this.currentPage*25)
    },
    getListData() {
      this.data_morning = this.$store.state.query_time_morning.map((item) => {
        return [item[1], item[2]];
      });
      this.data_night = this.$store.state.query_time_night.map((item) => {
        return [item[1], item[2]];
      });

      this.totalPage =
        this.data_morning.length % 25 === 0
          ?  this.data_morning.length / 25
          :  this.data_morning.length / 25 + 1;
    //   console.log(this.allData.length);
     
      // 启动定时器
      this.startInterval();
       this. updataList();
    },
    startInterval() {
      if (this.timerId) {
        clearInterval(this.timerId);
      }
      this.timerId = setInterval(() => {
        this.currentPage++;
        if (this.currentPage > this.totalPage) {
          this.currentPage = 1;
        }
        this.updataList();
        console.log(this.currentPage);
        // console.log(this.totalPage)
        // console.log(this.allData.length)
      }, 3000);
    },
  },
};
</script>

<style lang="less" scoped>
.chart {
  // background-color: red;
  float: left;
}
.list {
    font: italic;
letter-spacing: 4px;
    text-align: center;
  float: right;
  font-size: 10px;
  color: aliceblue;
  .list_title{
      color: aliceblue;
      letter-spacing: 15px;
       font-size: 17px;
      
  }
}
.menu-item {
  line-height: 23px;
  font-weight: bold;
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  &:hover {
    background-color: #6f42c1;
    cursor: pointer;
  }
  a {
    display: block;
    color: black;
    padding-left: 30px;
    &:hover {
      text-decoration: none;
    }
  }
}
.list_morning {
//   background-color: blue;
  height: 680px;
  width: 50%;
  float: left;
}
.list_night {
  height: 680px;
  width: 50%;
  float: right;
}
</style>